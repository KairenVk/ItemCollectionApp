package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user) {
        userService.createUser(user);
        return "redirect:/registration";
    }
}
