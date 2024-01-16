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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final GeneralMapper generalMapper;
//    private final static org.slf4j.Logger log = LoggerFactory.getLogger(ConverterController.class);

    @PostMapping("/create")
    public ResponseEntity<Currency> create(@RequestBody CreateCurrencyDTO createCurrencyDTO){

        log.info("Save new currency:");

        Currency currency = currencyService.save(generalMapper.mapToCurrency(createCurrencyDTO));

        log.info("New user's name is: "  + createCurrencyDTO.getName());

        return ResponseEntity.ok(currency);
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<Currency> update(@RequestBody UpdateCurrencyDTO updateCurrencyDTO,
                                           @PathVariable(name = "currencyId") Long id){

        log.info("Update old currency with id = " + id);

        Currency currency = currencyService.update(generalMapper.mapToCurrency(updateCurrencyDTO));

        return ResponseEntity.ok(currency);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Currency> deleteById(@PathVariable(name = "currencyId") Long id){
        if(currencyService.findById(id).isPresent()) {

            log.info("Delete currency, because id = " + id + " was found.");

            currencyService.deleteById(id);
        }

        return ResponseEntity.ok().build();

    }


    @GetMapping("{id}/get")
    public ResponseEntity<Currency> findById(@RequestBody GetCurrencyDTO getCurrencyDTO,
                                             @PathVariable(name = "currencyId") Long id){

        log.info("Find currency by id = " + id);

        Currency currencyById = currencyService.findById(generalMapper.mapToCurrency(getCurrencyDTO).getId()).orElseThrow();

        return ResponseEntity.ok(currencyById);

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Currency>> findAll(){

        List<Currency> currencies = currencyService.findAll();

        log.info("Find all currencies: " + currencies);

        return ResponseEntity.ok(currencies);
    }

}
