package Chaeda_spring.global.exception;

public class EmptyException extends CustomException {
    public EmptyException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EmptyException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
