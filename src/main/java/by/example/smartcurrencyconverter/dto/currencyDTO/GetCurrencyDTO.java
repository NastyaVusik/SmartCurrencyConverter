package by.example.smartcurrencyconverter.dto.currencyDTO;

import by.example.smartcurrencyconverter.entity.Bank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class GetCurrencyDTO {

    @NotBlank
    @NotEmpty
    private Long id;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String shortName;

    @NotBlank
    @NotEmpty
    private double govBankRate;

    @NotBlank
    @NotEmpty
    private LocalDate govBankRateDate;

    @NotBlank
    @NotEmpty
    private Set<Bank> banks;

    @NotBlank
    @NotEmpty
    private double bestRate;
}
