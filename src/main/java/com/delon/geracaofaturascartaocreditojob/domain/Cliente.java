package com.delon.geracaofaturascartaocreditojob.domain;

import lombok.Builder;

@Builder
public record Cliente(Integer id,
                      String nome,
                      String endereco) {}
