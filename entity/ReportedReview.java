package com.gdc.Rating_Review_System.entity;

import com.gdc.Rating_Review_System.Enum.ReportStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reported_reviews" , schema = "ratingandreviewdata") // Defines the table name in the database
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Getter
@Setter
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
public class ReportedReview {

    @Id
    @Column(name = "report_id", length = 50, nullable = false) // Unique identifier for the report
    private String reportId;

    @Column(name = "review_id", length = 50, nullable = false) // ID of the reported review
    private String reviewId;

    @Column(name = "reported_by", length = 50, nullable = false) // ID of the user who reported the review
    private String reportedBy;

    @Column(name = "reason", nullable = false, columnDefinition = "TEXT") // Reason for reporting the review
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status", nullable = false) // Status of the report
    private ReportStatus reportStatus = ReportStatus.PENDING;

    @Column(name = "reported_at", nullable = false, updatable = false) // Timestamp when the review was reported
    private LocalDateTime reportedAt;

    @Column(name = "resolved_at") // Timestamp when the report was resolved
    private LocalDateTime resolvedAt;

    @PrePersist
    protected void onCreate() {
        this.reportedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.reportStatus == ReportStatus.RESOLVED) {
            this.resolvedAt = LocalDateTime.now();
        }
    }
}
