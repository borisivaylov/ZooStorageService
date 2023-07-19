package com.example.zoostorageservice.api.Item.importItem;

import com.example.zoostorageservice.api.base.OperationProcessor;
import org.springframework.stereotype.Service;

@Service
public interface ImportItemService extends OperationProcessor<ImportRequest, ImportResponse> {
    ImportResponse process(ImportRequest importRequest);
}
