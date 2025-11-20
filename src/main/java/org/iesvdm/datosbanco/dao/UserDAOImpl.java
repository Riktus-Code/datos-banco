package org.iesvdm.datosbanco.dao;

import org.iesvdm.datosbanco.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl implements UserDAO{


    public JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public User create(User user) {

        String sql = """
                insert into user (nombre,direccion,telefono,fecha_nac)
                values(     ?,      ?,      ?,      ?);
                """;
        return null;
    }

    @Override
    public User findByUserName(String username) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
