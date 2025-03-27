package com.gdc.Rating_Review_System.entity;
import com.gdc.Rating_Review_System.Enum.ReviewStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "UserRatingAndReview" , schema = "ratingandreviewdata")
@Data // Includes @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingAndReview {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id", updatable = false, nullable = false)
    private UUID reviewId; // Use UUID type instead of String

    @Column(name = "reviewer_id", nullable = false, length = 50)
    private String reviewerId;  // User giving the rating

    @Column(name = "reviewed_user_id", nullable = false, length = 50)
    private String reviewedUserId;  // Driver being reviewed

    @Column(name = "ride_id", nullable = false, length = 50)
    private String rideId;

    @Column(name = "rating", nullable = false)
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Double rating;

    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText; // Allows long review text

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReviewStatus status = ReviewStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

