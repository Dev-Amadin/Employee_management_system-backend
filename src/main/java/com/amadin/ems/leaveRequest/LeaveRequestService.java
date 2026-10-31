package com.amadin.ems.leaveRequest;

import org.springframework.data.domain.Page;

public interface LeaveRequestService {

    LeaveRequestDTO createLeaveRequest(LeaveRequestDTO dto);

    LeaveRequestDTO getLeaveRequestById(String leaveRequestId);

    Page<LeaveRequestDTO> getLeaveRequests(int size, int page, String sortField, String sortDirection,
            String status, String role, String roleOwnerId);

    LeaveRequestDTO updateLeaveRequest(String leaveRequestId, LeaveRequestDTO dto);

    void deleteLeaveRequest(String leaveRequestId);

    LeaveRequestSummaryDto getSummary(String employeeId);

}
