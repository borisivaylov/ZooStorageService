package com.example.storageservice.rest.Controller;

import com.example.storageservice.api.catalog.generate.GenerateCatalogInput;
import com.example.storageservice.api.catalog.generate.GenerateCatalogResult;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveCatalogsOperationProcessor;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveResult;
import com.example.storageservice.api.catalog.view.GetCatalogInput;
import com.example.storageservice.api.catalog.view.GetCatalogResult;
import com.example.storageservice.core.catalog.generate.GenerateCatalogOperationProcessor;
import com.example.storageservice.core.catalog.get.GetCatalogOperationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final GenerateCatalogOperationProcessor generateCatalogOperationProcessor;
    private final GetCatalogOperationProcessor viewCatalogOperationProcessor;
    private final GetAllActiveCatalogsOperationProcessor getAllActiveCatalogsOperationProcessor;

    @PostMapping("/generate")
    GenerateCatalogResult generateCatalog(@RequestBody GenerateCatalogInput generateCatalogInput) throws Exception {
        return generateCatalogOperationProcessor.process(generateCatalogInput);
    }

    @GetMapping("/{catalogId}")
    GetCatalogResult viewCatalog(@PathVariable UUID catalogId) throws Exception {
        return viewCatalogOperationProcessor.process(GetCatalogInput.builder().catalogId(catalogId).build());
    }
    @PostMapping("/allActive")
    List<GetAllActiveResult> getAllActiveResultList(@RequestBody GetAllActiveInput  getAllActiveInput) throws Exception {
        return  getAllActiveCatalogsOperationProcessor.process(getAllActiveInput);
    }
}
