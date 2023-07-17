package com.example.zoostorageservice.core.mappers;

import com.example.zoostorageservice.API.Item.item.add.ShipmentRequest;
import com.example.zoostorageservice.API.Item.item.add.ShipmentResponse;
import com.example.zoostorageservice.persistance.entity.Shipment;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapperImpl implements ShipmentMapper
{
    public Shipment itemGet(ShipmentRequest shipmentRequest)
    {
        if (shipmentRequest == null)
        {
            return null;
        }

        return Shipment.builder()
                            .shipmentId(shipmentRequest.getShipmentId())
                            .itemId(shipmentRequest.getId())
                            .price(shipmentRequest.getPrice())
                            .quantity(shipmentRequest.getQuantity())
                            .build();
    }

    public ShipmentResponse itemPost(Shipment shipment)
    {
        if (shipment == null)
        {
            return null;
        }

        return ShipmentResponse.builder()
                                    .shipmentId(shipment.getShipmentId())
                                    .id(shipment.getItemId())
                                    .price(shipment.getPrice())
                                    .quantity(shipment.getQuantity())
                                    .build();
    }
}
