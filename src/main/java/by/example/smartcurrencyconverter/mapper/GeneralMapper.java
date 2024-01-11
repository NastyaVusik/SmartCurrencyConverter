package by.example.smartcurrencyconverter.mapper;

import by.example.smartcurrencyconverter.dto.currencyDTO.CreateCurrencyDTO;
import by.example.smartcurrencyconverter.dto.currencyDTO.GetCurrencyDTO;
import by.example.smartcurrencyconverter.dto.currencyDTO.UpdateCurrencyDTO;
import by.example.smartcurrencyconverter.dto.currencyDTO.ViewedCurrencyDTO;
import by.example.smartcurrencyconverter.dto.userDTO.GetUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.LoginUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.RegistrationUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.UpdateUserDTO;
import by.example.smartcurrencyconverter.entity.Currency;
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


    User mapToUser(GetUserDTO getUserDTO);


    Currency mapToCurrency(ViewedCurrencyDTO viewedCurrencyDTO);


    Currency mapToCurrency(UpdateCurrencyDTO updateCurrencyDTO);


    Currency mapToCurrency(CreateCurrencyDTO createCurrencyDTO);
    CreateCurrencyDTO mapToCreateCurrencyDTO(Currency currency);


    Currency mapToCurrency(GetCurrencyDTO getCurrencyDTO);

}
