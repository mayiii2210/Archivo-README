package com.appspc.apilibros.data.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

public interface IConnection {
    public DataSource dataSource();
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource);
}
