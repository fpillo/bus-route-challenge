package com.goeuro.http;

import com.goeuro.domains.BusRoute;
import com.goeuro.domains.Station;
import com.goeuro.http.json.ResponseJson;
import com.goeuro.usecases.FindBusRouteByStations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
public class ApiController {

    private final FindBusRouteByStations findBusRouteByStations;

    @Autowired
    public ApiController(final FindBusRouteByStations findBusRouteByStations) {
        this.findBusRouteByStations = findBusRouteByStations;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/direct", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseJson hasBusRoute(@RequestParam(name = "dep_sid") final Integer fromStation,
                                    @RequestParam(name = "arr_sid") final Integer toStation) {

        log.trace("Searching direct connections from: {} to: {}", fromStation, toStation);
        final Collection<BusRoute> routes = findBusRouteByStations.find(new Station(fromStation), new Station(toStation));
        final boolean hasRoute = !routes.isEmpty();

        return new ResponseJson(fromStation, toStation, hasRoute);
    }

}
