package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        boolean result = userService.createUser(user);

        if (result) {
            redirectAttributes.addFlashAttribute("message", "User created successfully! Login using your credentials below.");
            return "redirect:/login";
        }
        else {
            redirectAttributes.addFlashAttribute("message", "User with given credentials already exists!");
            return "redirect:/registration";
        }

    }
}
