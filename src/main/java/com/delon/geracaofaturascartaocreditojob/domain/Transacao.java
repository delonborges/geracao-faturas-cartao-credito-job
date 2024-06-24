package com.delon.geracaofaturascartaocreditojob.domain;

import java.util.Date;

public record Transacao(Integer id,
                        CartaoCredito cartaoCredito,
                        String descricao,
                        Double valor,
                        Date data) {}
