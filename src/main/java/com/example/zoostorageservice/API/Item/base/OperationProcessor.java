package com.example.zoostorageservice.API.Item.base;

public interface OperationProcessor<I extends OperationInput, T extends OperationResult> {
     T process(I operationInput);

}
