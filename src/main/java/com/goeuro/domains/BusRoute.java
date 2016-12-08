package com.goeuro.domains;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class BusRoute {

    @Getter
    private final Integer id;

    private final Set<Station> stations = new HashSet();

    public Integer visitedStationQty() {
        return stations.size();
    }

    public void addStation(final Station station) {
        stations.add(station);
    }

}
