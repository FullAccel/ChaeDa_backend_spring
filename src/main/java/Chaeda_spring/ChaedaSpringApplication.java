package Chaeda_spring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChaedaSpringApplication {

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(ChaedaSpringApplication.class, args);
    }

//    @PostConstruct
//    @Transactional
//    public void init() {
//        // 시퀀스가 존재하지 않으면 생성
//        entityManager.createNativeQuery(
//                "DO $$ " +
//                        "BEGIN " +
//                        "IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'member_id_seq') THEN " +
//                        "CREATE SEQUENCE member_id_seq INCREMENT BY 1; " +
//                        "END IF; " +
//                        "END $$;"
//        ).executeUpdate();
//
//        // 시퀀스 값 초기화
//        entityManager.createNativeQuery(
//                "SELECT setval('member_id_seq', COALESCE((SELECT MAX(id) FROM member), 1), false)"
//        ).executeUpdate();
//    }

}
