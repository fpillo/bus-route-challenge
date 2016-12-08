package com.goeuro.usecases;

import com.goeuro.configs.Limits;
import com.goeuro.domains.BusRoute;
import com.goeuro.domains.Station;
import com.goeuro.domains.StationBusRouteMap;
import com.goeuro.gateways.io.StationBusRouteMapGatewayImpl;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.Arrays;

public class LoadStationBusRouteMapTest {

    @Test
    public void test_load_should_assert_numbers_of_station_and_busroutes() {
        final String path = LoadStationBusRouteMapTest.class.getClassLoader().getResource("file.txt").getPath();

        final Limits limits = new Limits();
        limits.setMaxbusroute(3);
        limits.setMaxstation(8);
        limits.setMaxstationbyroute(5);

        final StationBusRouteMap map = new LoadStationBusRouteMap(new StationBusRouteMapGatewayImpl(limits)).load(path);

        Assert.isTrue(map.stationQty() == 8);

        Assert.isTrue(map.findBusRouteByStation(new Station(0)).size() == 2);
        Assert.isTrue(map.findBusRouteByStation(new Station(0)).containsAll(Arrays.asList(new BusRoute(0), new BusRoute(2))));

        Assert.isTrue(map.findBusRouteByStation(new Station(1)).size() == 2);
        Assert.isTrue(map.findBusRouteByStation(new Station(1)).containsAll(Arrays.asList(new BusRoute(0), new BusRoute(1))));

        Assert.isTrue(map.findBusRouteByStation(new Station(2)).size() == 1);
        Assert.isTrue(map.findBusRouteByStation(new Station(2)).containsAll(Arrays.asList(new BusRoute(0))));

        Assert.isTrue(map.findBusRouteByStation(new Station(3)).size() == 2);
        Assert.isTrue(map.findBusRouteByStation(new Station(3)).containsAll(Arrays.asList(new BusRoute(0), new BusRoute(1))));

        Assert.isTrue(map.findBusRouteByStation(new Station(4)).size() == 2);
        Assert.isTrue(map.findBusRouteByStation(new Station(4)).containsAll(Arrays.asList(new BusRoute(0), new BusRoute(2))));

        Assert.isTrue(map.findBusRouteByStation(new Station(5)).size() == 1);
        Assert.isTrue(map.findBusRouteByStation(new Station(5)).containsAll(Arrays.asList(new BusRoute(1))));

        Assert.isTrue(map.findBusRouteByStation(new Station(6)).size() == 2);
        Assert.isTrue(map.findBusRouteByStation(new Station(6)).containsAll(Arrays.asList(new BusRoute(1), new BusRoute(2))));

        Assert.isTrue(map.findBusRouteByStation(new Station(7)).size() == 1);
        Assert.isTrue(map.findBusRouteByStation(new Station(7)).containsAll(Arrays.asList(new BusRoute(2))));
    }

    @Test
    public void test_load_limit_of_max_busroute() {
        final String path = LoadStationBusRouteMapTest.class.getClassLoader().getResource("file.txt").getPath();

        final Limits limits = new Limits();
        limits.setMaxbusroute(2);
        limits.setMaxstation(8);
        limits.setMaxstationbyroute(5);

        final StationBusRouteMap map = new LoadStationBusRouteMap(new StationBusRouteMapGatewayImpl(limits)).load(path);

        Assert.isTrue(map.stationQty() == 7);
        Assert.isTrue(map.hasStation(new Station(0)));
        Assert.isTrue(map.hasStation(new Station(1)));
        Assert.isTrue(map.hasStation(new Station(2)));
        Assert.isTrue(map.hasStation(new Station(3)));
        Assert.isTrue(map.hasStation(new Station(4)));
        Assert.isTrue(map.hasStation(new Station(5)));
        Assert.isTrue(map.hasStation(new Station(6)));
        Assert.isTrue(!map.hasStation(new Station(7)));

    }

    @Test
    public void test_load_limit_of_max_station() {
        final String path = LoadStationBusRouteMapTest.class.getClassLoader().getResource("file.txt").getPath();

        final Limits limits = new Limits();
        limits.setMaxbusroute(3);
        limits.setMaxstation(6);
        limits.setMaxstationbyroute(5);

        final StationBusRouteMap map = new LoadStationBusRouteMap(new StationBusRouteMapGatewayImpl(limits)).load(path);

        Assert.isTrue(map.stationQty() == 6);
        Assert.isTrue(map.hasStation(new Station(0)));
        Assert.isTrue(map.hasStation(new Station(1)));
        Assert.isTrue(map.hasStation(new Station(2)));
        Assert.isTrue(map.hasStation(new Station(3)));
        Assert.isTrue(map.hasStation(new Station(4)));
        Assert.isTrue(!map.hasStation(new Station(5)));
        Assert.isTrue(map.hasStation(new Station(6)));
        Assert.isTrue(!map.hasStation(new Station(7)));
    }

    @Test
    public void test_load_limit_of_max_station_by_busroute() {
        final String path = LoadStationBusRouteMapTest.class.getClassLoader().getResource("file.txt").getPath();

        final Limits limits = new Limits();
        limits.setMaxbusroute(3);
        limits.setMaxstation(8);
        limits.setMaxstationbyroute(2);

        final StationBusRouteMap map = new LoadStationBusRouteMap(new StationBusRouteMapGatewayImpl(limits)).load(path);

        Assert.isTrue(map.stationQty() == 4);
        Assert.isTrue(map.hasStation(new Station(0)));
        Assert.isTrue(map.hasStation(new Station(1)));
        Assert.isTrue(!map.hasStation(new Station(2)));
        Assert.isTrue(map.hasStation(new Station(3)));
        Assert.isTrue(!map.hasStation(new Station(4)));
        Assert.isTrue(!map.hasStation(new Station(5)));
        Assert.isTrue(map.hasStation(new Station(6)));
        Assert.isTrue(!map.hasStation(new Station(7)));
    }

}
