package com.gdc.Rating_Review_System.Entitys;
import com.gdc.Rating_Review_System.Entitys.User;
import com.gdc.Rating_Review_System.Enum.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Driver entity representing a user who is registered as a driver.
 * Each driver is associated with a unique user profile.
 */
@Entity
@Table(name = "drivers", schema = "gdc_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    // Primary key using UUID for uniqueness across distributed systems
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // One-to-One mapping with the User entity
    // Ensures that each driver is linked to a unique user
    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_driver_user") // Naming the foreign key constraint
    )
    private User user;

    // Enum representing the type of vehicle the driver uses (e.g., TWO_WHEELER, TRUCK)
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    // License number of the driver - must be unique
    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    // Vehicle number (registration plate) - must be unique
    @Column(name = "vehicle_number", nullable = false, unique = true)
    private String vehicleNumber;

    // Maximum capacity of the vehicle (in kilograms or tons)
    @Column(name = "vehicle_capacity", nullable = false)
    private Double vehicleCapacity;

    // Timestamp for when the driver record was created
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    // Timestamp for when the driver record was last updated
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    // Automatically sets timestamps on creation
    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createdAt = now;
        this.updatedAt = now;
    }

    // Automatically updates the timestamp when the record is updated
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}

