package com.bloomberg.fxdatawarehouse.service;

import com.bloomberg.fxdatawarehouse.model.Deal;
import com.bloomberg.fxdatawarehouse.repository.DealRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final DataSourceTransactionManager transactionManager;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository, DataSourceTransactionManager transactionManager) {
        this.dealRepository = dealRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveDeal(Deal deal) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            dealRepository.save(deal);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public Deal getDealById(Long id) {
        return dealRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Deal not found"));
    }

    @Override
    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    @Override
    public void updateDeal(Long id, Deal deal) {
        deal.setId(id);
        dealRepository.save(deal);
    }

    @Override
    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }
}
