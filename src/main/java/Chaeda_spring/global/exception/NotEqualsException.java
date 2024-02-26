package Chaeda_spring.global.exception;

public class NotEqualsException extends RuntimeException {

    public NotEqualsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
