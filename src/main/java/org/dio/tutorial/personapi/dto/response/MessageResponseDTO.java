package org.dio.tutorial.personapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private String message;

    public static MessageResponseDTO of(String message) {
        return new MessageResponseDTO(message);
    }

}
