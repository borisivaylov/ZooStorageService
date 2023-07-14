package com.example.zoostorageservice.Bussiness.operations.changePrice;

import com.example.zoostorageservice.Data.Entity.Shipment;
import com.example.zoostorageservice.Data.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePriceService {

    private final ShipmentRepository shipmentRepository;

   public Shipment changePrice(ChangePriceRequest changePriceRequest){
        Shipment shipment = shipmentRepository.findById(changePriceRequest.getId()).orElseThrow(()
            -> new IllegalArgumentException("There is no item with id:"+changePriceRequest.getId()));
            shipment.setPrice(changePriceRequest.getPrice());
            return shipmentRepository.save(shipment);
    }
}


