package com.delon.geracaofaturascartaocreditojob.step;

import com.delon.geracaofaturascartaocreditojob.domain.FaturaCartaoCredito;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class GeracaoFaturaCartaoCreditoStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public GeracaoFaturaCartaoCreditoStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step geracaoFaturaCartaoCreditoStep(ItemReader<FaturaCartaoCredito> faturaCartaoCreditoItemReader,
                                               ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> faturaCartaoCreditoItemProcessor,
                                               ItemWriter<FaturaCartaoCredito> faturaCartaoCreditoItemWriter) {
        return new StepBuilder("geracaoFaturaCartaoCreditoStep", jobRepository).<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1, transactionManager)
                                                                               .reader(faturaCartaoCreditoItemReader)
                                                                               .processor(faturaCartaoCreditoItemProcessor)
                                                                               .writer(faturaCartaoCreditoItemWriter)
                                                                               .build();
    }
}
