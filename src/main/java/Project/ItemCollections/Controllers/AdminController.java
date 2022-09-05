package Project.ItemCollections.Controllers;

import Project.ItemCollections.Repositories.UserRepository;
import Project.ItemCollections.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String blockUsers(@RequestParam(value="select") List<String> userUsernames, RedirectAttributes redirectAttributes) {
        userService.editUsers(userUsernames, "block");
        redirectAttributes.addFlashAttribute("message", "Selected users have been blocked!");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=unblock")
    public String unblockUsers(@RequestParam(value="select") List<String> userUsernames, RedirectAttributes redirectAttributes) {
        userService.editUsers(userUsernames, "unblock");
        redirectAttributes.addFlashAttribute("message", "Selected users have been unblocked!");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=delete")
    public String deleteUsers(@RequestParam(value="select") List<String> userUsernames, RedirectAttributes redirectAttributes) {
        userService.editUsers(userUsernames, "delete");
        redirectAttributes.addFlashAttribute("message", "Selected users have been deleted!");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=grantAdmin")
    public String grantAdminToUsers(@RequestParam(value="select") List<String> userUsernames, RedirectAttributes redirectAttributes) {
        userService.editUsers(userUsernames, "grantAdmin");
        redirectAttributes.addFlashAttribute("message", "Added selected users to admin group!");
        return "redirect:/admin";
    }

    @RequestMapping(value="/admin/editUsers", method=RequestMethod.POST, params="action=revokeAdmin")
    public String revokeAdminFromUsers(@RequestParam(value="select") List<String> userUsernames, RedirectAttributes redirectAttributes) {
        userService.editUsers(userUsernames, "revokeAdmin");
        redirectAttributes.addFlashAttribute("message", "Removed selected users from admin group!");
        return "redirect:/admin";
    }
}
