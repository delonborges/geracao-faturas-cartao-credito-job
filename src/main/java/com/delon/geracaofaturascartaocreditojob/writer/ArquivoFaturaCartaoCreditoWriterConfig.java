package com.delon.geracaofaturascartaocreditojob.writer;

import com.delon.geracaofaturascartaocreditojob.domain.FaturaCartaoCredito;
import com.delon.geracaofaturascartaocreditojob.domain.Transacao;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

@Configuration
public class ArquivoFaturaCartaoCreditoWriterConfig {

    @Bean
    public MultiResourceItemWriter<FaturaCartaoCredito> arquivoFaturaCartaoCreditoWriter() {
        return new MultiResourceItemWriterBuilder<FaturaCartaoCredito>().name("arquivoFaturaCartaoCreditoWriter")
                                                                        .resource(new FileSystemResource("src/main/resources/files/bills/bill"))
                                                                        .itemCountLimitPerResource(1)
                                                                        .resourceSuffixCreator(suffixCreator())
                                                                        .delegate(arquivoFaturaCartaoCredito())
                                                                        .build();
    }

    private FlatFileItemWriter<FaturaCartaoCredito> arquivoFaturaCartaoCredito() {
        return new FlatFileItemWriterBuilder<FaturaCartaoCredito>().name("arquivoFaturaCartaoCredito")
                                                                   .resource(new FileSystemResource("src/main/resources/files/bills/bill.txt"))
                                                                   .lineAggregator(lineAggregator())
                                                                   .headerCallback(headerCallback())
                                                                   .footerCallback(footerCallback())
                                                                   .build();
    }

    @Bean
    public FlatFileFooterCallback footerCallback() {
        return new TotalTransacoesFooterCallback();
    }

    private FlatFileHeaderCallback headerCallback() {
        return writer -> {
            writer.append(String.format("%121s%n", "Cartão XPTO"));
            writer.append(String.format("%121s%n%n", "Rua Tralálá, 999"));
        };
    }

    private LineAggregator<FaturaCartaoCredito> lineAggregator() {
        return faturaCartaoCredito -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("Nome: %s%n", faturaCartaoCredito.getCliente().nome()));
            stringBuilder.append(String.format("Endereço: %s%n%n%n", faturaCartaoCredito.getCliente().endereco()));
            stringBuilder.append(String.format("Fatura completa do cartão %d%n", faturaCartaoCredito.getCartaoCredito().numeroCartaoCredito()));
            stringBuilder.append("-----------------------------------------------------------------------------------------------------------%n");
            stringBuilder.append("DATA DESCRIÇÃO VALOR\n");
            stringBuilder.append("-----------------------------------------------------------------------------------------------------------%n");
            for (Transacao transacao : faturaCartaoCredito.getTransacoes()) {
                stringBuilder.append(String.format("%n[%10s] %-80s - %s",
                                                   new SimpleDateFormat("dd/MM/yyyy").format(transacao.data()),
                                                   transacao.descricao(),
                                                   NumberFormat.getCurrencyInstance().format(transacao.valor())));
            }
            return stringBuilder.toString();
        };
    }

    private ResourceSuffixCreator suffixCreator() {
        return index -> index + ".txt";
    }
}
