package com.example.zoostorageservice.Controller;

import com.example.zoostorageservice.API.Item.ItemRequest;
import com.example.zoostorageservice.Bussiness.operations.addItem.AddItemService;
import com.example.zoostorageservice.Data.Entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zooStorage")
@RequiredArgsConstructor
public class StorageController {
    private final AddItemService addItemService;

    @PostMapping("/createItem")
    Item addItem(@RequestBody ItemRequest itemRequest){
        return addItemService.createItem(itemRequest);
    }
    @PostMapping("/importItem")

    @PutMapping("/setPrice")

    @PutMapping("changePrice")
}
