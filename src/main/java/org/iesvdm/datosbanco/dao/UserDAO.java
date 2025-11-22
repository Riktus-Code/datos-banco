package org.iesvdm.datosbanco.dao;

import org.iesvdm.datosbanco.model.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface UserDAO {

    User create(User user) throws SQLException;

    User findByUserName(String username);

    void delete (Long id) throws SQLException;

    List<User> getAll();

    void update(User user) throws SQLException;

}
