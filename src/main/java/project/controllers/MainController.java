package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.configs.Configurations;
import project.entity.Car;
import project.entity.Key;
import project.entity.User;
import project.repository.CarRepository;
import project.repository.KeyRepository;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private KeyRepository keyRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "home";
    }
    @GetMapping("/home/{id}")
    public String loginhome(Model model, @PathVariable ("id") long id){
        model.addAttribute("cars", carRepository.findAll());
//        Car car = new Car();
//        int year = car.get
        model.addAttribute("key", keyRepository.findById(id).get());
        return "home";
    }

    @GetMapping("/authorization")
    public String authorization(@ModelAttribute("key") Key key){
        return "login";
    }

    @PostMapping("/home")
    public String login(@ModelAttribute("key") Key key, Model model){
        Optional<Key> newKey = keyRepository.findByLoginAndPassword(key.getLogin(), key.getPassword());
        if(newKey.isEmpty()){
            return "redirect:/authorization";
        }
        return "redirect:/home/"+newKey.get().getId();
    }

}
