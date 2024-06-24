package com.delon.geracaofaturascartaocreditojob.domain;

import lombok.Builder;

import java.util.Date;

@Builder
public record Transacao(Integer id,
                        CartaoCredito cartaoCredito,
                        String descricao,
                        Double valor,
                        Date data) {}
