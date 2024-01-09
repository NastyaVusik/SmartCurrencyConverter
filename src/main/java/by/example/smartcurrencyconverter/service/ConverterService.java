package by.example.smartcurrencyconverter.service;

import by.example.smartcurrencyconverter.entity.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConverterService {

    public double getDifference(Currency basicCurrency, Currency requiredCurrency){

        return basicCurrency.getGovBankRate()/requiredCurrency.getGovBankRate();
    }
}
