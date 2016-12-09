package com.goeuro.configs;

import com.goeuro.domains.StationBusRouteMap;
import com.goeuro.usecases.LoadStationBusRouteMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MapLoader {

    private final LoadStationBusRouteMap loadStationBusRouteMap;

    private final String path;

    @Autowired
    public MapLoader(final LoadStationBusRouteMap loadStationBusRouteMap, @Value("${path}") final String path) {
        log.info("path: {}", path);
        this.loadStationBusRouteMap = loadStationBusRouteMap;
        this.path = path;
    }

    @Bean
    public StationBusRouteMap loadMap() {
        return loadStationBusRouteMap.load(path);
    }

}
