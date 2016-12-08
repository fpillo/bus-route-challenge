package com.goeuro.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "limits")
public class Limits {

    private Integer maxbusroute;

    private Integer maxstation;

    private Integer maxstationbyroute;

}
