package by.example.smartcurrencyconverter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Converter {
    @Id
    private Long id;

    @OneToMany
    private Set<Currency> currencies1;

    @OneToMany
    private Set<Currency> currencies2;

}
