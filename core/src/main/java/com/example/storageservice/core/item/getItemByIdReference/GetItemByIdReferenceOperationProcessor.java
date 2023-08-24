package com.example.storageservice.core.item.getItemByIdReference;


import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleInput;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceOperation;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceRequest;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceResponse;
import com.example.storageservice.api.Item.getdiscount.GetDiscountInput;
import com.example.storageservice.core.item.getdiscount.GetItemDiscountOperationProcessor;
import com.example.storageservice.core.item.ifonsale.CheckIfOnSaleOperationProcessor;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetItemByIdReferenceOperationProcessor implements GetItemByIdReferenceOperation {

    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;
    private final CheckIfOnSaleOperationProcessor checkIfOnSaleOperationProcessor;
    private final GetItemDiscountOperationProcessor getItemDiscountOperationProcessor;
    @Override
    public GetItemByIdReferenceResponse process(GetItemByIdReferenceRequest getItemByIdReferenceRequest) {

        Shipment shipment = shipmentRepository.getReferenceById(getItemByIdReferenceRequest.getItemId());

        if (checkIfOnSaleOperationProcessor.process(CheckIfOnSaleInput.builder()
                .itemId(shipment.getItemId()).build()).isOnSale()){
            Integer discount = getItemDiscountOperationProcessor.process(GetDiscountInput.builder().itemId(shipment.getItemId()).build()).getDiscount();
            Double price = shipment.getPrice()*(1-discount/100);
            shipment.setPrice(price);
        }

        return GetItemByIdReferenceResponse.builder()
                .ItemId(shipment.getItemId())
                .price(shipment.getPrice())
                .quantity(shipment.getQuantity())
                .build();
    }
}
