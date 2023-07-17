package com.example.zoostorageservice.core.addItem;

import com.example.zoostorageservice.API.Item.base.OperationInput;
import com.example.zoostorageservice.API.Item.base.OperationResult;
import com.example.zoostorageservice.API.Item.item.add.ShipmentRequest;
import com.example.zoostorageservice.API.Item.item.add.ShipmentResponse;
import com.example.zoostorageservice.API.Item.item.add.AddItemService;
import com.example.zoostorageservice.core.mappers.ShipmentMapperImpl;
import com.example.zoostorageservice.persistance.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddItemServiceImpl implements AddItemService {

    private final ShipmentRepository itemRepository;

    private final ShipmentMapperImpl itemMapper;

    @Override
    public ShipmentResponse process(ShipmentRequest itemRequest)
    {
        itemRepository.save(itemMapper.itemGet(itemRequest));

        ShipmentResponse shipmentResponse = ShipmentResponse.builder()
                                            .id(itemRequest.getId())
                                            .price(itemRequest.getPrice())
                                            .quantity(itemRequest.getQuantity())
                                            .build();

        return shipmentResponse;
    }
}
