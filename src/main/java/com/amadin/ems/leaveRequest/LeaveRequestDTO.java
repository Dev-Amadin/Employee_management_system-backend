package com.amadin.ems.leaveRequest;

import java.time.Instant;
import jakarta.validation.constraints.NotNull;

public record LeaveRequestDTO(
        String id,
        @NotNull(message = "Leave type is required") String type,
        @NotNull(message = "Start Date is required") Instant startDate,
        @NotNull(message = "End Date is required") Instant endDate,
        Long duration,
        String reason,
        String status,
        String employeeId,
        String employeeName,
        String approvedById,
        String approvedByName,
        Instant reviewedAt,
        String rejectionReason,
        Instant createdAt,
        Instant updatedAt) {
}
