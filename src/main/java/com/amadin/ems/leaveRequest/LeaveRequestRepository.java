package com.amadin.ems.leaveRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, String> {

    Page<LeaveRequest> findByEmployeeId(Pageable pageable, String employeeId);

    Page<LeaveRequest> findByEmployeeIdAndStatus(Pageable pageable, String employeeId, String status);

    Page<LeaveRequest> findByApprovedById(Pageable pageable, String approvedById);

    Page<LeaveRequest> findByApprovedByIdAndStatus(Pageable pageable, String approvedById, String status);

    Page<LeaveRequest> findByStatus(Pageable pageable, String status);

    long countByStatus(String status);

    long countByStatusAndEmployeeId(String status, String employeeId);

}
