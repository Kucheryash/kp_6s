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

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-favorite/{id}/{idkey}")
    public String addFavorite(@PathVariable("idkey") long id_key, @PathVariable("id") long id){
        Favorite favorite = new Favorite();
        favorite.setUser(keyRepository.findById(id_key).get().getUser());
        favorite.setCar(carRepository.findById(id).get());
        favorite.setDealer(carRepository.findById(id).get().getDealer().getId());
        favoriteRepository.save(favorite);
        Car car = carRepository.getReferenceById(id);
        car.setFav(car.getFav()+1);
        carRepository.save(car);
        return "redirect:/home/"+id_key;
    }

    @PostMapping("/del-favorite/{id}/{idkey}")
    public String delFavorite(@PathVariable("idkey") long id_key, @PathVariable("id") long id){
        Car car = carRepository.getReferenceById(id);
        User user = userRepository.getReferenceById(id_key);
        List<Favorite> favorite = favoriteRepository.findByCarAndUser(car, user);
        Favorite fav = favoriteRepository.getReferenceById(favorite.get(0).getId());
        favoriteRepository.delete(fav);
        return "redirect:/favorite/"+id_key;
    }

    @GetMapping("/favorite/{id}")
    public String favorite(Model model, @PathVariable ("id") long id){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("key", keyRepository.findById(id).get());
        model.addAttribute("favorite", favoriteRepository.findAllByUser(userRepository.findById(id).get()));
        return "liked";
    }

    @PostMapping("/edit-user/{id}")
    public String editUser(@ModelAttribute("key") Key key, @ModelAttribute("user") User user, Model model, @PathVariable ("id") long id){
        user.setType(userRepository.findById(id).get().getType());
        userRepository.save(user);
        key.setRole(0);
        key.setUser(userRepository.findById(id).get());
        keyRepository.save(key);
        model.addAttribute("key", key);
        model.addAttribute("user", user);
        return "user-acc";
    }
}
