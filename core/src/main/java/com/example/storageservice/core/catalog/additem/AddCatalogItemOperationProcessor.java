package com.example.storageservice.core.catalog.additem;

import com.example.storageservice.api.catalog.additem.AddCatalogItemOperation;
import com.example.storageservice.api.catalog.additem.CatalogItemInput;
import com.example.storageservice.api.catalog.additem.CatalogItemResult;
import com.example.storageservice.persistence.entity.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;

// Collects all items from the storage items table and selects 1 at random.
// After an item is selected it gets an discount parameter from 5 to 30 %

@Service
@RequiredArgsConstructor
public class AddCatalogItemOperationProcessor implements AddCatalogItemOperation {

    @Override
    public CatalogItemResult process(CatalogItemInput operationInput) {

        int index = new Random().nextInt(operationInput.getListOfItems().size());
        Shipment catalogItemId = operationInput.getListOfItems().get(index);
        operationInput.getListOfItems().remove(index);

        return CatalogItemResult.builder()
                .itemId(catalogItemId.getItemId())
                .discount(new Random().nextInt(31) + 5)
                .build();
    }
}
