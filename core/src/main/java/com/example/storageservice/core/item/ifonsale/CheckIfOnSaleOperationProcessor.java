package com.example.storageservice.core.item.ifonsale;

import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleInput;
import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleOperation;
import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleResult;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckIfOnSaleOperationProcessor implements CheckIfOnSaleOperation {

    private final OnSaleItemRepository onSaleItemRepository;

    @Override
    public CheckIfOnSaleResult process(CheckIfOnSaleInput operationInput) throws Exception {

        if (onSaleItemRepository.existsById(operationInput.getItemId())){
            return CheckIfOnSaleResult.builder().isOnSale(true).build();
        }

        return CheckIfOnSaleResult.builder().isOnSale(false).build();
    }
}
