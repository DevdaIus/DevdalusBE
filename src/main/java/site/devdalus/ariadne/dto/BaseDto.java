package site.devdalus.ariadne.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class BaseDto {
    public static class BaseResponseDto {
        private final HttpStatus code;
        private final String message;

        @Builder
        public BaseResponseDto(HttpStatus code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
