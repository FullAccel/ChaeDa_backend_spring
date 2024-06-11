//package Chaeda_spring.global;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//import org.springframework.jdbc.datasource.init.ScriptException;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@Component
//public class ResourceDatabaseInitializer {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void initializeDatabase() {
//        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("data.sql"));
//        try (Connection connection = dataSource.getConnection()) {
//            resourceDatabasePopulator.populate(connection);
//        } catch (SQLException | ScriptException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
