package br.com.almodeschool.mvcalmode.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "usuario",
        indexes = {
                @Index(name = "idx_nome", columnList = "nome"),
                @Index(name = "idx_cpf", columnList = "cpf" )
        })
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
public class Usuario {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    UUID id;

    @NonNull
    String nome;

    @NonNull
    String cpf;

}
