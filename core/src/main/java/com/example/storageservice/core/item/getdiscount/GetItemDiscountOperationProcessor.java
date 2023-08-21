package com.example.storageservice.core.item.getdiscount;

import com.example.storageservice.api.Item.getdiscount.GetDiscountInput;
import com.example.storageservice.api.Item.getdiscount.GetDiscountOperation;
import com.example.storageservice.api.Item.getdiscount.GetDiscountResult;
import com.example.storageservice.api.base.OperationInput;
import com.example.storageservice.api.base.OperationResult;
import com.example.storageservice.persistence.entity.OnSaleItem;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class GetItemDiscountOperationProcessor implements GetDiscountOperation {
    private  final OnSaleItemRepository onSaleItemRepository;

    @Override
    public GetDiscountResult process(GetDiscountInput operationInput) throws Exception {

        OnSaleItem onSaleItem = onSaleItemRepository.findByItemId(operationInput.getItemId())
                .orElseThrow(()-> new NoSuchElementException("There is no such item in the catalog"));

        return GetDiscountResult.builder()
                .itemId(onSaleItem.getItemId())
                .discount(onSaleItem.getDiscount())
                .build();
    }
}
