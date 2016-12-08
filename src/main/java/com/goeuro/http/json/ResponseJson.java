package com.goeuro.http.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ResponseJson {

    @Getter
    @JsonProperty(value = "dep_sid")
    private final Integer from;

    @Getter
    @JsonProperty(value = "arr_sid")
    private final Integer to;

    @Getter
    @JsonProperty(value = "direct_bus_route")
    private final Boolean hasRoute;

}
