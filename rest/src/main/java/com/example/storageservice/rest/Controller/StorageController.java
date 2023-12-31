package com.example.storageservice.rest.Controller;



import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.add.ShipmentResponse;
import com.example.storageservice.api.Item.changePrice.ChangePriceRequest;
import com.example.storageservice.api.Item.changePrice.ChangePriceResponse;
import com.example.storageservice.api.Item.export.ExportRequest;
import com.example.storageservice.api.Item.export.ExportResponse;
import com.example.storageservice.api.Item.getItem.ItemRequest;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.Item.getallitems.GetAllItemsRequest;
import com.example.storageservice.api.Item.getallitems.GetAllItemsResponse;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagRequest;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import com.example.storageservice.api.Item.importItem.ImportRequest;
import com.example.storageservice.api.Item.importItem.ImportResponse;
import com.example.storageservice.api.catalog.generate.GenerateCatalogInput;
import com.example.storageservice.api.catalog.generate.GenerateCatalogResult;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseResult;
import com.example.storageservice.core.catalog.additem.AddCatalogItemOperationProcessor;
import com.example.storageservice.core.catalog.generate.GenerateCatalogOperationProcessor;
import com.example.storageservice.core.item.addItem.AddItemOperationProcessor;
import com.example.storageservice.core.item.changePrice.ChangePriceOperationProcessor;
import com.example.storageservice.core.item.exportItem.ExportItemOperationProcessor;
import com.example.storageservice.core.item.getItem.GetItemOperationProcessor;
import com.example.storageservice.core.item.getallitems.GetAllItemsOperationProcessors;
import com.example.storageservice.core.item.getitembytag.GetItemByTagOperationProcessor;
import com.example.storageservice.core.item.importItem.ImportItemOperationProcessor;
import com.example.storageservice.core.purchase.PurchaseOperationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/zooStorage")
@RequiredArgsConstructor
public class StorageController {


    private final AddItemOperationProcessor addItemOperation;
    private final ImportItemOperationProcessor importItemService;
    private final ExportItemOperationProcessor exportItemService;
    private final ChangePriceOperationProcessor changePriceService;
    private final GetItemOperationProcessor getItemService;
    private final GetItemByTagOperationProcessor getItemByTagOperationProcessor;
    private final PurchaseOperationProcessor purchaseOperationProcessor;
    private  final GetAllItemsOperationProcessors getAllItemsOperationProcessors;


        @PostMapping("/createItem")
    ShipmentResponse addItem(@RequestBody ShipmentRequest shipmentRequest) throws Exception {

        return addItemOperation.process(shipmentRequest);
    }
   @PutMapping("/importItem")
   ImportResponse importItem(@RequestBody ImportRequest importRequest){
        return  importItemService.process(importRequest) ;
   }
    @PutMapping("/exportItem")
    ExportResponse exportItem(@RequestBody ExportRequest exportRequest){
        return exportItemService.process(exportRequest);
    }

    @PutMapping("/updatePrice")
    ChangePriceResponse changePrice(@RequestBody ChangePriceRequest changePriceRequest){
        return changePriceService.process(changePriceRequest);
    }
    @GetMapping("/{uuid}")
    ItemResponse getItemByItemId(@PathVariable UUID uuid){
        return  getItemService.process(ItemRequest.builder().id(uuid).build());
    }
    @GetMapping("byTag/{tagName}")
    List<GetItemByTagResponse> getItemByTag(@PathVariable String tagName) throws Exception {
        return  getItemByTagOperationProcessor.process(GetItemByTagRequest.builder().tagName(tagName).build());
    }

    @PostMapping("/purchase")
    StoragePurchaseResult purchaseResult(@RequestBody StoragePurchaseRequest purchaseRequest) throws Exception {
            return  purchaseOperationProcessor.process(purchaseRequest);
    }
    @GetMapping("/getAllItemsId/{string}")
    List<GetAllItemsResponse> getAllIds(@PathVariable String string) throws Exception {
            return getAllItemsOperationProcessors.process(GetAllItemsRequest.builder().testing1(string).build());
    }

}
