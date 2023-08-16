package com.example.storageservice.core.catalog.view;

import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.getItem.ItemRequest;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.catalog.view.ViewCatalogInput;
import com.example.storageservice.api.catalog.view.ViewCatalogItem;
import com.example.storageservice.api.catalog.view.ViewCatalogOperation;
import com.example.storageservice.api.catalog.view.ViewCatalogResult;
import com.example.storageservice.core.item.getItem.GetItemOperationProcessor;
import com.example.storageservice.persistence.entity.Catalog;
import com.example.storageservice.persistence.repository.CatalogRepository;
import com.example.zoostoreproject.api.Item.getItem.GetItemResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetCatalogOperationProcessor implements ViewCatalogOperation {

    private final CatalogRepository catalogRepository;
    private final ZooStoreRestExport zooStoreRestExport;
    private final GetItemOperationProcessor getItemOperationProcessor;
    @Override
    public ViewCatalogResult process(ViewCatalogInput operationInput) throws Exception {

        Catalog currentCatalog = catalogRepository.findById(operationInput.getCatalogId())
                .orElseThrow(() -> new NoSuchElementException("No such catalog"));

        ViewCatalogResult currentCatalogView =ViewCatalogResult.builder()
                .catalogId(currentCatalog.getCatalogId())
                .timestamp(currentCatalog.getDateOfCreation())
                .build();

        currentCatalog.getItems().forEach(item ->{

            GetItemResponse currentItem =zooStoreRestExport.getItemById(item.toString());
            ItemResponse itemResponseStorage = getItemOperationProcessor.process(ItemRequest.builder().id(item).build());


            ViewCatalogItem viewCatalogItem = ViewCatalogItem.builder()
                            .itemId(currentItem.getId())
                                    .title(currentItem.getTitle())
                                            .description(currentItem.getDescription())
                                                    .price(itemResponseStorage.getPrice())
                    .build();
        });

        return null;
    }
}
