package org.dio.tutorial.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {


    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;

    @NotBlank
    @CPF
    private String cpf;

    private String birthDate;

    @NotEmpty
    @Valid
    private List<PhoneDTO> phones;
}
