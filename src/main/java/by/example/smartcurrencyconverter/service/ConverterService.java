package by.example.smartcurrencyconverter.service;

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

    private final ConverterService converterService;
    private final UserRepository userRepository;
    private final CurrencyService currencyService;


//    //Get difference between two currencies
//    public double getDifference(String fromCurrencyName, String toCurrencyName) {
//
//        Currency fromCurrency = currencyService.findByName(fromCurrencyName).orElseThrow();
//        Currency toCurrency = currencyService.findByName(toCurrencyName).orElseThrow();
//
//        return (fromCurrency.getGovBankRate()) / (toCurrency.getGovBankRate());
//    }


    //Calculate result amount of "toCurrency"
    public double getDifference(String fromCurrencyName, String toCurrencyName, double convertedAmount) {

        Currency fromCurrency = currencyService.findByName(fromCurrencyName).orElseThrow();
        Currency toCurrency = currencyService.findByName(toCurrencyName).orElseThrow();

        double difference = (fromCurrency.getGovBankRate()) / (toCurrency.getGovBankRate());

        return difference * convertedAmount;
    }

}
