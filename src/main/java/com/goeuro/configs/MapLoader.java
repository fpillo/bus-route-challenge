package com.goeuro.configs;

import com.goeuro.domains.StationBusRouteMap;
import com.goeuro.usecases.LoadStationBusRouteMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MapLoader {

    private final LoadStationBusRouteMap loadStationBusRouteMap;

    @Autowired
    public MapLoader(final LoadStationBusRouteMap loadStationBusRouteMap) {
        this.loadStationBusRouteMap = loadStationBusRouteMap;
    }

    @Bean
    public StationBusRouteMap loadMap() {
        return loadStationBusRouteMap.load("/Users/fernando.lucia/bus-route-challenge/src/main/resources/file.txt");
    }

}
