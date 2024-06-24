package com.delon.geracaofaturascartaocreditojob.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaturaCartaoCredito {
    Cliente cliente;
    CartaoCredito cartaoCredito;
    List<Transacao> transacoes;

    public Double getTotal() {
        return transacoes.stream().mapToDouble(Transacao::valor).reduce(0.0, Double::sum);
    }
}
