package com.example.storageservice.core.catalog.get;

import com.example.storageservice.api.catalog.checkcatalogstatus.CheckCatalogStatusInput;
import com.example.storageservice.api.catalog.view.GetCatalogInput;
import com.example.storageservice.api.catalog.view.GetCatalogItem;
import com.example.storageservice.api.catalog.view.GetCatalogOperation;
import com.example.storageservice.api.catalog.view.GetCatalogResult;
import com.example.storageservice.core.catalog.checkstatus.CheckStatusOperationProcessor;
import com.example.storageservice.core.item.getItem.GetItemOperationProcessor;
import com.example.storageservice.persistence.entity.Catalog;
import com.example.storageservice.persistence.repository.CatalogRepository;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import com.example.zoostoreproject.api.Item.getItem.GetItemResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

 // Display all available information about a catalog by id

@Service
@RequiredArgsConstructor
public class GetCatalogOperationProcessor implements GetCatalogOperation {

    private final CatalogRepository catalogRepository;
    private final ZooStoreRestExport zooStoreRestExport;
    private final GetItemOperationProcessor getItemOperationProcessor;
    private final OnSaleItemRepository onSaleItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final CheckStatusOperationProcessor checkStatusOperationProcessor;
    @Override
    public GetCatalogResult process(GetCatalogInput operationInput)  {

        Catalog currentCatalog = catalogRepository.findById(operationInput.getCatalogId())
                .orElseThrow(() -> new NoSuchElementException("No such catalog"));
        List<GetCatalogItem> viewCatalogItemList = new ArrayList<>();

        currentCatalog.getItems().forEach(item ->{

            double actualPrice = shipmentRepository.findShipmentByItemId(item)
                    .orElseThrow(()-> new NoSuchElementException("No such item.")).getPrice();
            Integer discount = onSaleItemRepository.findByItemId(item)
                    .orElseThrow(()-> new NoSuchElementException("There is no such item in the catalog")).getDiscount();

            GetItemResponse currentItem =zooStoreRestExport.getItemById(item.toString());


            GetCatalogItem viewCatalogItem = GetCatalogItem.builder()
                                    .itemId(currentItem.getId())
                                    .title(currentItem.getTitle())
                                    .description(currentItem.getDescription())
                                    .actualPrice(actualPrice)
                                    .discount(discount)
                                    .onSalePrice(actualPrice*(1-discount/100))
                                    .build();

            viewCatalogItemList.add(viewCatalogItem);
        });


        checkStatusOperationProcessor.process(CheckCatalogStatusInput.builder()
                .catalogId(currentCatalog.getCatalogId()).build());

        return GetCatalogResult.builder()
                                    .catalogId(currentCatalog.getCatalogId())
                                    .catalogItems(viewCatalogItemList)
                                    .dateOfCreation(currentCatalog.getDateOfCreation())
                                    .dateOfExpiration(currentCatalog.getDateOfExpiration())
                                    .status(currentCatalog.isExpired())
                                    .build();
    }
}
