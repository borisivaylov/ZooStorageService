package com.example.zoostorageservice.core.mappers;

import com.example.zoostorageservice.api.Item.add.ShipmentRequest;
import com.example.zoostorageservice.api.Item.add.ShipmentResponse;
import com.example.zoostorageservice.persistence.entity.Shipment;
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
                                    .id(shipment.getItemId())
                                    .price(shipment.getPrice())
                                    .quantity(shipment.getQuantity())
                                    .build();
    }
}
