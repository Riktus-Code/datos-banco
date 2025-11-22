package org.iesvdm.datosbanco.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.datosbanco.dao.UserDAO;
import org.iesvdm.datosbanco.model.User;
import org.iesvdm.datosbanco.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Service
@RequestMapping("/inicio")
@RestController
public class UserController {

    private final UserDAO userDAO;
    private final HashUtil hashUtil;


    public UserController(UserDAO userDAO, HashUtil hashUtil) {
        this.userDAO = userDAO;
        this.hashUtil = hashUtil;
    }

    @GetMapping("")
    public String ini(Model  model, @ModelAttribute User user) {

        model.addAttribute("user", user);

        return "inicio";

    }

    @PostMapping("")
    public String post(Model  model, @ModelAttribute UserDAO userDAO) {
        List<User> users = userDAO.getAll();

        model.addAttribute("users", users);
        return "inicio";
    }
}
