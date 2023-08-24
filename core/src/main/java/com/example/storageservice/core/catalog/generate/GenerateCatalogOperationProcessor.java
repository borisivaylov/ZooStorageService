package com.example.storageservice.core.catalog.generate;

import com.example.storageservice.api.catalog.additem.CatalogItemInput;
import com.example.storageservice.api.catalog.additem.CatalogItemResult;
import com.example.storageservice.api.catalog.expirationdate.ExpirationDateInput;
import com.example.storageservice.api.catalog.generate.GenerateCatalogInput;
import com.example.storageservice.api.catalog.generate.GenerateCatalogOperation;
import com.example.storageservice.api.catalog.generate.GenerateCatalogResult;
import com.example.storageservice.core.catalog.additem.AddCatalogItemOperationProcessor;
import com.example.storageservice.core.catalog.expirationdate.ExpirationDateOperationProcessor;
import com.example.storageservice.persistence.entity.Catalog;
import com.example.storageservice.persistence.entity.OnSaleItem;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.CatalogRepository;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GenerateCatalogOperationProcessor implements GenerateCatalogOperation {

    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;
    private final CatalogRepository catalogRepository;
    private final AddCatalogItemOperationProcessor addCatalogItemOperationProcessor;
    private final ExpirationDateOperationProcessor expirationDateOperationProcessor;

    @Override
    public GenerateCatalogResult process(GenerateCatalogInput operationInput) {

        List<Shipment> items = shipmentRepository.findAll();
        List<UUID> onSaleItemIds = new ArrayList<>();


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
                .dateOfCreation(new Timestamp(System.currentTimeMillis()))
                .expired(false)
                .build();


        ExpirationDateInput expirationDateInput = ExpirationDateInput.builder()
                                                        .dateOfCreation(newCatalog.getDateOfCreation())
                                                        .build();

        newCatalog.setDateOfExpiration(expirationDateOperationProcessor
                .process(expirationDateInput).getDateOfExpiration());



        catalogRepository.save(newCatalog);

        newCatalog.getItems().forEach( item ->{

            OnSaleItem onSaleItem = onSaleItemRepository.findByItemId(item)
                    .orElseThrow(()-> new NoSuchElementException("No such item") );



            onSaleItemRepository.save(OnSaleItem.builder()
                    .catalogId(newCatalog.getCatalogId())
                    .itemId(onSaleItem.getItemId())
                    .discount(onSaleItem.getDiscount())
                    .build());
        });

        return GenerateCatalogResult.builder()
                .catalogId(newCatalog.getCatalogId())
                .items(newCatalog.getItems())
                .dateOfCreation(new Timestamp(System.currentTimeMillis()))
                .dateOfExpiration(newCatalog.getDateOfExpiration())
                .build();
    }
}
