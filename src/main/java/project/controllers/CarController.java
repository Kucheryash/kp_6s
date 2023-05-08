package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.entity.*;
import project.repository.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/info/{id}")
    public String infoCar(Model model, @PathVariable("id") long id){
        Car car = carRepository.getReferenceById(id);
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("comments", commentRepository.findAllByCar(car));
        model.addAttribute("key", null);
        List<Image> images = imageRepository.findAllByCar(car.getId());
        model.addAttribute("images", images);
        return "car-info";
    }

    @GetMapping("/{idkey}/info/{id}")
    public String infoCarMore(Model model, @PathVariable("idkey") long id_key, @PathVariable("id") long id, @ModelAttribute("comm") Comment comment){
        Car car = carRepository.getReferenceById(id);
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("key", keyRepository.findById(id_key).get());
        model.addAttribute("comments", commentRepository.findAllByCar(car));
        List<Image> images = imageRepository.findAllByCar(car.getId());
        model.addAttribute("images", images);

        if (car.getDealer().getType().equals("seller")) {
            User user = userRepository.getReferenceById(car.getDealer().getId());
            model.addAttribute("seller", sellerRepository.findByUser(user).get());
        }
        if (car.getDealer().getType().equals("company")) {
            User user = userRepository.getReferenceById(car.getDealer().getId());
            model.addAttribute("company", companyRepository.findByUser(user).get());
        }
        return "car-info";
    }

    @PostMapping("/add-comment/{id}/{idkey}")
    public String addComment(@ModelAttribute("comm") Comment comment, @PathVariable("idkey") long id_key, @PathVariable("id") long id){
        Comment com = new Comment();
        com.setComment(comment.getComment());
        com.setUser(keyRepository.findById(id_key).get().getUser());
        com.setCar(carRepository.findById(id).get());
        commentRepository.save(com);
        return "redirect:/"+id_key+"/info/"+id;
    }

    @GetMapping("/ads/{id}")
    public String favorite(Model model, @PathVariable ("id") long id){
        model.addAttribute("cars", carRepository.findAllByDealer(userRepository.getReferenceById(id)));
        model.addAttribute("key", keyRepository.findById(id).get());
        return "announcements";
    }

    @GetMapping("/add/{id}")
    public String add(Model model, @PathVariable("id") long id){
        model.addAttribute("car", new Car());
        model.addAttribute("user", userRepository.getReferenceById(id));
        return "add-car";
    }

    @PostMapping("/add-car/{id}")
    public String addCar(Model model, @ModelAttribute("car") Car car, @PathVariable("id") long id){
        List<Car> cars = carRepository.findAll();
        long i = cars.get(cars.size()-1).getId();
        car.setId(i+1);
        car.setDealer(userRepository.getReferenceById(id));
        car.setFav(0);
        carRepository.save(car);
        List<Car> newCars = carRepository.findAll();
        long new_id = newCars.get(newCars.size()-1).getId();
        model.addAttribute("countries", new Country());
        model.addAttribute("car", carRepository.getReferenceById(new_id));
        return "add-country";
    }

    @PostMapping("/add-country/{id}")
    public String addCountry(Model model, @ModelAttribute("countries") Country country, @PathVariable("id") long id, @ModelAttribute("images") MultipartFile[] images){
        List<Car> carList = carRepository.findAll();
        Optional<Country> cList = countryRepository.findAllByCountryAndRegionAndCity(country.getCountry(), country.getRegion(), country.getCity());
        long i;
        if (cList.isEmpty()){
            countryRepository.save(country);
            List<Country> c = countryRepository.findAll();
            i = c.get(c.size()-1).getId();
        }else {
            i = cList.get().getId();
        }
        Car car = carList.get(carList.size()-1);
        car.setCountry(countryRepository.getReferenceById(i));
        carRepository.save(car);
        model.addAttribute("images", images);
        model.addAttribute("car", car);
        return "add-photo";
    }

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/";

    @PostMapping("/add-photo/{id}")
    public String addPhoto(Model model, @PathVariable("id") long id, @RequestParam("images") MultipartFile[] images) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        List<Car> carList = carRepository.findAll();
        Car car = carList.get(carList.size()-1);
        boolean isPreview = true;
        for (MultipartFile image : images) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, image.getOriginalFilename());
            fileNames.append(image.getOriginalFilename()).append(", ");
            Files.write(fileNameAndPath, image.getBytes());

            Image newImage = new Image();
            newImage.setCar(car.getId());
            newImage.setPath("../../images/" + image.getOriginalFilename());
            imageRepository.save(newImage);

            if (isPreview) {
                car.setImage(newImage);
                isPreview = false;
            }
        }
        model.addAttribute("key", keyRepository.getReferenceById(car.getDealer().getId()));
        return "redirect:/home/" + car.getDealer().getId();
    }

    @PostMapping("/delete-car/{id}")
    public String deleteCar(@PathVariable("id") long id, Model model){
        long id_user = carRepository.findById(id).get().getDealer().getId();
        imageRepository.deleteAllByCar(carRepository.findById(id).get());
        carRepository.delete(carRepository.getReferenceById(id));
        return "redirect:/ads/"+id_user;
    }


    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable ("id") long id){
        model.addAttribute("car", carRepository.getReferenceById(id));
        return "edit-car";
    }

    @PostMapping("/edit-car/{id}")
    public String editCar(Model model, @ModelAttribute("car") Car car, @PathVariable("id") long id){
        car.setDealer(carRepository.findById(id).get().getDealer());
        car.setCountry(carRepository.findById(id).get().getCountry());
        car.setFav(carRepository.findById(id).get().getFav());
        car.setImage(carRepository.findById(id).get().getImage());
        carRepository.save(car);

        long id_dealer = car.getDealer().getId();
        model.addAttribute("cars", carRepository.findAllByDealer(userRepository.findById(id_dealer).get()));
        model.addAttribute("key", keyRepository.findById(id_dealer).get());
        return "redirect:/ads/"+id_dealer;
    }

    @PostMapping("/filter")
    public String filter(@ModelAttribute("filtering") CarForFilter carForFilter, Model model) {
        List<Car> cars = carRepository.findByBrandAndModelAndPriceBetween(carForFilter.getBrand(), carForFilter.getModel(), carForFilter.getMin_price(), carForFilter.getMax_price());
        model.addAttribute("key", null);
        model.addAttribute("cars", cars);
        model.addAttribute("filtering", carForFilter);
        return "home";
    }

    @PostMapping("/filter/{id}")
    public String filtering(@ModelAttribute("filtering") CarForFilter carForFilter, Model model, @PathVariable("id") long id) {
        List<Car> cars = carRepository.findByBrandAndModelAndPriceBetween(carForFilter.getBrand(), carForFilter.getModel(), carForFilter.getMin_price(), carForFilter.getMax_price());
        model.addAttribute("key", keyRepository.findById(id).get());
        model.addAttribute("cars", cars);
        model.addAttribute("filtering", carForFilter);
        return "home";
    }

}
