package com.delon.geracaofaturascartaocreditojob.reader;


import com.delon.geracaofaturascartaocreditojob.domain.FaturaCartaoCredito;
import com.delon.geracaofaturascartaocreditojob.domain.Transacao;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.lang.NonNull;

public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito> {

    private final ItemStreamReader<Transacao> transacaoItemStreamReader;
    private Transacao transacaoAtual;

    public FaturaCartaoCreditoReader(ItemStreamReader<Transacao> transacaoItemStreamReader) {
        this.transacaoItemStreamReader = transacaoItemStreamReader;
    }

    @Override
    public FaturaCartaoCredito read() throws Exception {
        Transacao transacao = (transacaoAtual != null) ? transacaoAtual : transacaoItemStreamReader.read();

        if (transacao != null) {
            transacaoAtual = null;
            FaturaCartaoCredito faturaCartaoCredito = new FaturaCartaoCredito();
            faturaCartaoCredito.setCartaoCredito(transacao.cartaoCredito());
            faturaCartaoCredito.setCliente(transacao.cartaoCredito().cliente());
            faturaCartaoCredito.getTransacoes().add(transacao);
            return faturaCartaoCredito;
        }
        return null;
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
