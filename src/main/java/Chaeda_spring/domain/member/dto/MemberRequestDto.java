package Chaeda_spring.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    protected String name;

    protected String email;

    protected String gender;

    protected String phoneNumber;

    protected String address;

    protected String profileUrl;
}
