package com.gdc.Rating_Review_System.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "UserAggregatedRating" , schema = "ratingandreviewdata") // Defines the table name in the database
@Data// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Getter
@Setter
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
public class UserAggregatedRating {

    @Id
    @Column(name = "user_id") // Primary key for the user rating table
    private String userId;

    @Column(name = "average_rating", nullable = false) // Stores the average rating for the user
    private Double averageRating = 0.0; // Default value set to 0.0 to avoid null issues

    @Column(name = "total_reviews", nullable = false) // Stores the total number of reviews received
    @Min(value = 0, message = "Total reviews must be at least 0") // Ensures totalReviews is never negative
    private Integer totalReviews = 0; // Default value set to 0 to avoid null issues
}

