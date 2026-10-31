package com.amadin.ems.leaveRequest;

public class LeaveRequestMapper {

    static LeaveRequest RequestDtoToEntity(LeaveRequestDTO dto) {
        return new LeaveRequest(
                dto.id(), dto.type(), dto.startDate(), dto.endDate(), dto.duration(), dto.reason(),
                dto.status(), null, null, dto.reviewedAt(), dto.rejectionReason(),
                dto.createdAt(), dto.updatedAt());
    }

    static LeaveRequestDTO RequestEntityToDto(LeaveRequest request) {
        return new LeaveRequestDTO(request.getId(), request.getType(), request.getStartDate(), request.getEndDate(),
                request.getDuration(), request.getReason(),
                request.getStatus(), 
                request.getEmployee() != null ? request.getEmployee().getId() : "",
                request.getEmployee() != null
                        ? request.getEmployee().getFirstName() + " " + request.getEmployee().getLastName()
                        : "",
                request.getApprovedBy() != null ? request.getApprovedBy().getId() : "",
                request.getApprovedBy() != null
                        ? request.getApprovedBy().getFirstName() + " " + request.getApprovedBy().getLastName()
                        : "",
                request.getReviewedAt(), request.getRejectionReason(),
                request.getCreatedAt(), request.getUpdatedAt());
    }

}
