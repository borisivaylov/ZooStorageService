package com.example.storageservice.persistence.entity;

import com.example.bffshare.persistence.entity.Cart;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID purchaseId;
    private UUID userId;
    private UUID cartId;
    @ElementCollection
    private Map<UUID,Integer> cart;
    @CreationTimestamp
    private Instant dateOfPurchase;
    private double sumPrice;
}
