package com.amadin.ems.leaveRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.amadin.ems.employee.Employee;
import com.amadin.ems.employee.EmployeeMapper;
import com.amadin.ems.employee.EmployeeService;
import com.amadin.ems.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository lRequestRepository;
    private final EmployeeService employeeService;

    @Override
    public LeaveRequestDTO createLeaveRequest(LeaveRequestDTO dto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeService.getEmployeeById(dto.employeeId()));
        LeaveRequest request = LeaveRequestMapper.RequestDtoToEntity(dto);
        request.setEmployee(employee);
        request.setDuration(ChronoUnit.DAYS.between(dto.startDate(), dto.endDate()) + 1);
        request.setStatus("PENDING");
        request.setCreatedAt(Instant.now());
        request.setUpdatedAt(Instant.now());

        LeaveRequest savedRequest = lRequestRepository.save(request);
        return LeaveRequestMapper.RequestEntityToDto(savedRequest);

    }

    @Override
    public LeaveRequestDTO getLeaveRequestById(String leaveRequestId) {
        LeaveRequest request = lRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave Request not found. id: " + leaveRequestId));

        return LeaveRequestMapper.RequestEntityToDto(request);
    }

    @Override
    public LeaveRequestDTO updateLeaveRequest(String leaveRequestId, LeaveRequestDTO dto) {
        LeaveRequest request = LeaveRequestMapper.RequestDtoToEntity(dto);
        request.setDuration(ChronoUnit.DAYS.between(dto.startDate(), dto.endDate()) + 1);
        request.setStatus("PENDING");
        request.setUpdatedAt(Instant.now());

        LeaveRequest savedRequest = lRequestRepository.save(request);
        return LeaveRequestMapper.RequestEntityToDto(savedRequest);
    }

    @Override
    public void deleteLeaveRequest(String leaveRequestId) {
        lRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave Request not found. id: " + leaveRequestId));
        lRequestRepository.deleteById(leaveRequestId);
    }

    @Override
    public Page<LeaveRequestDTO> getLeaveRequests(int size, int page, String sortField, String sortDirection,
            String status, String role, String roleOwnerId) {

        Pageable pageable = null;
        Page<LeaveRequest> requests = null;

        if (sortDirection.equalsIgnoreCase("ASC")) {
            pageable = PageRequest.of(page, size, Direction.ASC, sortField);
        } else {
            pageable = PageRequest.of(page, size, Direction.DESC, sortField);
        }

        switch (role) {
            case "ADMIN":
                if (!status.isEmpty()) {
                    requests = lRequestRepository.findByStatus(pageable, status);
                } else {
                    requests = lRequestRepository.findAll(pageable);
                }

                break;
            case "MANAGER":
                if (!status.isEmpty()) {
                    requests = lRequestRepository.findByApprovedByIdAndStatus(pageable, roleOwnerId, status);
                } else {
                    requests = lRequestRepository.findByApprovedById(pageable, roleOwnerId);
                }
                break;
            case "STAFF":
                if (!status.isEmpty()) {
                    requests = lRequestRepository.findByEmployeeIdAndStatus(pageable, roleOwnerId, status);
                } else {
                    requests = lRequestRepository.findByEmployeeId(pageable, roleOwnerId);
                }

                break;
            default:
                break;
        }

        return requests.map((req) -> LeaveRequestMapper.RequestEntityToDto(req));
    }

    @Override
    public LeaveRequestSummaryDto getSummary(String employeeId) {

        LeaveRequestSummaryDto requestSummaryDto = null;

        if (employeeId.isEmpty()) {
            requestSummaryDto = new LeaveRequestSummaryDto(
                    lRequestRepository.countByStatus("PENDING"),
                    lRequestRepository.countByStatus("APPROVED"),
                    lRequestRepository.countByStatus("REJECTED"),
                    lRequestRepository.countByStatus("CANCELLED"));
        } else {
            requestSummaryDto = new LeaveRequestSummaryDto(
                    lRequestRepository.countByStatusAndEmployeeId("PENDING", employeeId),
                    lRequestRepository.countByStatusAndEmployeeId("APPROVED", employeeId),
                    lRequestRepository.countByStatusAndEmployeeId("REJECTED", employeeId),
                    lRequestRepository.countByStatusAndEmployeeId("CANCELLED", employeeId));
        }

        return requestSummaryDto;
    }

}
