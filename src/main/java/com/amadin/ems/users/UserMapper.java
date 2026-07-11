package com.amadin.ems.users;

public class UserMapper {

    public static UserDto mapToUserDTO(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmployee() != null ? user.getEmployee().getId(): "",
                user.getEmployee() != null ? user.getEmployee().getFirstName() + " " + user.getEmployee().getLastName(): "",
                user.getRole(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt());

    }

    public static User mapToUser(UserDto userDto){
        return new User(
            userDto.getId(),
            userDto.getUsername(),
            userDto.getPassword(),
            null,
            userDto.getRole(),
            userDto.getIsActive(),
            userDto.getCreatedAt(),
            userDto.getUpdatedAt()
         );
    }

}
