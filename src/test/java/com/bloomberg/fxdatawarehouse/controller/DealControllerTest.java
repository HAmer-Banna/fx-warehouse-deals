package com.bloomberg.fxdatawarehouse.controller;

import com.bloomberg.fxdatawarehouse.model.Deal;
import com.bloomberg.fxdatawarehouse.service.DealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DealControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dealController).build();
    }

    @Test
    public void testCreateDeal() throws Exception {
        Deal deal = new Deal();
        deal.setDealUniqueId("123");
        deal.setFromCurrencyIsoCode("USD");
        deal.setToCurrencyIsoCode("EUR");
        deal.setDealAmount(100.0);

        ObjectMapper objectMapper = new ObjectMapper();
        String dealJson = objectMapper.writeValueAsString(deal);

        mockMvc.perform(post("/api/deals").contentType(MediaType.APPLICATION_JSON).content(dealJson)).andExpect(status().isCreated());

        verify(dealService, times(1)).saveDeal(any(Deal.class));
    }
}

