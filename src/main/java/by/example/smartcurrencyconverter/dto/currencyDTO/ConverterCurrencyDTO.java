package by.example.smartcurrencyconverter.dto.currencyDTO;

import lombok.*;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

//    {
//       "Cur_ID":426,
//       "Date":"2024-01-16T00:00:00",
//       "Cur_Abbreviation":"CHF",
//       "Cur_Scale":1,
//       "Cur_Name":"Швейцарский франк",
//       "Cur_OfficialRate":3.6840
//    }

@Getter
@Setter
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ConverterCurrencyDTO {

    @JsonProperty("Cur_ID")
    private long id;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;

    @JsonProperty("Cur_Scale")
    private Double scale;

    @JsonProperty("Cur_Name")
    private String name;

    @JsonProperty("Cur_OfficialRate")
    private Double officialRate;

    private Double amount;

}
