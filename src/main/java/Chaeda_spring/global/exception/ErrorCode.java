package Chaeda_spring.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "Sample Error Message"),

    // Common
    UNSUPPORTED_VALUE(HttpStatus.BAD_REQUEST, "지원하지 않는 종류의 값이 body에 있습니다."),
    TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, "글자 수가 제한 길이를 초과하였습니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "요청 한 값 타입이 잘못되어 binding에 실패하였습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP method 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류, 관리자에게 문의하세요"),
    JSON_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 파싱오류, 관리자에게 문의"),
    INCORRECT_SCOPE(HttpStatus.BAD_REQUEST, "범위에 맞지 않는 값을 넣었습니다"),
    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
    MEMBER_NOT_AUTHORIZED_TO_ANNOUNCE(HttpStatus.BAD_REQUEST, "이 회원은 해당 공지를 할 권한이 없습니다."),
    IS_NOT_TEACHER(HttpStatus.BAD_REQUEST, "해당 회원은 선생님 회원이 아닙니다"),
    IS_NOT_STUDENT(HttpStatus.BAD_REQUEST, "해당 회원은 학생 회원이 아닙니다"),

    // Class
    CLASS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 클래스를 찾을 수 없습니다."),

    // Submission
    HOMEWORK_NOT_COMPLETE(HttpStatus.BAD_REQUEST, "숙제를 아직 완료하지 않았습니다"),


    // TextBook
    TEXTBOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 교재를 찾을 수 없습니다"),

    // Self_Assignment
    SELF_ASSIGNMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 과제를 찾을 수 없습니다"),

    //Review_note
    PROBLEM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 문제를 찾을 수 없습니다."),

    //Announcement
    ANNOUNCEMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 공지를 찾을 수 없습니다."),

    //auth
    ID_OR_PW_EMPTY(HttpStatus.UNAUTHORIZED, "아이디나 비밀번호가 비어있습니다"),
    ALREADY_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다"),
    LOGIN_ID_NOT_FOUND(HttpStatus.UNAUTHORIZED, "가입되지 않은 아이디입니다"),

    // Security
    AUTH_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "시큐리티 인증 정보를 찾을 수 없습니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    MEMBER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "이미 가입된 회원입니다."),
    MEMBER_ALREADY_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),
    MEMBER_INVALID_NICKNAME(HttpStatus.BAD_REQUEST, "올바르지 않는 닉네임입니다."),
    MEMBER_ALREADY_DELETED(HttpStatus.NOT_FOUND, "이미 탈퇴한 회원입니다."),
    PASSWORD_NOT_MATCHES(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    ID_TOKEN_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "ID 토큰 검증에 실패했습니다."),

    // Image
    IMAGE_KEY_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지 키를 찾을 수 없습니다."),
    IMAGE_FILE_EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지 파일 형식을 찾을 수 없습니다."),
    IMAGE_TYPE_INCORRECT(HttpStatus.BAD_REQUEST, "해당 이미지 타입을 받을 수 없습니다."),
    IMAGEFILE_AND_REQUESTBODY_COUNT_NOT_EQUALS(HttpStatus.BAD_REQUEST, "파일 개수와 요청 body의 개수가 맞지 않습니다."),

    // Notification
    SELF_SENDING_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "본인에게 메세지를 전송할 수 없습니다."),
    TODAY_COMPLETED_MISSION_SENDING_NOT_ALLOWED(
            HttpStatus.BAD_REQUEST, "오늘 미션을 완료한 미션에는 메세지를 전송할 수 없습니다."),

    ;

    private final HttpStatus status;
    private final String message;
}
