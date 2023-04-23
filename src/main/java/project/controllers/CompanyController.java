package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.configs.Configurations;
import project.entity.Company;
import project.repository.CompanyRepository;
import project.repository.UserRepository;

@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reg-for-company")
    public String registrationCompany(@ModelAttribute("company") Company company){
        return "register-company";
    }

    @PostMapping("/register-company")
    public String registerCompany(@ModelAttribute("company") Company company, @PathVariable("id") long id){
        if(company.equals("")){
            return "redirect:/reg-for-company";
        }
        company.setUser(userRepository.getReferenceById(id));
        companyRepository.save(company);
        return "home";
    }
}
