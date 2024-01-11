package by.example.smartcurrencyconverter.web.controller;

import by.example.smartcurrencyconverter.dto.currencyDTO.ViewedCurrencyDTO;
import by.example.smartcurrencyconverter.dto.userDTO.LoginUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.UpdateUserDTO;
import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.entity.User;
import by.example.smartcurrencyconverter.mapper.GeneralMapper;
import by.example.smartcurrencyconverter.service.ConverterService;
import by.example.smartcurrencyconverter.service.CurrencyService;
import by.example.smartcurrencyconverter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/converter")
public class ConverterController {

    private final UserService userService;
    private final CurrencyService currencyService;
    private final ConverterService converterService;
    private final GeneralMapper generalMapper;

    @PostMapping("/currency={name}-amoumt")
    public ResponseEntity<Double> getResults(@Validated @RequestBody UpdateUserDTO updateUserDTO, @RequestBody ViewedCurrencyDTO viewedCurrencyDTO,
                                             @PathVariable(name = "currencyName") String name, @PathVariable(name = "amount") Double amount){

User user = userService.findById(generalMapper.mapToUser(updateUserDTO).getId()).orElseThrow();
Currency fromCurrency = currencyService.findByName(generalMapper.mapToCurrency(viewedCurrencyDTO).getName()).orElseThrow();
Currency toCurrency = currencyService.findByName(generalMapper.mapToCurrency(viewedCurrencyDTO).getName()).orElseThrow();

if(amount == 0){
    return new ResponseEntity<>(HttpStatus.valueOf("Enter amount more than null!"));
}

Double result = converterService.getResult(viewedCurrencyDTO, amount);

user.getLovelyCurrencies().put(user.getId(), fromCurrency);
user.getLovelyCurrencies().put(user.getId(), toCurrency);

return ResponseEntity.ok(result);

    }
}
