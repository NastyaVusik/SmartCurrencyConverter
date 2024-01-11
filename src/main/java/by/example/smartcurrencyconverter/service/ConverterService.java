package by.example.smartcurrencyconverter.service;

import by.example.smartcurrencyconverter.dto.currencyDTO.ViewedCurrencyDTO;
import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConverterService {

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date convertionDate;

    private final CurrencyService currencyService;


//    //Calculate result amount of "toCurrency"
//    public double getResult(String fromCurrencyName, String toCurrencyName, double convertedAmount) {
//
//        Currency fromCurrency = currencyService.findByName(fromCurrencyName).orElseThrow();
//        Currency toCurrency = currencyService.findByName(toCurrencyName).orElseThrow();
//
//        double difference = (fromCurrency.getGovBankRate()) / (toCurrency.getGovBankRate());
//
//        return difference * convertedAmount;
//    }


    //Calculate result amount of "toCurrency"
    public Double getResult(ViewedCurrencyDTO viewedCurrencyDTO, Double amount) {

        Currency fromCurrency = currencyService.findByName(viewedCurrencyDTO.getName()).orElseThrow();
        Currency toCurrency = currencyService.findByName(viewedCurrencyDTO.getName()).orElseThrow();

        Double difference = (fromCurrency.getGovBankRate()) / (toCurrency.getGovBankRate());

        viewedCurrencyDTO.setAmount(Math.abs(amount));

//        if(amount == 0){
////write something??????????????????????????????????
//        }

        return difference * viewedCurrencyDTO.getAmount();
    }

}
