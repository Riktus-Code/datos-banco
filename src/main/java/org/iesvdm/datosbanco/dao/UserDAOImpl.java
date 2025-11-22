package org.iesvdm.datosbanco.dao;

import org.iesvdm.datosbanco.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{


    public JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public User create(User user) throws SQLException {

        String sql = """
                insert into user (nombre,direccion,telefono,fecha_nac)
                values(     ?,      ?,      ?,      ?);
                """;
        String [] ids = {"id"};
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update( con ->{
            PreparedStatement ps = con.prepareStatement(sql,ids);

            ps.setString(1,user.getNombre());
            ps.setString(2,user.getDireccion());
            ps.setString(3,user.getTelefono());
            ps.setString(4,user.getDireccion());
            return ps;
        }, keyholder);
        user.setId(keyholder.getKey().longValue());
        return user;
    }

    @Override
    public User findByUserName(String nombre) {

        String sql = """
                select * from user where nombre = ?;
                """;

        User user = jdbcTemplate.queryForObject(sql,  (rs, rowNum) -> User.builder()
                .id(rs.getLong("id"))
                .nombre(rs.getString("nombre"))
                .direccion(rs.getString("direccion"))
                .telefono(rs.getString("telefono"))
                .fecha_nac(rs.getString("fecha_nac"))
                .build()
        ,nombre);
        return user;
    }

    @Override
    public void delete(Long id) {
        var rowUpdate = jdbcTemplate.update("""
            delete from user
            where id = ?
""",id);

    }

    @Override
    public List<User> getAll() {
        List<User> users = jdbcTemplate.query("""
                select * from user
                """,
                (rs,rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("fecha_nac")
                )

        );
        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        var rowsUpdate = jdbcTemplate.update(
                """
                update user
                set nombre = ?, direccion = ?, telefono = ?, fecha_nac = ?
            where id = ?;
            """, user.getNombre(),
                user.getDireccion(),
                user.getTelefono(),
                user.getFecha_nac(),
                user.getId()
        );
    }
}
