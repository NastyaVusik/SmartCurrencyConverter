package by.example.smartcurrencyconverter.mapper;

import by.example.smartcurrencyconverter.dto.userDTO.LoginUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.RegistrationUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.UpdateUserDTO;
import by.example.smartcurrencyconverter.entity.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface GeneralMapper {

    User mapToUser(RegistrationUserDTO registrationUserDTO);
    RegistrationUserDTO mapToRegistrationUserDTO(User user);


    User mapToUser(LoginUserDTO loginUserDTO);
    LoginUserDTO mapToLoginUserDTO(User user);


    User mapToUser(UpdateUserDTO updateUserDTO);
    UpdateUserDTO mapToUpdateUserDTO(User user);
}
