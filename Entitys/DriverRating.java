package com.gdc.Rating_Review_System.Entitys;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This entity represents the rating a driver gives to a user (goods sender) after completing a ride.
 */
@Entity
@Table(name = "driver_ratings", schema = "gdc_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRating {

    // Unique identifier for each driver rating entry
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Ride for which this rating was given
    @ManyToOne
    @JoinColumn(
            name = "ride_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_driver_rating_ride_id")
    )
    private Ride ride;

    // Driver who gave the rating
    @ManyToOne
    @JoinColumn(
            name = "reviewer_user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_driver_rating_reviewer_user_id")
    )
    private User reviewerUser;

    // User (goods sender) who was rated
    @ManyToOne
    @JoinColumn(
            name = "reviewed_user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_driver_rating_reviewed_user_id")
    )
    private User reviewedUser;

    // Rating value between 1 (worst) to 5 (best)
    @Column(name = "rating", nullable = false)
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    // Timestamp when this rating was created
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Timestamp when this rating was last updated
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Set timestamps before insert
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    // Update timestamp before update
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
