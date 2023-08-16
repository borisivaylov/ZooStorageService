package com.example.storageservice.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OnSaleItems")
public class OnSaleItem {
    @Id
    private UUID itemId;
    private Integer discount;

}
