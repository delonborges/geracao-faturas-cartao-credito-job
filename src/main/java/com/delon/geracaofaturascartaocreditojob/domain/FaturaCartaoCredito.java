package com.delon.geracaofaturascartaocreditojob.domain;

import java.util.List;

public record FaturaCartaoCredito(Cliente cliente,
                                  CartaoCredito cartaoCredito,
                                  List<Transacao> transacoes) {}
