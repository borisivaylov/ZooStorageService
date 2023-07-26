package com.example.storageservice.rest.Controller;



import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.add.ShipmentResponse;
import com.example.storageservice.api.Item.changePrice.ChangePriceRequest;
import com.example.storageservice.api.Item.changePrice.ChangePriceResponse;
import com.example.storageservice.api.Item.export.ExportRequest;
import com.example.storageservice.api.Item.export.ExportResponse;
import com.example.storageservice.api.Item.getItem.ItemRequest;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.Item.importItem.ImportRequest;
import com.example.storageservice.api.Item.importItem.ImportResponse;
import com.example.storageservice.core.addItem.AddItemOperation;
import com.example.storageservice.core.changePrice.ChangePriceOperation;
import com.example.storageservice.core.exportItem.ExportItemOperation;
import com.example.storageservice.core.getItem.GetItemOperation;
import com.example.storageservice.core.importItem.ImportItemOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/zooStorage")
@RequiredArgsConstructor
public class StorageController {


    private final AddItemOperation addItemOperation;
    private final ImportItemOperation importItemService;
    private final ExportItemOperation exportItemService;
    private final ChangePriceOperation changePriceService;
    private final GetItemOperation getItemService;

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
        return  getItemService.process(ShipmentRequest.builder().id(uuid).build());
    }

}
