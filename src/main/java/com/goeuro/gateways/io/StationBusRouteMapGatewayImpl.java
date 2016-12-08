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
            final Integer limit = extract(lineIterator);

            while (lineIterator.hasNext() && map.busRouteQty() < limits.getMaxbusroute()) {
                final List<Integer> stationIds = castToIntegerList(lineIterator);
                final BusRoute busRoute = extractBusRoute(stationIds);
                if (!map.hasBusRoute(busRoute)) {
                    addBusRouteInStation(map, busRoute, removeDuplicates(stationIds));
                }
            }
        } finally {
            LineIterator.closeQuietly(lineIterator);
        }

        return map;
    }

    private Integer extract(final LineIterator lineIterator) {
        return Integer.valueOf(lineIterator.nextLine());
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

    private boolean addBusRouteInStation(final StationBusRouteMap map, final BusRoute busRoute, final List<Integer> stationIds) {
        Integer count = 0;
        if (stationIds.size() > 1) {
            while (count < stationIds.size() && count < limits.getMaxstationbyroute() && map.stationQty() < limits.getMaxstation()) {
                final Station station = new Station(stationIds.get(count));
                map.put(station, busRoute);
                count++;
            }
        }
        return count > 0;
    }

}
