package by.example.smartcurrencyconverter.service;

import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public Currency save(Currency currency) {

        return currencyRepository.save(currency);
    }


    public void deleteById(Long id) {
        currencyRepository.findById(id).orElseThrow(RuntimeException::new);     //????????????????????????????????

        currencyRepository.deleteById(id);
    }


    public Currency delete(Currency currency) {
        currencyRepository.delete(currency);

        return currency;
    }


    @Transactional(readOnly = true)
    public Optional<Currency> findById(Long id) {
        Optional<Currency> currencyById = Optional.of(currencyRepository.findById(id).orElseThrow());

        return currencyById;
    }


    @Transactional(readOnly = true)
    public List<Currency> findAll() {

        return currencyRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }


    @Transactional(readOnly = true)
    public Optional<Currency> findByName(String name) {

        return Optional.of(currencyRepository.findByName(name).orElseThrow());
    }


    @Transactional(readOnly = true)
    public Optional<Currency> findByShortName(String shortName) {

        return Optional.of(currencyRepository.findByShortName(shortName).orElseThrow());
    }

}
