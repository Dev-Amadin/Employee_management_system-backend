package com.amadin.ems.leaveRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService lService;

    // Create LeaveRequest entity API
    @PostMapping
    public ResponseEntity<LeaveRequestDTO> createLeaveRequest(@Valid @RequestBody LeaveRequestDTO dto) {
        return new ResponseEntity<LeaveRequestDTO>(lService.createLeaveRequest(dto), HttpStatus.CREATED);

    }

    // Edit LeaveRequest entity API
    @PatchMapping("{id}")
    public ResponseEntity<LeaveRequestDTO> updateLeaveRequest(@Valid @PathVariable("id") String requestId,
            @RequestBody LeaveRequestDTO dto) {
        return ResponseEntity.ok(lService.updateLeaveRequest(requestId, dto));

    }

    // Delete LeaveRequest entity API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLeaveRequest(@Valid @PathVariable("id") String requestId) {
        lService.deleteLeaveRequest(requestId);
        return ResponseEntity.ok("Leave Request deleted successfully.");

    }

    // Get LeaveRequests API
    @GetMapping
    public ResponseEntity<Page<LeaveRequestDTO>> getLeaveRequests(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection,
            @RequestParam(required = false, defaultValue = "createdAt") String sortField,
            @RequestParam String role,
            @RequestParam(required = false, defaultValue = "") String status,
            @RequestParam(required = false, defaultValue = "") String roleOwnerId

    ) {
        Page<LeaveRequestDTO> lPage = lService.getLeaveRequests(size, page, sortField, sortDirection, status, role,
                roleOwnerId);
        return ResponseEntity.ok(lPage);
    }

    @GetMapping("/summary")
    public ResponseEntity<LeaveRequestSummaryDto> getSummary(
            @RequestParam(required = false, defaultValue = "") String employeeId) {
        return ResponseEntity.ok(lService.getSummary(employeeId));
    }

}
