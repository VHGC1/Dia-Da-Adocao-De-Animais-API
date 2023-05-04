package br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AnimalResponse {
    private int id;
    private String name;
    private int age;
    private String species;
    private Date birthDate;
    private int cuteness;
    private int kindness;
    private String email;
}
