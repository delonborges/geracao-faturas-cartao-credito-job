package com.delon.geracaofaturascartaocreditojob.reader;


import com.delon.geracaofaturascartaocreditojob.domain.FaturaCartaoCredito;
import com.delon.geracaofaturascartaocreditojob.domain.Transacao;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Objects;

public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito> {

    private final ItemStreamReader<Transacao> transacaoItemStreamReader;
    private Transacao transacaoAtual;

    public FaturaCartaoCreditoReader(ItemStreamReader<Transacao> transacaoItemStreamReader) {
        this.transacaoItemStreamReader = transacaoItemStreamReader;
    }

    @Override
    public FaturaCartaoCredito read() throws Exception {
        if (transacaoAtual == null) {
            transacaoAtual = transacaoItemStreamReader.read();
        }

        FaturaCartaoCredito faturaCartaoCredito = null;
        Transacao transacao = transacaoAtual;
        transacaoAtual = null;

        if (transacao != null) {
            faturaCartaoCredito = new FaturaCartaoCredito();
            faturaCartaoCredito.setCartaoCredito(transacao.cartaoCredito());
            faturaCartaoCredito.setCliente(transacao.cartaoCredito().cliente());

            if (faturaCartaoCredito.getTransacoes() == null) {
                faturaCartaoCredito.setTransacoes(new ArrayList<>());
            }

            faturaCartaoCredito.getTransacoes().add(transacao);

            while (isTransacaoRelacionada(transacao)) {
                faturaCartaoCredito.getTransacoes().add(transacaoAtual);
            }
        }
        return faturaCartaoCredito;
    }

    private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {
        return peek() != null && Objects.equals(transacao.cartaoCredito().numeroCartaoCredito(), transacaoAtual.cartaoCredito().numeroCartaoCredito());
    }

    private Transacao peek() throws Exception {
        transacaoAtual = transacaoItemStreamReader.read();
        return transacaoAtual;
    }

    @Override
    public void open(@NonNull ExecutionContext executionContext) throws ItemStreamException {
        transacaoItemStreamReader.open(executionContext);
    }

    @Override
    public void update(@NonNull ExecutionContext executionContext) throws ItemStreamException {
        transacaoItemStreamReader.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        transacaoItemStreamReader.close();
    }
}
