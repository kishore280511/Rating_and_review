package com.gdc.Rating_Review_System.Entitys;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This entity stores the aggregated rating summary for a user (goods sender),
 * based on individual ratings given by drivers.
 */
@Entity
@Table(name = "user_aggregated_ratings", schema = "gdc_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAggregatedRating {

    // Primary key - Unique identifier for the aggregated rating record
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // The user (goods sender) for whom this aggregate rating is maintained
    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_user_aggregated_rating_user_id")
    )
    private User user;

    // Total number of ratings received from drivers
    @Column(name = "total_ratings_count", nullable = false)
    private int totalRatingsCount;

    // Calculated average rating (e.g., 4.3)
    @Column(name = "average_rating", nullable = false)
    private double averageRating;

    // Timestamp when the record was created
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Timestamp when the record was last updated
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Lifecycle callback to set timestamps on insert
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    // Lifecycle callback to update timestamp on update
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}



