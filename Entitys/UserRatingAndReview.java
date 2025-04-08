package com.gdc.Rating_Review_System.Entitys;
import com.gdc.Rating_Review_System.Entitys.User;
import com.gdc.Rating_Review_System.Entitys.Ride;
import com.gdc.Rating_Review_System.Enum.ReviewStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity for reviews and ratings given by users (goods senders) to drivers after a ride.
 */
@Entity
@Table(name = "user_ratings_reviews", schema = "gdc_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRatingAndReview {

    // Unique ID for the review (UUID)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // The ride that the review is associated with
    @ManyToOne
    @JoinColumn(
            name = "ride_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_rating_ride")
    )
    private Ride ride;

    // The user who gave the review (reviewer)
    @ManyToOne
    @JoinColumn(
            name = "reviewer_user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_rating_reviewer")
    )
    private User reviewerUser;

    // The driver who received the review (reviewed)
    @ManyToOne
    @JoinColumn(
            name = "reviewed_user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_rating_reviewed_user")
    )
    private User reviewedUser;

    // Rating given by the user (1 to 5)
    @Column(name = "rating", nullable = false)
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    // Optional text review
    @Column(name = "review")
    private String review;

    // Review status (default is PENDING)
    @Enumerated(EnumType.STRING)
    @Column(name = "review_status", nullable = false)
    private ReviewStatus reviewStatus;

    // Created timestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Updated timestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Set creation and update time on insert
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;

        // Default status
        if (this.reviewStatus == null) {
            this.reviewStatus = ReviewStatus.PENDING;
        }
    }

    // Update timestamp before modifying record
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
