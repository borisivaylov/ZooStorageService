package com.example.zoostorageservice.Controller;

import com.example.zoostorageservice.API.Item.ShipmentRequest;
import com.example.zoostorageservice.Bussiness.operations.addItem.AddItemService;
import com.example.zoostorageservice.Bussiness.operations.changePrice.ChangePriceRequest;
import com.example.zoostorageservice.Bussiness.operations.changePrice.ChangePriceService;
import com.example.zoostorageservice.Bussiness.operations.exportItem.ExportItemService;
import com.example.zoostorageservice.Bussiness.operations.exportItem.ExportRequest;
import com.example.zoostorageservice.Bussiness.operations.getItem.GetItemService;
import com.example.zoostorageservice.Bussiness.operations.getItem.ItemRequest;
import com.example.zoostorageservice.Bussiness.operations.importItem.ImportItemService;
import com.example.zoostorageservice.Bussiness.operations.importItem.ImportRequest;
import com.example.zoostorageservice.Data.Entity.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/zooStorage")
@RequiredArgsConstructor
public class StorageController {

    private final AddItemService addItemService;
    private final ImportItemService importItemService;
    private final ExportItemService exportItemService;
    private final ChangePriceService changePriceService;
    private final GetItemService getItemService;

    @PostMapping("/createItem")
    Shipment addItem(@RequestBody ShipmentRequest shipmentRequest){
        return addItemService.createItem(shipmentRequest);
    }
   @PutMapping("/importItem")
    Shipment importItem(@RequestBody ImportRequest importRequest){
        return  importItemService.importItem(importRequest) ;
   }
    @PutMapping("/exportItem")
    Shipment exportItem(@RequestBody ExportRequest exportRequest){
        return  exportItemService.exportItem(exportRequest);
    }

    @PutMapping("/updatePrice")
    Shipment changePrice(@RequestBody ChangePriceRequest changePriceRequest){
        return changePriceService.changePrice(changePriceRequest);
    }
    @GetMapping("getItem")
    Set<Shipment> getAllByItemId(@RequestBody ItemRequest itemRequest){
        return  getItemService.getItem(itemRequest);
    }

}
