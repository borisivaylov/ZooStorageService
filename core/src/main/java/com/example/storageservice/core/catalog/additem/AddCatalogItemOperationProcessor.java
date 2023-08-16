package com.example.storageservice.core.catalog.additem;

import com.example.storageservice.api.catalog.additem.AddCatalogItemOperation;
import com.example.storageservice.api.catalog.additem.CatalogItemInput;
import com.example.storageservice.api.catalog.additem.CatalogItemResult;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.CatalogRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddCatalogItemOperationProcessor implements AddCatalogItemOperation {

    @Override
    public CatalogItemResult process(CatalogItemInput operationInput) throws Exception {

        int index = new Random().nextInt(operationInput.getListOfItems().size());
        Shipment catalogItemId = operationInput.getListOfItems().get(index);
        operationInput.getListOfItems().remove(index);

        return CatalogItemResult.builder()
                .itemId(catalogItemId.getItemId())
                .discount(new Random().nextInt(31) + 5)
                .build();
    }
}
