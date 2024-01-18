package by.example.smartcurrencyconverter.service;

import by.example.smartcurrencyconverter.dto.currencyDTO.ConverterCurrencyDTO;
import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


// json sample

// [
//    {
//       "Cur_ID":440,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"AUD",
//       "Cur_Scale":1,
//       "Cur_Name":"Австралийский доллар",
//       "Cur_OfficialRate":2.1055
//    },
//    {
//       "Cur_ID":510,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"AMD",
//       "Cur_Scale":1000,
//       "Cur_Name":"Армянских драмов",
//       "Cur_OfficialRate":7.7829
//    },
//    {
//       "Cur_ID":441,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"BGN",
//       "Cur_Scale":1,
//       "Cur_Name":"Болгарский лев",
//       "Cur_OfficialRate":1.7620
//    },
//    {
//       "Cur_ID":514,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"BRL",
//       "Cur_Scale":10,
//       "Cur_Name":"Бразильских реалов",
//       "Cur_OfficialRate":6.4821
//    },
//    {
//       "Cur_ID":449,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"UAH",
//       "Cur_Scale":100,
//       "Cur_Name":"Гривен",
//       "Cur_OfficialRate":8.3144
//    },
//    {
//       "Cur_ID":450,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"DKK",
//       "Cur_Scale":10,
//       "Cur_Name":"Датских крон",
//       "Cur_OfficialRate":4.6159
//    },
//    {
//       "Cur_ID":513,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"AED",
//       "Cur_Scale":10,
//       "Cur_Name":"Дирхамов ОАЭ",
//       "Cur_OfficialRate":8.5717
//    },
//    {
//       "Cur_ID":431,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"USD",
//       "Cur_Scale":1,
//       "Cur_Name":"Доллар США",
//       "Cur_OfficialRate":3.1482
//    },
//    {
//       "Cur_ID":512,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"VND",
//       "Cur_Scale":100000,
//       "Cur_Name":"Донгов",
//       "Cur_OfficialRate":12.8603
//    },
//    {
//       "Cur_ID":451,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"EUR",
//       "Cur_Scale":1,
//       "Cur_Name":"Евро",
//       "Cur_OfficialRate":3.4469
//    },
//    {
//       "Cur_ID":452,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"PLN",
//       "Cur_Scale":10,
//       "Cur_Name":"Злотых",
//       "Cur_OfficialRate":7.8697
//    },
//    {
//       "Cur_ID":508,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"JPY",
//       "Cur_Scale":100,
//       "Cur_Name":"Иен",
//       "Cur_OfficialRate":2.1595
//    },
//    {
//       "Cur_ID":511,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"INR",
//       "Cur_Scale":100,
//       "Cur_Name":"Индийских рупий",
//       "Cur_OfficialRate":3.7985
//    },
//    {
//       "Cur_ID":461,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"IRR",
//       "Cur_Scale":100000,
//       "Cur_Name":"Иранских риалов",
//       "Cur_OfficialRate":7.4770
//    },
//    {
//       "Cur_ID":453,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"ISK",
//       "Cur_Scale":100,
//       "Cur_Name":"Исландских крон",
//       "Cur_OfficialRate":2.3025
//    },
//    {
//       "Cur_ID":371,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"CAD",
//       "Cur_Scale":1,
//       "Cur_Name":"Канадский доллар",
//       "Cur_OfficialRate":2.3452
//    },
//    {
//       "Cur_ID":462,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"CNY",
//       "Cur_Scale":10,
//       "Cur_Name":"Китайских юаней",
//       "Cur_OfficialRate":4.3602
//    },
//    {
//       "Cur_ID":394,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"KWD",
//       "Cur_Scale":1,
//       "Cur_Name":"Кувейтский динар",
//       "Cur_OfficialRate":10.2447
//    },
//    {
//       "Cur_ID":454,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"MDL",
//       "Cur_Scale":10,
//       "Cur_Name":"Молдавских леев",
//       "Cur_OfficialRate":1.7725
//    },
//    {
//       "Cur_ID":448,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"NZD",
//       "Cur_Scale":1,
//       "Cur_Name":"Новозеландский доллар",
//       "Cur_OfficialRate":1.9606
//    },
//    {
//       "Cur_ID":455,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"NOK",
//       "Cur_Scale":10,
//       "Cur_Name":"Норвежских крон",
//       "Cur_OfficialRate":3.0437
//    },
//    {
//       "Cur_ID":456,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"RUB",
//       "Cur_Scale":100,
//       "Cur_Name":"Российских рублей",
//       "Cur_OfficialRate":3.5831
//    },
//    {
//       "Cur_ID":457,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"XDR",
//       "Cur_Scale":1,
//       "Cur_Name":"СДР (Специальные права заимствования)",
//       "Cur_OfficialRate":4.2062
//    },
//    {
//       "Cur_ID":421,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"SGD",
//       "Cur_Scale":1,
//       "Cur_Name":"Сингапурcкий доллар",
//       "Cur_OfficialRate":2.3599
//    },
//    {
//       "Cur_ID":458,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"KGS",
//       "Cur_Scale":100,
//       "Cur_Name":"Сомов",
//       "Cur_OfficialRate":3.5246
//    },
//    {
//       "Cur_ID":459,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"KZT",
//       "Cur_Scale":1000,
//       "Cur_Name":"Тенге",
//       "Cur_OfficialRate":6.9797
//    },
//    {
//       "Cur_ID":460,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"TRY",
//       "Cur_Scale":10,
//       "Cur_Name":"Турецких лир",
//       "Cur_OfficialRate":1.0459
//    },
//    {
//       "Cur_ID":429,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"GBP",
//       "Cur_Scale":1,
//       "Cur_Name":"Фунт стерлингов",
//       "Cur_OfficialRate":4.0092
//    },
//    {
//       "Cur_ID":463,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"CZK",
//       "Cur_Scale":100,
//       "Cur_Name":"Чешских крон",
//       "Cur_OfficialRate":13.9444
//    },
//    {
//       "Cur_ID":464,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"SEK",
//       "Cur_Scale":10,
//       "Cur_Name":"Шведских крон",
//       "Cur_OfficialRate":3.0571
//    },
//    {
//       "Cur_ID":426,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"CHF",
//       "Cur_Scale":1,
//       "Cur_Name":"Швейцарский франк",
//       "Cur_OfficialRate":3.6840
//    }
// ]



