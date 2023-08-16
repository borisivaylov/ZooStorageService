package com.example.storageservice.core.catalog.generate;

import com.example.storageservice.api.catalog.additem.CatalogItemInput;
import com.example.storageservice.api.catalog.additem.CatalogItemResult;
import com.example.storageservice.api.catalog.generate.GenerateCatalogInput;
import com.example.storageservice.api.catalog.generate.GenerateCatalogOperation;
import com.example.storageservice.api.catalog.generate.GenerateCatalogResult;
import com.example.storageservice.core.catalog.additem.AddCatalogItemOperationProcessor;
import com.example.storageservice.persistence.entity.Catalog;
import com.example.storageservice.persistence.entity.OnSaleItem;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.CatalogRepository;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GenerateCatalogOperationProcessor implements GenerateCatalogOperation {
    private final ShipmentRepository shipmentRepository;

    private final OnSaleItemRepository onSaleItemRepository;

    private final CatalogRepository catalogRepository;

    private final AddCatalogItemOperationProcessor addCatalogItemOperationProcessor;

    @Override
    public GenerateCatalogResult process(GenerateCatalogInput operationInput) throws Exception {

        List<Shipment> items = shipmentRepository.findAll();
        List<UUID> onSaleItemIds = new ArrayList<>();


       /* IntStream.rangeClosed(0,operationInput.getItemsCount())
                .mapToObj(i -> {
                    CatalogItemResult currentItem = new CatalogItemResult();
                    try {
                        currentItem = addCatalogItemOperationProcessor.process(CatalogItemInput.builder().ListOfItems(items).build());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    catalogItemRepository.save(CatalogItem.builder()
                            .itemId(currentItem.getItemId())
                            .discount(currentItem.getDiscount())
                            .build());

                    newCatalog.getItems().add(currentItem.getItemId());
                    return currentItem.getItemId();
                }
        ).forEach(newCatalog.getItems()::add);*/

        onSaleItemRepository.deleteAll();

        for (int i =0; i<operationInput.getItemsCount();i++)
        {
            CatalogItemResult currentItem = addCatalogItemOperationProcessor.process(CatalogItemInput.
                    builder()
                    .ListOfItems(items)
                            .build());

            onSaleItemRepository.save(OnSaleItem.builder()
                    .itemId(currentItem.getItemId())
                    .discount(currentItem.getDiscount())
                    .build());

            onSaleItemIds.add(currentItem.getItemId());
        }

        Catalog newCatalog = Catalog.builder()
                .items(onSaleItemIds)
                .dateOfCreation(new Date(System.currentTimeMillis() + 1000 * 60* 24))
                .build();

        catalogRepository.save(newCatalog);

        return GenerateCatalogResult.builder()
                .catalogId(newCatalog.getCatalogId())
                .items(newCatalog.getItems())
                .timestamp(newCatalog.getDateOfCreation())
                .build();
    }
}
