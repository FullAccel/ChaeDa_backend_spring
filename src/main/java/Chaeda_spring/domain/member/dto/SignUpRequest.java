package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Role;

public interface SignUpRequest {
    String loginId();

    String password();

    String name();

    String email();

    String gender();

    String phoneNumber();

    Role role();
}
