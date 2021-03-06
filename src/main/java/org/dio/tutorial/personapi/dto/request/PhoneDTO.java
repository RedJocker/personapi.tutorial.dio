package org.dio.tutorial.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.dio.tutorial.personapi.enums.PhoneType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {


    private Long id;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @NotBlank
    @Size(min = 13, max = 14)
    private String number;
}
