package com.delon.geracaofaturascartaocreditojob.writer;

import com.delon.geracaofaturascartaocreditojob.domain.FaturaCartaoCredito;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {
    private Double totalTransacoes = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.write(String.format("%n%121s", "Total: " + NumberFormat.getCurrencyInstance().format(totalTransacoes)));
    }

    @BeforeWrite
    public void beforeWrite(Chunk<FaturaCartaoCredito> faturas) {
        for (FaturaCartaoCredito fatura : faturas.getItems()) {
            totalTransacoes += fatura.getTotal();
        }
    }

    @AfterChunk
    public void afterChunk() {
        totalTransacoes = 0.0;
    }
}
