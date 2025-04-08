package com.gdc.Rating_Review_System.Enum;
/**
 * Enum to track the status of a review.
 */
public enum ReviewStatus {
    PENDING,    // Waiting for moderation or confirmation
    APPROVED,   // Approved and visible
    REJECTED,   // Rejected due to policy violation or other reasons
    DELETED     // Deleted (either by user or admin)
}

