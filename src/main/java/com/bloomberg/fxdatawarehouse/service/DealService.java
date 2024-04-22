package com.bloomberg.fxdatawarehouse.service;


import com.bloomberg.fxdatawarehouse.model.Deal;

import java.util.List;

public interface DealService {
    void saveDeal(Deal deal);
    Deal getDealById(Long id);
    List<Deal> getAllDeals();
    void updateDeal(Long id, Deal deal);
    void deleteDeal(Long id);
}

