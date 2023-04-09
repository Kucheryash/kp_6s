package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.entity.Key;
import project.entity.User;

@Controller
public class UserController {

    @GetMapping("/registration")
    public String registration(@ModelAttribute("key") Key key){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, @ModelAttribute("key") Key key){
//        Optional<Key> newKey = keyRepository.findByLoginAndPassword(key.getLogin(), key.getPassword());
//        if(newKey.isEmpty()){
//            return "redirect:/authorization";
//        }
//        Configurations.USER = newKey.get().getUser().getId();
        return "home";
    }
}
