package Project.ItemCollections.Controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return ("login");
    }

    @RequestMapping(value="/logout")
    public String logoutAction(RedirectAttributes redirectAttributes) {
        SecurityContextHolder.clearContext();
        redirectAttributes.addFlashAttribute("message", "Logged out successfully!");
        return "redirect:/main";
    }
}
