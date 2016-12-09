package com.goeuro.usecases;


import com.goeuro.domains.BusRoute;
import com.goeuro.domains.Station;
import com.goeuro.domains.StationBusRouteMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FindBusRouteByStations {

    private final StationBusRouteMap stationBusRouteMap;

    @Autowired
    public FindBusRouteByStations(final StationBusRouteMap stationBusRouteMap) {
        this.stationBusRouteMap = stationBusRouteMap;
    }

    public Collection<BusRoute> find(final Station from, final Station to) {
        log.info("Searching for direct connections from: {}, to: {}", from, to);
        final Collection<BusRoute> fromRoutes = stationBusRouteMap.findBusRouteByStation(from);
        final Collection<BusRoute> toRoutes = stationBusRouteMap.findBusRouteByStation(to);

        return findIntersection(fromRoutes, toRoutes);
    }

    private Collection<BusRoute> findIntersection(final Collection<BusRoute> fromRoutes, final Collection<BusRoute> toRoutes) {
        return fromRoutes.stream().filter(toRoutes::contains).collect(Collectors.toList());
    }

}
