package by.example.smartcurrencyconverter.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Email{

    private User recipient;
    private String message;
    private String subject;
    private String attachment;
}