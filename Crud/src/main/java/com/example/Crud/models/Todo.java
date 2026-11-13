package com.example.Crud.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    Long id;

    @NotBlank
    //  @Schema(name= "title", example="Todo") is act like a placeholder
    String title;
    Boolean isCompleted;
 // use validationbecause when we send data without input is not acceptable
    // so we use notblank or not null

}
