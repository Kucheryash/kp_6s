package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.entity.Company;
import project.entity.Key;
import project.entity.User;
import project.repository.CompanyRepository;
import project.repository.KeyRepository;
import project.repository.UserRepository;

@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KeyRepository keyRepository;

    @GetMapping("/reg-for-company")
    public String registrationCompany(@ModelAttribute("company") Company company){
        return "register-company";
    }

    @PostMapping("/register-company/{id}")
    public String registerCompany(@ModelAttribute("company") Company company, @PathVariable("id") long id){
        company.setUser(userRepository.getReferenceById(id));
        companyRepository.save(company);
        return "redirect:/home/"+id;
    }

    @PostMapping("/edit-company/{id}")
    public String editCompany(@ModelAttribute("key") Key key, @ModelAttribute("user") User user, @ModelAttribute("company") Company company, Model model, @PathVariable ("id") long id){
        user.setType(userRepository.findById(id).get().getType());
        userRepository.save(user);
        key.setRole(2);
        key.setUser(userRepository.findById(id).get());
        keyRepository.save(key);
        companyRepository.save(company);
        model.addAttribute("user", user);
        model.addAttribute("key", key);
        model.addAttribute("company", company);
        return "redirect:/company-acc";
    }
}
