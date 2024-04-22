package com.bloomberg.fxdatawarehouse.service;

import com.bloomberg.fxdatawarehouse.config.TestConfig;
import com.bloomberg.fxdatawarehouse.model.Deal;
import com.bloomberg.fxdatawarehouse.repository.DealRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    @Autowired
    private DealService dealService;

    @Test
    public void testGetAllDeals() {
        // Given
        List<Deal> deals = new ArrayList<>();
        Deal deal = new Deal();
        deal.setDealUniqueId("4321");
        deal.setFromCurrencyIsoCode("USD");
        deal.setToCurrencyIsoCode("EUR");
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(100.0);

        dealService.saveDeal(deal);

        Mockito.when(dealRepository.findAll()).thenReturn(deals);

        List<Deal> result = dealService.getAllDeals();

        assertEquals(1, result.size());
    }
}
