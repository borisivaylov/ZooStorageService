package com.example.zoostorageservice.core.addItem;

import com.example.zoostorageservice.api.Item.add.ShipmentRequest;
import com.example.zoostorageservice.api.Item.add.ShipmentResponse;
import com.example.zoostorageservice.api.Item.add.AddItemService;
import com.example.zoostorageservice.core.mappers.ShipmentMapperImpl;
import com.example.zoostorageservice.persistence.entity.Shipment;
import com.example.zoostorageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddItemOperation implements AddItemService {

    private final ShipmentRepository shipmentRepository;

    private final ShipmentMapperImpl itemMapper;

    @Override
    public ShipmentResponse process(ShipmentRequest itemRequest)
    {
        Shipment shipment = Shipment.builder()
                .itemId(itemMapper.itemGet(itemRequest).getItemId())
                .price(itemMapper.itemGet(itemRequest).getPrice())
                .quantity(itemMapper.itemGet(itemRequest).getQuantity())
                .build();

        shipmentRepository.save(shipment);

        return ShipmentResponse.builder()
                                            .shipmentId(shipment.getShipmentId())
                                            .id(itemRequest.getId())
                                            .price(itemRequest.getPrice())
                                            .quantity(itemRequest.getQuantity())
                                            .build();
    }
}
