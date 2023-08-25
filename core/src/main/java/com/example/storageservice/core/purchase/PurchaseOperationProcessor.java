package com.example.storageservice.core.purchase;

import com.example.bffshare.persistence.entity.Cart;
import com.example.bffshare.restexport.BffShareRestExport;
import com.example.storageservice.api.Item.getItem.ItemRequest;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.purchase.cartpurchase.PurchaseOperation;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseResult;
import com.example.storageservice.core.item.getItem.GetItemOperationProcessor;
import com.example.storageservice.persistence.entity.Purchase;

import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.PurchaseRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

 /* Receives a request from bff, and executes the purchase,
    saves it in the purchases table and returns status to the bff
    if the operation was a success or not */

@Service
@RequiredArgsConstructor
public class PurchaseOperationProcessor implements PurchaseOperation {

    private final PurchaseRepository purchaseRepository;
    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;
    private final GetItemOperationProcessor getItemOperationProcessor;

    @Override
    public StoragePurchaseResult process(StoragePurchaseRequest operationInput)  {


        Map<UUID,Integer> cartItems= operationInput.getItems();

        boolean successful= cartItems.entrySet().stream()
                .allMatch( entry -> shipmentRepository.findShipmentByItemId(entry.getKey())
                        .orElseThrow(()-> new NoSuchElementException("No such shipment")).getQuantity() - entry.getValue()>=0);

        if (!successful)
        {
            return StoragePurchaseResult.builder()
                    .status("ERR")
                    .build();
        }

        operationInput.setSumPrice(0);

        cartItems.forEach((key, value) -> {
            boolean onSale = false;
            Shipment current = shipmentRepository.findShipmentByItemId(key) .orElseThrow(()-> new NoSuchElementException("No such shipment"));;
            current.setQuantity(current.getQuantity() - value);

            if (onSaleItemRepository.existsById(current.getItemId())){
                ItemResponse onSaleItem = getItemOperationProcessor.process(ItemRequest.builder().id(current.getItemId()).build());
                operationInput.setSumPrice(operationInput.getSumPrice() + onSaleItem.getPrice());
                onSale=true;
            }

            if (!onSale){
                operationInput.setSumPrice(operationInput.getSumPrice() + current.getPrice());
            }

            shipmentRepository.save(current);

        });


        Purchase purchase = Purchase.builder()
                .userId(operationInput.getUserId())
                .cart(operationInput.getItems())
                .cartId(operationInput.getCartId())
                .sumPrice(operationInput.getSumPrice())
                .build();

        purchaseRepository.save(purchase);

        return StoragePurchaseResult.builder()
                .status("Success")
                .build();
    }
}
