package com.gdc.Rating_Review_System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "driver_ratings" , schema = "ratingandreviewdata")
@Data // Includes @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DriverRating {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rating_id", updatable = false, nullable = false)
    private UUID ratingId; // Use UUID for unique rating IDs

    @Column(name = "reviewer_id", nullable = false, length = 50)
    private String reviewerId;  // Driver giving the rating

    @Column(name = "reviewed_user_id", nullable = false, length = 50)
    private String reviewedUserId;  // User being rated

    @Column(name = "ride_id", nullable = false, length = 50)
    private String rideId;

    @Column(name = "rating", nullable = false)
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Double rating;
}
