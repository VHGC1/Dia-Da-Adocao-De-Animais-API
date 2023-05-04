package br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class AnimalCreateRequest {
    @NotEmpty(message = "O nome deve ser definido")
    private String name;

    @NotNull(message = "A idade deve ser definida")
    private int age;

    @NotEmpty(message = "A especie deve ser definida")
    private String Species;

    @Past(message = "A data deve ser igual ou anterior a data de hoje")
    private Date birthDate;

    @Range(min = 1, max = 5, message = "Fofura deve estar entre 1 e 5")
    private int cuteness = 1;

    @Range(min = 1, max = 5, message = "Carinho deve estar entre 1 e 5")
    private int kindness = 1;

    @Email(message = "email invalido")
    private String email;
}
