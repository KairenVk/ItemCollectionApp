package Project.ItemCollections.Controllers;

import Project.ItemCollections.Repositories.UserRepository;
import Project.ItemCollections.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ModelAndView adminPanel() {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }

    @RequestMapping(value="/admin/editUsers", method = RequestMethod.POST, params="action=block")
    public String blockUsers(@RequestParam(value="select") List<String> userUsernames) {
        userService.editUsers(userUsernames, "block");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=unblock")
    public String unblockUsers(@RequestParam(value="select") List<String> userUsernames) {
        userService.editUsers(userUsernames, "unblock");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=delete")
    public String deleteUsers(@RequestParam(value="select") List<String> userUsernames) {
        userService.editUsers(userUsernames, "delete");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=grantAdmin")
    public String grantAdminToUsers(@RequestParam(value="select") List<String> userUsernames) {
        userService.editUsers(userUsernames, "grantAdmin");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=revokeAdmin")
    public String revokeAdminFromUsers(@RequestParam(value="select") List<String> userUsernames) {
        userService.editUsers(userUsernames, "revokeAdmin");

        return "redirect:/admin";
    }
}
