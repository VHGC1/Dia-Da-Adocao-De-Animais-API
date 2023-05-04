package br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto;

import lombok.Data;
import javax.validation.constraints.Email;


@Data
public class AnimalUpdateRequest {
    @Email(message = "Email invalido")
    private String email;
}
