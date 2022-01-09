package me.js.springbootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class MysqlRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void run(ApplicationArguments args) throws Exception{
        try(Connection conn = dataSource.getConnection()){
            System.out.println(dataSource.getClass());
            System.out.println(conn.getMetaData().getURL());
            System.out.println(conn.getMetaData().getUserName());

            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            conn.close();
        }
        jdbcTemplate.execute("INSERT INTO USER VALUES (1,'js')");
    }
}
