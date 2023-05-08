package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.entity.*;
import project.repository.*;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/")
    public String home(Model model, @ModelAttribute("filtering") CarForFilter carForFilter){
        model.addAttribute("cars", carRepository.findAll());
        return "home";
    }

    @GetMapping("/home/{id}")
    public String loginHome(Model model, @PathVariable ("id") long id, @ModelAttribute("filtering") CarForFilter carForFilter){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("key", keyRepository.findById(id).get());
        return "home";
    }

    @GetMapping("/authorization")
    public String authorization(@ModelAttribute("key") Key key){
        return "login";
    }

    @PostMapping("/home")
    public String login(@ModelAttribute("key") Key key){
        Optional<Key> newKey = keyRepository.findByLoginAndPassword(key.getLogin(), key.getPassword());
        if(newKey.isEmpty()){
            return "redirect:/authorization";
        }
        return "redirect:/home/"+newKey.get().getId();
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user, @ModelAttribute("key") Key key){
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("user") User user, @ModelAttribute("key") Key key){
        Optional<Key> k = keyRepository.findByLogin(key.getLogin());
        if (k.isEmpty()) {
            userRepository.save(user);
            //customer
            if (user.getType().equals("customer")) {
                key.setRole(0);
                key.setUser(userRepository.getReferenceById(user.getId()));
                keyRepository.save(key);
                return "redirect:/home/" + user.getId();

                //seller
            } else if (user.getType().equals("seller")) {
                key.setRole(1);
                key.setUser(userRepository.getReferenceById(user.getId()));
                keyRepository.save(key);
                model.addAttribute("seller", new Seller());
                model.addAttribute("user", userRepository.findById(user.getId()).get());
                return "register-seller";

                //company
            } else if (user.getType().equals("company")) {
                key.setRole(2);
                key.setUser(userRepository.getReferenceById(user.getId()));
                keyRepository.save(key);
                model.addAttribute("company", new Company());
                model.addAttribute("user", userRepository.findById(user.getId()).get());
                return "register-company";
            }
        }else return "redirect:/registration";

        return "error";
    }

    @GetMapping("/account/{id}")
    public String account(Model model, @PathVariable ("id") long id){
        Optional<User> user = userRepository.findById(id);
        User u = userRepository.getReferenceById(id);
        if (user.get().getType().equals("customer")) {
            model.addAttribute("user", userRepository.findById(id).get());
            model.addAttribute("key", keyRepository.findByUser(u).get());
            return "user-acc";
        }
        else if (user.get().getType().equals("seller")) {
            model.addAttribute("seller", sellerRepository.findByUser(u).get());
            model.addAttribute("user", userRepository.findById(id).get());
            model.addAttribute("key", keyRepository.findByUser(u));
            return "seller-acc";
        }
        else if (user.get().getType().equals("company")) {
            model.addAttribute("company", companyRepository.findByUser(u).get());
            model.addAttribute("user", userRepository.findById(id).get());
            model.addAttribute("key", keyRepository.findByUser(u));
            return "company-acc";
        }
        return "error";
    }

    @PostMapping("/delete-acc/{id}")
    public String deleteAcc(@PathVariable("id") long id, Model model){
        userRepository.delete(userRepository.getReferenceById(id));
        model.addAttribute("cars", carRepository.findAll());
        return "redirect:/";
    }
}
