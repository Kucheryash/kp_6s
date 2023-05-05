package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.entity.Key;
import project.entity.Seller;
import project.entity.User;
import project.repository.KeyRepository;
import project.repository.SellerRepository;
import project.repository.UserRepository;

@Controller
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KeyRepository keyRepository;

    @GetMapping("/reg-for-seller")
    public String registrationSeller(@ModelAttribute("seller") Seller seller){
        return "register-seller";
    }

    @PostMapping("/register-seller/{id}")
    public String registerSeller(@ModelAttribute("seller") Seller seller, @PathVariable ("id") long id){
        seller.setUser(userRepository.getReferenceById(id));
        sellerRepository.save(seller);
        return "redirect:/home/"+id;
    }

    @PostMapping("/edit-seller/{id}")
    public String editSeller(@ModelAttribute("key") Key key, @ModelAttribute("user") User user, @ModelAttribute("seller") Seller seller, Model model, @PathVariable ("id") long id){
        user.setType(userRepository.findById(id).get().getType());
        userRepository.save(user);
        key.setRole(1);
        key.setUser(userRepository.findById(id).get());
        keyRepository.save(key);
        sellerRepository.save(seller);
        model.addAttribute("user", user);
        model.addAttribute("key", key);
        model.addAttribute("seller", seller);
        return "redirect:/seller-acc";
    }
}
