package com.gdc.Rating_Review_System.Enum;

/**
 * Status of a reported review.
 */
public enum ReportedReviewStatus {

    // Newly reported, not yet reviewed
    PENDING,

    // Currently under review
    UNDER_REVIEW,

    // Report approved and action taken
    APPROVED,

    // Report rejected
    REJECTED,

    // Review deleted
    DELETED
}
