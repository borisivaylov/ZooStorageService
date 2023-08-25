package com.example.storageservice.core.item.getdiscount;

import com.example.storageservice.api.Item.getdiscount.GetDiscountInput;
import com.example.storageservice.api.Item.getdiscount.GetDiscountOperation;
import com.example.storageservice.api.Item.getdiscount.GetDiscountResult;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveCatalogsOperationProcessor;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveResult;
import com.example.storageservice.persistence.entity.OnSaleItem;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

// Returns the discount percent of an item from an active catalog

@RequiredArgsConstructor
@Service
public class GetItemDiscountOperationProcessor implements GetDiscountOperation {
    private  final OnSaleItemRepository onSaleItemRepository;
    private final GetAllActiveCatalogsOperationProcessor getAllActiveCatalogsOperationProcessor;

    @Override
    public GetDiscountResult process(GetDiscountInput operationInput) {

        List<GetAllActiveResult> getAllActiveResultList = getAllActiveCatalogsOperationProcessor
                                                                .process(GetAllActiveInput.builder().build());
        AtomicReference<OnSaleItem> onSaleItem = new AtomicReference<>(new OnSaleItem());


        getAllActiveResultList.forEach(catalog ->{
            OnSaleItem currentItem = onSaleItemRepository
                    .findByItemIdAndCatalogId(operationInput.getItemId(),catalog.getCatalogId())
                        .orElseThrow(()-> new NoSuchElementException("No such item in this catalog"));

             onSaleItem.set(OnSaleItem.builder()
                     .catalogId(currentItem.getCatalogId())
                     .itemId(currentItem.getItemId())
                     .discount(currentItem.getDiscount())
                     .build());
        });

        return GetDiscountResult.builder()
                .itemId(onSaleItem.get().getItemId())
                .discount(onSaleItem.get().getDiscount())
                .build();
    }
}
