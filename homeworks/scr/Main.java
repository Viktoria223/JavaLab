import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("resources/application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        DataSource dataSource = new HikariDataSource(config);
        CourseRepository courseRepository = new CourseRepositoryJdbcTemplateImpl(dataSource);
        System.out.println(courseRepository.findById(1));

    }
}
