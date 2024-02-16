package site.devdalus.ariadne.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int statusCode;
    private final String message;


    @Builder
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
