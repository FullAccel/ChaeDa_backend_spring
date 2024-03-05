//package Chaeda_spring.domain.member.entity;
//
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//public class OAuthAttributes {
//    private final Map<String, Object> attributes;
//    private final String nameAttributeKey;
//    private final String name;
//    private final String email;
//    private final String profileUrl;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//    }
//
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .picture((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public Member toEntity() {
//        return Member.builder()
//                .name(name)
//                .profileUrl(profileUrl)
//                .build();
//    }
//}
