package com.example.storageservice.core.addItem;


import com.example.storageservice.api.Item.add.AddItemService;
import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.Item.add.ShipmentResponse;


import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AddItemOperation implements AddItemService {

    private final ShipmentRepository shipmentRepository;
    private  final ZooStoreRestExport zooStoreRestExport;




    @Override
    public ShipmentResponse process(ShipmentRequest shipmentRequest) throws Exception {

        try {
            //System.out.println(shipmentRequest.getId().toString());
            zooStoreRestExport.getItemById(shipmentRequest.getId().toString());
        } catch (Exception e)
        {
            throw new Exception("item not found");
        }

        Shipment shipment = Shipment.builder().itemId(shipmentRequest.getId()).
                price(shipmentRequest.getPrice()).
                quantity(shipmentRequest.getQuantity())
                .build();

        shipmentRepository.save(shipment);

        return ShipmentResponse.builder()
                                            .shipmentId(shipment.getShipmentId())
                                            .id(shipment.getItemId())
                                            .price(shipment.getPrice())
                                            .quantity(shipment.getQuantity())
                                            .build();
    }
}