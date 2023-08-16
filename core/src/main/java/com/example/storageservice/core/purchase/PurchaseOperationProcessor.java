package com.example.storageservice.core.purchase;

import com.example.bffshare.persistence.entity.Cart;
import com.example.bffshare.restexport.BffShareRestExport;
import com.example.storageservice.api.purchase.cartpurchase.PurchaseOperation;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseResult;
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

@Service
@RequiredArgsConstructor
public class PurchaseOperationProcessor implements PurchaseOperation {

    private final PurchaseRepository purchaseRepository;
    private final BffShareRestExport bffShareRestExport;
    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;

    @Override
    public StoragePurchaseResult process(StoragePurchaseRequest operationInput) throws Exception {


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

        cartItems.forEach((key, value) -> {
            Shipment current = shipmentRepository.findShipmentByItemId(key) .orElseThrow(()-> new NoSuchElementException("No such shipment"));;
            current.setQuantity(current.getQuantity() - value);
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
