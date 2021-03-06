package com.goeuro.domains;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Station {

    @Getter
    private final Integer id;

}
