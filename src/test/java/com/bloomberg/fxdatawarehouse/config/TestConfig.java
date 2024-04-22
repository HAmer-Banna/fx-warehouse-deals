package com.bloomberg.fxdatawarehouse.config;

import com.bloomberg.fxdatawarehouse.repository.DealRepository;
import com.bloomberg.fxdatawarehouse.service.DealServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@TestConfiguration
public class TestConfig {
    @Bean
    public DealRepository dealRepository() {
        return Mockito.mock(DealRepository.class);
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return Mockito.mock(DataSourceTransactionManager.class);
    }

    @Bean
    public DealServiceImpl dealService(DealRepository dealRepository, DataSourceTransactionManager transactionManager) {
        return new DealServiceImpl(dealRepository, transactionManager);
    }
}

