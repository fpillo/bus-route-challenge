package com.goeuro.usecases;


import com.goeuro.domains.BusRoute;
import com.goeuro.domains.Station;
import com.goeuro.domains.StationBusRouteMap;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;

public class FindBusRouteByStationsTest {

    @Test
    public void test_find_should_return_1_busroute() {
        final StationBusRouteMap map = loadDefaultData();
        final Collection<BusRoute> routes = new FindBusRouteByStations(map).find(new Station(1), new Station(5));

        Assert.isTrue(routes.size() == 1);
        Assert.isTrue(routes.containsAll(Arrays.asList(new BusRoute(1))));
    }


    @Test
    public void test_find_should_return_2_busroute() {
        final StationBusRouteMap map = loadDefaultData();

        final Collection<BusRoute> routes = new FindBusRouteByStations(map).find(new Station(0), new Station(4));

        Assert.isTrue(routes.size() == 2);
        Assert.isTrue(routes.containsAll(Arrays.asList(new BusRoute(0), new BusRoute(2))));
    }

    @Test
    public void test_find_should_return_0_busroute() {
        final StationBusRouteMap map = loadDefaultData();

        final Collection<BusRoute> routes = new FindBusRouteByStations(map).find(new Station(0), new Station(5));

        Assert.isTrue(routes.size() == 0);
    }

    @Test
    public void test_find_should_using_invalid_station_should_return_0_busroute() {
        final StationBusRouteMap map = loadDefaultData();

        final Collection<BusRoute> routes = new FindBusRouteByStations(map).find(new Station(0), new Station(7));

        Assert.isTrue(routes.size() == 0);
    }


    private StationBusRouteMap loadDefaultData() {
        final BusRoute busRoute0 = new BusRoute(0);
        final BusRoute busRoute1 = new BusRoute(1);
        final BusRoute busRoute2 = new BusRoute(2);

        final Station station0 = new Station(0);
        final Station station1 = new Station(1);
        final Station station2 = new Station(2);
        final Station station3 = new Station(3);
        final Station station4 = new Station(4);
        final Station station5 = new Station(5);
        final Station station6 = new Station(6);

        final StationBusRouteMap map = new StationBusRouteMap();
        map.put(station0, busRoute0);
        map.put(station0, busRoute2);

        map.put(station1, busRoute0);
        map.put(station1, busRoute1);

        map.put(station2, busRoute0);

        map.put(station3, busRoute0);
        map.put(station3, busRoute1);

        map.put(station4, busRoute0);
        map.put(station4, busRoute2);

        map.put(station5, busRoute1);

        map.put(station6, busRoute1);
        map.put(station6, busRoute2);

        return map;
    }


}
