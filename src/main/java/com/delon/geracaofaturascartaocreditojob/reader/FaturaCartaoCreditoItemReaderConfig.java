package com.delon.geracaofaturascartaocreditojob.reader;

import com.delon.geracaofaturascartaocreditojob.domain.CartaoCredito;
import com.delon.geracaofaturascartaocreditojob.domain.Cliente;
import com.delon.geracaofaturascartaocreditojob.domain.Transacao;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
public class FaturaCartaoCreditoItemReaderConfig {

    @Bean
    public JdbcCursorItemReader<Transacao> faturaCartaoCreditoItemReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Transacao>().name("faturaCartaoCreditoItemReader")
                                                           .dataSource(dataSource)
                                                           .sql("select * from transacao join cartao_credito using (numero_cartao_credito) order by numero_cartao_credito")
                                                           .rowMapper(rowMapperTransacao())
                                                           .build();
    }

    private RowMapper<Transacao> rowMapperTransacao() {
        return (rs, rowNum) -> {
            var cliente = Cliente.builder().id(rs.getInt("cliente")).build();
            var cartaoCredito = CartaoCredito.builder().numeroCartaoCredito(rs.getInt("numero_cartao_credito")).cliente(cliente).build();
            return Transacao.builder()
                            .id(rs.getInt("transacao"))
                            .cartaoCredito(cartaoCredito)
                            .data(rs.getDate("data"))
                            .valor(rs.getDouble("valor"))
                            .descricao(rs.getString("descricao"))
                            .build();
        };
    }
}
