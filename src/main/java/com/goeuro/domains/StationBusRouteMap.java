package com.goeuro.domains;


import java.util.*;

public class StationBusRouteMap {

    private final Set<BusRoute> busRoutes = new HashSet();

    private final Map<Station, Set<BusRoute>> map = new HashMap();

    public void put(final Station station, final BusRoute busRoute) {
        if (!map.containsKey(station)) {
            map.put(station, new HashSet());
        }
        map.get(station).add(busRoute);
        busRoutes.add(busRoute);
        busRoute.addStation(station);
    }

    public Collection<BusRoute> findBusRouteByStation(final Station station) {
        if (map.containsKey(station)) {
            return map.get(station);
        }
        return Collections.emptyList();
    }

    public Integer stationQty() {
        return map.keySet().size();
    }

    public Integer busRouteQty() {
        return busRoutes.size();
    }

    public boolean hasStation(final Station station) {
        return map.containsKey(station);
    }

    public boolean hasBusRoute(final BusRoute busRoute) {
        return busRoutes.contains(busRoute);
    }

}
