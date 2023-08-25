package com.example.storageservice.core.item.ifonsale;

import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleInput;
import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleOperation;
import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleResult;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveCatalogsOperationProcessor;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveResult;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


// Returns the status of an item (if it is on sale in active catalog or not)

@RequiredArgsConstructor
@Service
public class CheckIfOnSaleOperationProcessor implements CheckIfOnSaleOperation {

    private final OnSaleItemRepository onSaleItemRepository;
    private final GetAllActiveCatalogsOperationProcessor getAllActiveCatalogsOperationProcessor;

    @Override
    public CheckIfOnSaleResult process(CheckIfOnSaleInput operationInput){

        GetAllActiveInput getAllActiveInput;
        List<GetAllActiveResult> getAllActiveResultList = getAllActiveCatalogsOperationProcessor.process(GetAllActiveInput.builder().build());

        List<UUID> itemsOnSale = new ArrayList<>();

        getAllActiveResultList.forEach(catalog ->{
            itemsOnSale.addAll(catalog.getCatalogItems());
        });

        if (itemsOnSale.contains(operationInput.getItemId())){
            return CheckIfOnSaleResult.builder().isOnSale(true).build();
        }

        return CheckIfOnSaleResult.builder().isOnSale(false).build();
    }
}
