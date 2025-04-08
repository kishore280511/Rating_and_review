package com.gdc.Rating_Review_System.Entitys;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This entity stores the overall aggregated rating data for a driver,
 * based on ratings received from users (goods senders).
 *
 * The driver is represented as a User with role DRIVER.
 */
@Entity
@Table(name = "driver_aggregated_ratings", schema = "gdc_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverAggregatedRating {

    // Primary key - UUID
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * The driver whose aggregated rating is stored.
     * This is a user with the role DRIVER.
     */
    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_driver_aggregated_rating_driver_id"))
    private User driver;

    // Total number of ratings received by the driver
    @Column(name = "total_ratings_count", nullable = false)
    private int totalRatingsCount;

    // Average rating score (e.g., 4.8)
    @Column(name = "average_rating", nullable = false)
    private double averageRating;

    // Record creation timestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Last update timestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Automatically set timestamps before inserting a new record
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    // Automatically update timestamps before updating the record
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


