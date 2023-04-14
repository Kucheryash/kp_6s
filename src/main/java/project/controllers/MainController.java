package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.configs.Configurations;
import project.entity.Key;
import project.entity.User;
import project.repository.KeyRepository;

import java.util.Optional;

@Controller
public class MainController {
//    @Autowired
//    private CarRepository carRepository;
    @Autowired
    private KeyRepository keyRepository;

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

    @GetMapping("/authorization")
    public String authorization(@ModelAttribute("key") Key key){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("key") Key key){
        Optional<Key> newKey = keyRepository.findByLoginAndPassword(key.getLogin(), key.getPassword());
        if(newKey.isEmpty()){
            return "redirect:/authorization";
        }
        Configurations.USER = newKey.get().getUser().getId();
        return "home";
    }

}
