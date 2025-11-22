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

@Slf4j
@RequestMapping("/inicio")
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;


    @GetMapping("")
    public String ini(Model  model) {
        List<User> users = userDAO.getAll();
        User user = new User();
        model.addAttribute("users", users);
        model.addAttribute("user", user);

        return "inicio";

    }

    @PostMapping("")
    public String post(Model  model,  @RequestParam String nombre, @RequestParam String direccion, @RequestParam String telefono, @RequestParam String fecha_nac) throws SQLException {
        List<User> users = userDAO.getAll();
        User user = User.builder().nombre(nombre).direccion(direccion).telefono(telefono).fecha_nac(fecha_nac).build() ;
        User nuevoUser = userDAO.create(user);


        model.addAttribute("nuevoUser",nuevoUser);



        model.addAttribute("user", new User());
        model.addAttribute("users", users);
        return "inicio";
    }

    //actualizar
    @GetMapping("/actualizar/{idUser}")
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
    public String postBuscar(Model  model,  @ModelAttribute User user) throws SQLException {
        User find = userDAO.findByUserName(user.getNombre());
        model.addAttribute("user", find);
        return "buscar";
    }
    //eliminar
    @GetMapping("/eliminar/{idUser}")
    public String eliminar( @PathVariable Long idUser) throws SQLException {
        userDAO.delete(idUser);
        return "redirect:/inicio";
    }
}
