package br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto;

import lombok.Data;

@Data
public class AnimalFilterRequest {
    private String name;
    private String species;
}
