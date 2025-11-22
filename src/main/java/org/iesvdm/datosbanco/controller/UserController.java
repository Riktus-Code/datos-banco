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

import java.sql.SQLException;
import java.util.List;


@RequestMapping("/inicio")
@Controller
public class UserController {

    private final UserDAO userDAO;
    private final HashUtil hashUtil;
    public UserController(UserDAO userDAO, HashUtil hashUtil) {
        this.userDAO = userDAO;
        this.hashUtil = hashUtil;
    }

    @GetMapping("")
    public String ini(Model  model) {
        List<User> users = userDAO.getAll();
        User user = new User();
        model.addAttribute("users", users);
        model.addAttribute("user", user);

        return "inicio";

    }

    @PostMapping("")
    public String post(Model  model, @ModelAttribute UserDAO userDAO, @RequestParam Long idUser, @RequestParam String userName, @RequestParam String direccion, @RequestParam String telefono, @RequestParam String fecha_nac) throws SQLException {
        List<User> users = userDAO.getAll();
        User user = User.builder().nombre(userName).direccion(direccion).telefono(telefono).fecha_nac(fecha_nac).build() ;
        User nuevoUser = userDAO.create(user);


        model.addAttribute("nuevoUser",nuevoUser);




        model.addAttribute("users", users);
        return "inicio";
    }

    //actualizar
    @GetMapping("/actualizar/{id}")
    public String actualizar(Model  model, @PathVariable Long idUser) {
        User user = userDAO.getById(idUser);
        model.addAttribute("userAlActualizador", user);
        return "actualizar";
    }
    @PostMapping("/actualizar")
    public String actualizado(Model model, @ModelAttribute User user) throws SQLException {
        userDAO.update(user);
        model.addAttribute("userActualizado", user);
        return "redirect:/inicio";

    }
    //buscar
    @GetMapping("/buscar")
    public String  buscar(Model  model, @ModelAttribute User user) {
        model.addAttribute("user", user);
        return "buscar";
    }

    @PostMapping("/buscar")
    public String postBuscar(Model  model, @ModelAttribute UserDAO userDAO, @RequestParam String userName) {
        User find = userDAO.findByUserName(userName);
        model.addAttribute("user", find);
        return "buscar";
    }
    //eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(Model  model, @PathVariable Long idUser) throws SQLException {
        userDAO.delete(idUser);
        return "redirect:/inicio";
    }
}
