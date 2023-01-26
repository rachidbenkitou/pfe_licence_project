package com.example.demo.user.controller;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.entretien.pdf.EntretienPDFExporter;
import com.example.demo.entretien.pdf.EntretienPDFExporter1Element;
import com.example.demo.equipement.entity.Equipement;
import com.example.demo.opérateur.entity.Operateur;
import com.example.demo.responsable_atelier.entity.ResponsableAtelier;
import com.example.demo.role.entity.Role;
import com.example.demo.role.service.RoleService;
import com.example.demo.user.entity.User;
import com.example.demo.user.pdf.UserPDFExporter;
import com.example.demo.user.service.UserService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/list")
    public String showEntretiensList(Model model){

        List<User> userList=userService.findAll();
        model.addAttribute("userList",userList);

        return "user/list-user";

    }

    @GetMapping("/form")
    public String showFormForAdd(Model model){

        User user=new User();
        model.addAttribute("user",user);

        List<Role> roleList=roleService.findAll();
        model.addAttribute("roleList",roleList);

        return "user/form-user";
    }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/list-user";
        }
        else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.save(user);
            return "redirect:/users/list";

        }

    }


    @GetMapping("/delete/{userId}")
    public String deleteEntretien(@PathVariable Long userId){

        userService.deleteById(userId);

        return "redirect:/users/list";
    }




    @GetMapping("/edit/{userId}")
    public String showEditForm(@PathVariable  Long userId,Model model){
        User user=userService.findById(userId);
        model.addAttribute("user",user);
        List<Role> roleList=roleService.findAll();
        model.addAttribute("roleList",roleList);

        return "user/form-user";
    }

    /*
    @GetMapping("/export/pdf/{userId}")
    public void exportToPDF1Element(HttpServletResponse response , @PathVariable  Long userId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        User user =userService.findById(userId);

        EntretienPDFExporter1Element exporter = new EntretienPDFExporter1Element(user);
        exporter.export(response);

    }

     */
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<User> userList =userService.findAll();

        UserPDFExporter exporter = new UserPDFExporter(userList);
        exporter.export(response);

    }

    @GetMapping("/search")
    public String searchByType(@RequestParam("username") String username,Model model){

        if(username.trim().isEmpty()){
            return "redirect:/users/list";
        }else {
            List<User> userList=userService.searchBy(username);

            model.addAttribute("userList",userList);

            return "user/list-user";
        }
    }
}
