package com.gdc.Rating_Review_System.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "DriverAggregatedRating" , schema = "ratingandreviewdata") // Defines the table name in the database
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Getter
@Setter
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
public class DriverAggregatedRating {

    @Id
    @Column(name = "driver_id") // Primary key representing the driver
    private String driverId;

    @Column(name = "average_rating", nullable = false) // Stores the average rating for the driver
    private Double averageRating = 0.0; // Default value set to 0.0 to prevent null issues

    @Column(name = "total_reviews", nullable = false) // Stores the total number of reviews received
    @Min(value = 0, message = "Total reviews must be at least 0") // Ensures totalReviews is never negative
    private Integer totalReviews = 0; // Default value set to 0 to avoid null issues
}

