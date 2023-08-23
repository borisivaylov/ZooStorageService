package com.example.storageservice.api.catalog.expirationdate;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpirationDateInput implements OperationInput {

    @JsonProperty
    Timestamp dateOfCreation;
}
