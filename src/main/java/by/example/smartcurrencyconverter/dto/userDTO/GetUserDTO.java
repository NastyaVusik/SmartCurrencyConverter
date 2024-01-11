package by.example.smartcurrencyconverter.dto.userDTO;

import by.example.smartcurrencyconverter.entity.Country;
import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDTO {

    @NotBlank
    @NotEmpty
    private Long id;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String surname;

    @NotBlank
    @NotEmpty
    private String username;

    @NotBlank
    @NotEmpty
    @Range(min = 6, max = 18)
    private String password;

    @NotBlank
    @NotEmpty
    private String email;

    @NotBlank
    @NotEmpty
    private LocalDate birthdayDate;

    @NotBlank
    @NotEmpty
    private Set<Country> country;

    @NotBlank
    @NotEmpty
    private Set<Role> roles;

    @NotBlank
    @NotEmpty
    private LocalDate joinedDate;

    private Set<Currency> lovelyCurrency;

}
