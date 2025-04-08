package com.gdc.Rating_Review_System.Entitys;
import com.gdc.Rating_Review_System.Entitys.Driver;
import com.gdc.Rating_Review_System.Enum.RideStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a ride offered by a driver.
 */
@Entity
@Table(name = "rides", schema = "gdc_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    // Unique identifier for the ride
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Many rides can be offered by one driver
    @ManyToOne
    @JoinColumn(
            name = "driver_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_ride_driver_id")
    )
    private Driver driver;

    // Location from where the ride starts
    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    // Destination location for the ride
    @Column(name = "destination_location", nullable = false)
    private String destinationLocation;

    // Time at which the ride is scheduled to start
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    // Number of available spaces for goods in the ride
    @Column(name = "available_space", nullable = false)
    private Integer availableSpace;

    // Current status of the ride (enum: AVAILABLE, IN_PROGRESS, etc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RideStatus status;

    // Timestamp when the ride entry was created
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Timestamp when the ride entry was last updated
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Set timestamps before saving new record
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    // Update timestamp before modifying record
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}





