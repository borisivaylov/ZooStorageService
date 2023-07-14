package com.example.zoostorageservice.Bussiness.operations.exportItem;

import com.example.zoostorageservice.Data.Entity.Shipment;
import com.example.zoostorageservice.Data.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportItemService {

    private final ShipmentRepository shipmentRepository;

    public Shipment exportItem(ExportRequest exportRequest){

        Shipment shipment= shipmentRepository.findById(exportRequest.getId()).orElseThrow(()
            -> new IllegalArgumentException("There is no item with id:"+exportRequest.getId()));
            shipment.setQuantity(shipment.getQuantity() - exportRequest.getQuantity());
            return shipmentRepository.save(shipment);
    }
}
