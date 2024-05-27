package Chaeda_spring.global.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UrlConstants {
    FASTAPI_SERVER_URL("http://114.70.23.79:80"),
    ;

    private String value;
}

