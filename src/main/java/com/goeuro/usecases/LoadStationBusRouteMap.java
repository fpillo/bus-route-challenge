package com.goeuro.usecases;

import com.goeuro.domains.StationBusRouteMap;
import com.goeuro.gateways.StationBusRouteMapGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoadStationBusRouteMap {

    private final StationBusRouteMapGateway stationBusRouteMapGateway;

    @Autowired
    public LoadStationBusRouteMap(final StationBusRouteMapGateway stationBusRouteMapGateway) {
        this.stationBusRouteMapGateway = stationBusRouteMapGateway;
    }

    public StationBusRouteMap load(final String path) {
        log.info("Loading StationBusRoute using file: {}", path);
        return stationBusRouteMapGateway.load(path);
    }

}
