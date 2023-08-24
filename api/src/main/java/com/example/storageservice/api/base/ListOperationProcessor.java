package com.example.storageservice.api.base;

import java.util.List;

public interface ListOperationProcessor <I extends OperationInput, T extends OperationResult> {
    List<T> process(I operationInput);
}
