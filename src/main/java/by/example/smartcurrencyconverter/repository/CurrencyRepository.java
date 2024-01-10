package by.example.smartcurrencyconverter.repository;

import by.example.smartcurrencyconverter.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CurrencyRepository extends JpaRepository<Currency,Long> {

    Optional<Currency> findByName(String name);
    Optional<Currency> findByShortName(String shortName);
}
