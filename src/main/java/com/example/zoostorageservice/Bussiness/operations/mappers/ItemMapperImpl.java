package com.example.zoostorageservice.Bussiness.operations.mappers;

import com.example.zoostorageservice.API.Item.ItemRequest;
import com.example.zoostorageservice.API.Item.ItemResponse;
import com.example.zoostorageservice.Data.Entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapperImpl implements ItemMapper
{
    public Item itemGet(ItemRequest itemRequest)
    {
        if (itemRequest == null)
        {
            return null;
        }

        return Item.builder()
                            .Id(itemRequest.getId())
                            .price(itemRequest.getPrice())
                            .quantity(itemRequest.getQuantity())
                            .build();
    }

    public ItemResponse itemPost(Item item)
    {
        if (item == null)
        {
            return null;
        }

        return ItemResponse.builder()
                                    .id(item.getId())
                                    .price(item.getPrice())
                                    .quantity(item.getQuantity())
                                    .build();
    }
}
