package com.example.zoostorageservice.rest.Controller;

import com.example.zoostorageservice.API.Item.item.add.ShipmentRequest;
import com.example.zoostorageservice.API.Item.item.add.ShipmentResponse;
import com.example.zoostorageservice.API.Item.item.getItem.ItemResponse;
import com.example.zoostorageservice.core.addItem.AddItemOperation;
import com.example.zoostorageservice.API.Item.item.changePrice.ChangePriceRequest;
import com.example.zoostorageservice.API.Item.item.changePrice.ChangePriceResponse;
import com.example.zoostorageservice.core.changePrice.ChangePriceOperation;
import com.example.zoostorageservice.core.exportItem.ExportItemOperation;
import com.example.zoostorageservice.API.Item.item.export.ExportRequest;
import com.example.zoostorageservice.API.Item.item.export.ExportResponse;
import com.example.zoostorageservice.core.getItem.GetItemOperation;
import com.example.zoostorageservice.core.importItem.ImportItemOperation;
import com.example.zoostorageservice.API.Item.item.importItem.ImportRequest;
import com.example.zoostorageservice.API.Item.item.importItem.ImportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zooStorage")
@RequiredArgsConstructor
public class StorageController {

    private final AddItemOperation addItemService;
    private final ImportItemOperation importItemService;
    private final ExportItemOperation exportItemService;
    private final ChangePriceOperation changePriceService;
    private final GetItemOperation getItemService;

    @PostMapping("/createItem")
    ShipmentResponse addItem(@RequestBody ShipmentRequest shipmentRequest){

        return addItemService.process(shipmentRequest);
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
    @GetMapping("getItem")
    ItemResponse getAllByItemId(@RequestBody ShipmentRequest itemRequest){

        return  getItemService.process(itemRequest);
    }

}