@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Value("${currency.api.url}")
    private String apiUrl; // Inject the API URL from application.properties

    private final RestTemplate restTemplate;

    public Currency save(Currency currency) {

        return currencyRepository.save(currency);
    }


    public void deleteById(Long id) {
        currencyRepository.findById(id).orElseThrow(RuntimeException::new);

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


    public Currency update(Currency currency){
        currency = currencyRepository.findById(currency.getId()).orElseThrow();

        return currencyRepository.save(currency);
    }


    @Transactional(readOnly = true)
    public Optional<Currency> findByName(String name) {

        return Optional.of(currencyRepository.findByName(name).orElseThrow());
    }


    @Transactional(readOnly = true)
    public Optional<Currency> findByShortName(String shortName) {

        return Optional.of(currencyRepository.findByShortName(shortName).orElseThrow());
    }


    @Transactional(readOnly = true)
    public Optional<Currency> findByNameOrShortName(String name, String shortName){

        return Optional.of(currencyRepository.findByNameOrShortName(name, shortName).orElseThrow());
    }



    // url=https://api.nbrb.by/exrates/rates?periodicity=0
    public List<ConverterCurrencyDTO> getCurrencies() {
//        System.out.println(apiUrl);

        ResponseEntity<ConverterCurrencyDTO[]> responseEntity = restTemplate.getForEntity(apiUrl, ConverterCurrencyDTO[].class);
        Object[] objects = responseEntity.getBody();

        return List.of((ConverterCurrencyDTO[]) objects);
    }

}
