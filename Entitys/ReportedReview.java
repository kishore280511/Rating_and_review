package com.gdc.Rating_Review_System.Entitys;
import com.gdc.Rating_Review_System.Enum.ReportedReviewStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity to represent a reported review.
 * A review can either be for a user or a driver. This entity allows users to report inappropriate reviews.
 */
@Entity
@Table(name = "reported_reviews", schema = "gdc_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportedReview {

    // Primary key (UUID)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Reference to the user review being reported (optional).
     * Either this or driverRating should be set, not both.
     */
    @ManyToOne
    @JoinColumn(name = "user_rating_review_id",
            foreignKey = @ForeignKey(name = "fk_reported_user_review"))
    private UserRatingAndReview userRatingAndReview;

    /**
     * Reference to the driver review being reported (optional).
     * Either this or userRatingAndReview should be set, not both.
     */
    @ManyToOne
    @JoinColumn(name = "driver_rating_id",
            foreignKey = @ForeignKey(name = "fk_reported_driver_review"))
    private DriverRating driverRating;

    /**
     * The user who reported the review.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "reported_by_user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_reported_review_reported_by_user"))
    private User reportedByUser;

    /**
     * Reason provided by the user for reporting.
     */
    @Column(name = "reason", nullable = false)
    private String reason;

    /**
     * Status of the reported review.
     * Defaults to PENDING on creation.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReportedReviewStatus status;

    /**
     * Timestamp of when the report was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp of the last update to this report.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Sets timestamps and default status before persisting.
     */
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.status == null) {
            this.status = ReportedReviewStatus.PENDING;
        }
        validateReviewReference();
    }

    /**
     * Updates the timestamp and validates the review reference before update.
     */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        validateReviewReference();
    }

    /**
     * Validation to ensure only one of userRatingAndReview or driverRating is set.
     */
    private void validateReviewReference() {
        if ((userRatingAndReview == null && driverRating == null) ||
                (userRatingAndReview != null && driverRating != null)) {
            throw new IllegalStateException("Exactly one of userRatingAndReview or driverRating must be set.");
        }
    }
}