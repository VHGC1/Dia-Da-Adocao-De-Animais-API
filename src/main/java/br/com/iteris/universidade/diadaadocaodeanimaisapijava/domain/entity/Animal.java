package br.com.iteris.universidade.diadaadocaodeanimaisapijava.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Table(name = "Animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private int age;

    @Column(length = 40, nullable = false)
    private String species;

    @Column(length = 40, columnDefinition = "datetime")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Column(length = 1)
    @Range(min = 1, max = 5)
    private int cuteness;

    @Column(length = 1)
    @Range(min = 1, max = 5)
    private int kindness;

    @Column(length = 40)
    @Email
    private String email;
}
