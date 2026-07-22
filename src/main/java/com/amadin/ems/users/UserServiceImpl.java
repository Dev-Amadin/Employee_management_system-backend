package com.amadin.ems.users;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amadin.ems.employee.Employee;
import com.amadin.ems.employee.EmployeeMapper;
import com.amadin.ems.employee.EmployeeService;
import com.amadin.ems.exception.BadRequestException;
import com.amadin.ems.exception.DetailsAlreadyExistException;
import com.amadin.ems.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeService employeeService;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public UserDto getUserWithUsername(String username) {
        User user = userRepository.findByUsernameAndIsActive(username, true)
                .orElseThrow(() -> new ResourceNotFoundException("User " + username + " not found"));

        return UserMapper.mapToUserDTO(user);
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {

    // User user = getUserWithUsername(username);

    // return org.springframework.security.core.userdetails.User.builder()
    // .username(user.getUsername())
    // .password(user.getPassword())
    // .authorities(Collections.emptyList())
    // .build();
    // }

    public UserDto createGuestUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        // user.setPassword(passwordEncoder.encode(password));
        user.setPassword(password);
        user.setRole("");
        user.setIsActive(true);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        try {
            return UserMapper.mapToUserDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new DetailsAlreadyExistException("Username already exists");
        }

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmployeeId().isEmpty())
            throw new BadRequestException("Employee can not be empty");

        Employee employee = EmployeeMapper.mapToEmployee(employeeService.getEmployeeById(userDto.getEmployeeId()));
        User user = UserMapper.mapToUser(userDto);
        user.setEmployee(employee);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        try {
            return UserMapper.mapToUserDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new DetailsAlreadyExistException("Username already exists");
        }
    }

    @Override
    public Page<UserDto> getAllUsers(int size, int page, String sortField, String sortDirection) {
        Pageable pageable = null;

        if (sortDirection.equalsIgnoreCase("ASC")) {
            pageable = PageRequest.of(page, size, Direction.ASC, sortField);
        } else {
            pageable = PageRequest.of(page, size, Direction.DESC, sortField);
        }

        Page<User> users = userRepository.findAll(pageable);

        return users.map(user -> UserMapper.mapToUserDTO(user));

    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + id));

        if (userDto.getEmployeeId().isEmpty())
            throw new BadRequestException("Employee can not be empty");

        Employee employee = EmployeeMapper.mapToEmployee(employeeService.getEmployeeById(userDto.getEmployeeId()));
        user.setEmployee(employee);
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setUpdatedAt(Instant.now());

        try {
            return UserMapper.mapToUserDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new DetailsAlreadyExistException("Username already exists");
        }
    }

    @Override
    public UserDto getUserByIdAndStatus(String id, Boolean isActive) {
        User user = userRepository.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + id));

        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public UserDto deactivateUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + id));
        user.setUpdatedAt(Instant.now());
        user.setIsActive(false);
        try {
            return UserMapper.mapToUserDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new DetailsAlreadyExistException("Username already exists");
        }
    }

    @Override
    public UserDto activateUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + id));
        user.setUpdatedAt(Instant.now());
        user.setIsActive(true);
        try {
            return UserMapper.mapToUserDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new DetailsAlreadyExistException("Username already exists");
        }

    }

    @Override
    public void deleteUser(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + id));

        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + id));

        return UserMapper.mapToUserDTO(user);
    }
}
