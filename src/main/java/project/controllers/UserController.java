package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.configs.Configurations;
import project.entity.Company;
import project.entity.Key;
import project.entity.Seller;
import project.entity.User;
import project.repository.KeyRepository;
import project.repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user, @ModelAttribute("key") Key key){
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("user") User user, @ModelAttribute("key") Key key){
        if(key.getLogin().equals("") || key.getPassword().equals("") || user.getEmail().equals("") || user.getType().equals("")){
            return "redirect:/registration";
        }
        userRepository.save(user);
        //customer
        if (user.getType().equals("customer")){
            key.setRole(0);
            key.setUser(userRepository.getReferenceById(user.getId()));
            keyRepository.save(key);
            Configurations.USER = user.getId();
            return "home";

        //seller
        } else if (user.getType().equals("seller")) {
            key.setRole(1);
            key.setUser(userRepository.getReferenceById(user.getId()));
            keyRepository.save(key);
            Configurations.USER = user.getId();
            model.addAttribute("seller", new Seller());
            model.addAttribute("user", userRepository.findById(user.getId()).get());
            return "register-seller";

        //company
        } else {
            key.setRole(2);
            key.setUser(userRepository.getReferenceById(user.getId()));
            keyRepository.save(key);
            Configurations.USER = user.getId();
            model.addAttribute("company", new Company());
            model.addAttribute("user", userRepository.findById(user.getId()).get());
            return "register-company";
        }
    }
}
