package Chaeda_spring.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    STUDENT("ROLE_STUDENT", "학생"),
    TEACHER("ROLE_TEACHER", "선생님"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;

    // key 값으로 Role 객체를 반환하는 메서드
    public static Role findByKey(String key) {
        for (Role role : Role.values()) {
            if (role.getKey().equals(key)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No role with key: " + key);
    }
}
