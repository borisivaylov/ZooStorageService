package com.example.zoostorageservice.core.changePrice;

import com.example.zoostorageservice.api.Item.changePrice.ChangePriceRequest;
import com.example.zoostorageservice.api.Item.changePrice.ChangePriceResponse;
import com.example.zoostorageservice.api.Item.changePrice.ChangePriceService;
import com.example.zoostorageservice.persistence.entity.Shipment;
import com.example.zoostorageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePriceOperation implements ChangePriceService {

    private final ShipmentRepository shipmentRepository;

   public ChangePriceResponse process(ChangePriceRequest changePriceRequest){
            Shipment shipment = shipmentRepository.findShipmentByItemId(changePriceRequest.getId());
            shipment.setPrice(changePriceRequest.getPrice());

            ChangePriceResponse changePriceResponse = ChangePriceResponse.builder()
                    .id(changePriceRequest.getId())
                    .price(changePriceRequest.getPrice())
                    .build();
                    shipmentRepository.save(shipment);

            return changePriceResponse;
    }

}


