package org.iesvdm.datosbanco.dao;

import org.iesvdm.datosbanco.model.User;

public interface UserDAO {

    User create(User user);

    User findByUserName(String username);

    void delete (User user);


}
