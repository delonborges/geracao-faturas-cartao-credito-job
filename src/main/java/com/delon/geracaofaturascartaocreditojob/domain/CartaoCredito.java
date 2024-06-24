package com.delon.geracaofaturascartaocreditojob.domain;

import lombok.Builder;

@Builder
public record CartaoCredito(Integer numeroCartaoCredito,
                            Cliente cliente) {}
