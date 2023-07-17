package com.example.zoostorageservice.core.getItem;

import com.example.zoostorageservice.API.Item.item.getItem.GetItemService;
import com.example.zoostorageservice.API.Item.item.getItem.ItemRequest;
import com.example.zoostorageservice.API.Item.item.getItem.ItemResponse;
import com.example.zoostorageservice.persistance.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetItemServiceImpl implements GetItemService {

    private final ShipmentRepository shipmentRepository;

    public Set<ItemResponse> process(ItemRequest itemRequest){


        return shipmentRepository.findShipmentByItemId(itemRequest.getId());
    }

}
