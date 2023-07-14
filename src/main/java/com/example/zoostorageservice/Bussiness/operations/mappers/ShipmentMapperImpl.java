package com.example.zoostorageservice.Bussiness.operations.mappers;

import com.example.zoostorageservice.API.Item.ShipmentRequest;
import com.example.zoostorageservice.API.Item.ShipmentResponse;
import com.example.zoostorageservice.Data.Entity.Shipment;
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
