package com.goeuro.gateways.io;

import com.goeuro.configs.Limits;
import com.goeuro.domains.BusRoute;
import com.goeuro.domains.Station;
import com.goeuro.domains.StationBusRouteMap;
import com.goeuro.gateways.StationBusRouteMapGateway;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StationBusRouteMapGatewayImpl implements StationBusRouteMapGateway {

    private final Limits limits;

    @Autowired
    public StationBusRouteMapGatewayImpl(final Limits limits) {
        this.limits = limits;
    }

    @Override
    public StationBusRouteMap load(final String path) {
        final StationBusRouteMap map = new StationBusRouteMap();
        final File file = new File(path);

        final LineIterator lineIterator;
        try {
            lineIterator = FileUtils.lineIterator(file);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }

        try {
            final Integer maxBusRouteQty = getMaxBusRouteQty(lineIterator);

            while (lineIterator.hasNext() && map.busRouteQty() < maxBusRouteQty) {
                final List<Integer> stationIds = castToIntegerList(lineIterator);
                final BusRoute busRoute = extractBusRoute(stationIds);
                addBusRouteInStation(map, busRoute, removeDuplicates(stationIds));
            }
        } finally {
            LineIterator.closeQuietly(lineIterator);
            return map;
        }
    }

    private Integer getMaxBusRouteQty(final LineIterator lineIterator) {
        final Integer max = Integer.valueOf(lineIterator.nextLine());
        return max <= limits.getMaxbusroute() ? max : limits.getMaxbusroute();
    }

    private List<Integer> castToIntegerList(final LineIterator lineIterator) {
        return new ArrayList(Arrays.asList(lineIterator.nextLine().split(" ")).stream().map(Integer::valueOf).collect(Collectors.toList()));
    }

    private BusRoute extractBusRoute(final List<Integer> numbers) {
        final Integer id = numbers.get(0);
        numbers.remove(0);

        return new BusRoute(id);
    }

    private List<Integer> removeDuplicates(final List<Integer> stationIds) {
        return stationIds.parallelStream().distinct().collect(Collectors.toList());
    }

    private void addBusRouteInStation(final StationBusRouteMap map, final BusRoute busRoute, final List<Integer> stationIds) {
        if (stationIds.size() > 1 && !map.hasBusRoute(busRoute)) {
            stationIds.forEach(id -> {
                if (busRoute.visitedStationQty() < limits.getMaxstationbyroute() && map.stationQty() < limits.getMaxstation()) {
                    map.put(new Station(id), busRoute);
                }
            });
        }
    }

}
