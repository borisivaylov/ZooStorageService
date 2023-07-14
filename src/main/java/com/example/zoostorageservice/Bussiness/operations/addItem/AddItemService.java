package com.example.zoostorageservice.Bussiness.operations.addItem;

import com.example.zoostorageservice.API.Item.ShipmentRequest;
import com.example.zoostorageservice.Bussiness.operations.mappers.ShipmentMapperImpl;
import com.example.zoostorageservice.Data.Entity.Shipment;
import com.example.zoostorageservice.Data.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddItemService {

    private final ShipmentRepository itemRepository;

    private final ShipmentMapperImpl itemMapper;

    public Shipment createItem(ShipmentRequest itemRequest)
    {
        return itemRepository.save(itemMapper.itemGet(itemRequest));
    }

}
