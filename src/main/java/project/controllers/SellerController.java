package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.entity.Seller;
import project.repository.SellerRepository;
import project.repository.UserRepository;

@Controller
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reg-for-seller")
    public String registrationSeller(@ModelAttribute("seller") Seller seller){
        return "register-seller";
    }

    @PostMapping("/register-seller/{id}")
    public String registerSeller(@ModelAttribute("seller") Seller seller, @PathVariable ("id") long id)
    {
        if(seller.equals("")){
            return "redirect:/reg-for-seller";
        }
        seller.setUser(userRepository.getReferenceById(id));
        sellerRepository.save(seller);
        return "home";
    }
}
