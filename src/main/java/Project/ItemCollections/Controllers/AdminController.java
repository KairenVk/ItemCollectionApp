package Project.ItemCollections.Controllers;

import Project.ItemCollections.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public ModelAndView adminPanel() {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }
}
