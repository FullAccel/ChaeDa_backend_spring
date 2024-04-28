package Chaeda_spring.global.exception;

public class AlreadyExistException extends CustomException {
    public AlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AlreadyExistException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
