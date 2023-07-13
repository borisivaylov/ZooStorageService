package com.example.zoostorageservice.Bussiness.operations.addItem;

import com.example.zoostorageservice.API.Item.ItemRequest;
import com.example.zoostorageservice.Bussiness.operations.mappers.ItemMapperImpl;
import com.example.zoostorageservice.Data.Entity.Item;
import com.example.zoostorageservice.Data.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddItemService {

    private final ItemRepository itemRepository;

    private final ItemMapperImpl itemMapper;

    public Item createItem(ItemRequest itemRequest)
    {
        return itemRepository.save(itemMapper.itemGet(itemRequest));
    }

}
