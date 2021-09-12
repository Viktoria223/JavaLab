package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

public class PasswordBlackListImpl implements PasswordBlackList {
    private final JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_PASSWORD
            = "SELECT * FROM password_blacklist WHERE password=?";

    private final ResultSetExtractor<List<String>> passwordsResultSetExtractor = rows -> {
        List<String> passwords = new ArrayList<>();

        while (rows.next()) {
            passwords.add(rows.getString("password"));
        }
        return passwords;
    };

    public PasswordBlackListImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean blackList(String password){
        List<String> pass = jdbcTemplate.query(SQL_PASSWORD, passwordsResultSetExtractor);
        return !pass.isEmpty();
    }
}
