package com.api.rest.encuestas.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class VotoResult {

    private int totalVotos;

    private Collection<OpcionCount> results;

}
