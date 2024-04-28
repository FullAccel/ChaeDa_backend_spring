package Chaeda_spring.global.config.properties;

import Chaeda_spring.global.security.jwt.entity.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
        JwtProperties.class,
})
@Configuration
public class PropertiesConfig {
}
