package com.example.day4.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @NonNull
    @Min(value = 1)
    @Max(value = 99)
    private Integer id;
    //Cannot be null Length more than 2

    @NotEmpty
    @Size( min = 4)
    private String name;
    //Cannot be null Length more than 4

    @NonNull
    @Min(value = 25)
    private Integer age ;
    //Cannot be null It has to be number It must be more than 25

    @AssertFalse
    private boolean onLeave;
    //must be false


    @Range(min = 1950, max = 2022)
    private int employmentYear ;
    //Cannot be null it has to be number must be a valid year

    @NonNull
    @Min(value = 0)
    private Integer annualLeave;
    //Cannot be null it has to be number Use project lombok
}
