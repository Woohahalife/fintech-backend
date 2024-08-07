package com.core.backend.common.repsonse;

import com.core.backend.common.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"status","statusCode", "message", "status", "data"})
public final class ResultResponse<T> {

    private HttpStatus status;
    private int statusCode;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

     public static <T> ResultResponse<T> success() {
        return new ResultResponse<>(
            HttpStatus.OK,
            HttpStatus.OK.value(),
            "요청이 성공적으로 처리되었습니다.",
            null);
    }

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(
                HttpStatus.OK,
                HttpStatus.OK.value(),
                "요청이 성공적으로 처리되었습니다.",
                data);
    }

    public static <T> ResultResponse<T> failure(ErrorCode errorCode) {
        return new ResultResponse<>(
                errorCode.getHttpStatus(),
                errorCode.getStatusCode(),
                errorCode.getMessage(),
                null);
    }
}
