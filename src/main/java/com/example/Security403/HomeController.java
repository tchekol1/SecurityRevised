package com.example.Security403;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String index(Model model){
//        String username=principal.getName();
//        model.addAttribute("user",userRepository.findByUsername(username));
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentuser=authentication.getName();
        User user1 = userRepository.findByUsername(currentuser);
        model.addAttribute("user1",user1);
        model.addAttribute("role1",roleRepository.findAllByUsername(currentuser));
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/admin")
    public String admin(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentuser=authentication.getName();
        User user1 = userRepository.findByUsername(currentuser);
        model.addAttribute("user1",user1);
        model.addAttribute("role1",roleRepository.findAllByUsername(currentuser));
        return "admin";
    }
    @PostMapping("/logout")
    public String logout(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentuser=authentication.getName();
        User user1 = userRepository.findByUsername(currentuser);
        model.addAttribute("user1",user1);
        model.addAttribute("role1",roleRepository.findAllByUsername(currentuser));
        return "redirect:/login?logout=true";
    }
    @GetMapping("/secure")
    public String secure(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentuser=authentication.getName();
        User user1 = userRepository.findByUsername(currentuser);
        model.addAttribute("user1",user1);
        model.addAttribute("role1",roleRepository.findAllByUsername(currentuser));
        return "secure";
    }
    @GetMapping("registration")
    public String register(Model model){
        model.addAttribute("user",new User());
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentuser=authentication.getName();
        User user1 = userRepository.findByUsername(currentuser);
        model.addAttribute("user1",user1);
       // String username=principal.getName();
       // model.addAttribute("user",userRepository.findByUsername(username));
        model.addAttribute("role1",roleRepository.findAllByUsername(currentuser));

        return "registration";
    }
    @PostMapping("registration")
    public String register(@Valid @ModelAttribute User user, BindingResult result, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentuser=authentication.getName();
        User user1 = userRepository.findByUsername(currentuser);
        model.addAttribute("user1",user1);
        model.addAttribute("role1",roleRepository.findAllByUsername(currentuser));

        if(result.hasErrors()){
            return "registration";
        }
else {
    model.addAttribute("message","Registration successful");
            user.setEnabled(true);
    Role role= new Role(user.getUsername(),"ROLE_USER");
            Set<Role>roleSet=new HashSet<>();
            userRepository.save(user);
            roleSet.add(role);
            roleRepository.save(role);}
            return "index";
    }

}

