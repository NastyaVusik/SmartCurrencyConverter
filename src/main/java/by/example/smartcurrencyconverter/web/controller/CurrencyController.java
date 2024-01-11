package by.example.smartcurrencyconverter.web.controller;

import by.example.smartcurrencyconverter.dto.currencyDTO.CreateCurrencyDTO;
import by.example.smartcurrencyconverter.dto.currencyDTO.GetCurrencyDTO;
import by.example.smartcurrencyconverter.dto.currencyDTO.UpdateCurrencyDTO;
import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.mapper.GeneralMapper;
import by.example.smartcurrencyconverter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final GeneralMapper generalMapper;

    @PostMapping("/create")
    public ResponseEntity<Currency> create(@RequestBody CreateCurrencyDTO createCurrencyDTO){

        Currency currency = currencyService.save(generalMapper.mapToCurrency(createCurrencyDTO));

        return ResponseEntity.ok(currency);
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<Currency> update(@RequestBody UpdateCurrencyDTO updateCurrencyDTO){

        Currency currency = currencyService.update(generalMapper.mapToCurrency(updateCurrencyDTO));

        return ResponseEntity.ok(currency);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Currency> deleteById(@PathVariable(name = "currencyId") Long id){
        if(currencyService.findById(id).isPresent()) {
            currencyService.deleteById(id);
        }

        return ResponseEntity.ok().build();

    }


    @GetMapping("/get")
    public ResponseEntity<Currency> findById(@RequestBody GetCurrencyDTO getCurrencyDTO){
        Currency currencyById = currencyService.findById(generalMapper.mapToCurrency(getCurrencyDTO).getId()).orElseThrow();

        return ResponseEntity.ok(currencyById);

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Currency>> findAll(){
        List<Currency> currencies = currencyService.findAll();

        return ResponseEntity.ok(currencies);
    }

}
