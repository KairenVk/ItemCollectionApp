package Project.ItemCollections.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/main"})
    public String getHome() {
        return "main";
    }
}
