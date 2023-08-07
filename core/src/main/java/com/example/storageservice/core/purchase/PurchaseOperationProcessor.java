package com.example.storageservice.core.purchase;

import com.example.bffshare.persistence.entity.Cart;
import com.example.bffshare.restexport.BffShareRestExport;
import com.example.storageservice.api.purchase.cartpurchase.PurchaseOperation;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseResult;
import com.example.storageservice.persistence.entity.Purchase;

import com.example.storageservice.persistence.repository.PurchaseRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOperationProcessor implements PurchaseOperation {

    boolean transaction = true;

    private final PurchaseRepository purchaseRepository;
    private final BffShareRestExport bffShareRestExport;
    private final ShipmentRepository shipmentRepository;

    @Override
    public StoragePurchaseResult process(StoragePurchaseRequest operationInput) throws Exception {


        Boolean successful = true;

        //Cart currentCart = operationInput.getCart();


      //  Map<UUID,Integer> cartMap = currentCart.getItemMap();

        /*cartMap.entrySet().stream()
                .allMatch(entry -> {
                    if( shipmentRepository.findShipmentByItemId(entry.getKey()).getQuantity()<entry.getValue())
                        successful.set(false);

                    return true;
                });*/


        for (Map.Entry<UUID,Integer> entry: operationInput.getItems().entrySet()) {
            if( shipmentRepository.findShipmentByItemId(entry.getKey()).getQuantity()<entry.getValue())
                successful=false;
        }
        /*cartMap.forEach((key, value) -> {
            Shipment shipment = shipmentRepository.findShipmentByItemId(key);
            if((value>shipment.getQuantity()){
                return PurchaseResult.builder()
                        .status("not enough quantity of item" + key )
                        .build();
            }
        });*/

        if (!successful)
        {
            return StoragePurchaseResult.builder()
                    .status("ERR")
                    .build();
        }


        Purchase purchase = Purchase.builder()
                .userId(operationInput.getUserId())
                .cart(operationInput.getItems())
                .cartId(operationInput.getCartId())
                .build();

        purchaseRepository.save(purchase);

        return StoragePurchaseResult.builder()
                .status("Success")
                .build();
    }
}
