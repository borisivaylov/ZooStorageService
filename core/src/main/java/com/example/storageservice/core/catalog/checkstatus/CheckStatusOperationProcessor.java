package com.example.storageservice.core.catalog.checkstatus;

import com.example.storageservice.api.catalog.checkcatalogstatus.CheckCatalogStatusInput;
import com.example.storageservice.api.catalog.checkcatalogstatus.CheckCatalogStatusOperation;
import com.example.storageservice.api.catalog.checkcatalogstatus.CheckCatalogStatusResult;
import com.example.storageservice.persistence.entity.Catalog;
import com.example.storageservice.persistence.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CheckStatusOperationProcessor implements CheckCatalogStatusOperation {

   private  final  CatalogRepository catalogRepository;
    @Override
    public CheckCatalogStatusResult process(CheckCatalogStatusInput operationInput) {

        Catalog currentCatalog = catalogRepository.findById(operationInput.getCatalogId())
                .orElseThrow(()-> new NoSuchElementException("No such catalog"));

        LocalDateTime catalogExpirationDate = currentCatalog.getDateOfExpiration().toLocalDateTime();
        LocalDateTime presentTime = LocalDateTime.now();
        boolean status = !presentTime.isBefore(catalogExpirationDate);

        currentCatalog.setExpired(status);

        catalogRepository.save(currentCatalog);

        return CheckCatalogStatusResult.builder().catalogStatus(status).build();
    }
}
