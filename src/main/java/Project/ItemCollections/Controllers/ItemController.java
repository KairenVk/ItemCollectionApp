package Project.ItemCollections.Controllers;


import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.ItemRepository;
import Project.ItemCollections.Repositories.TagRepository;
import Project.ItemCollections.Repositories.UserRepository;

import Project.ItemCollections.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class ItemController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemService itemService;

    @GetMapping("/collection/{id}/createItem")
    public ModelAndView createItemPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("itemForm");
        mav.addObject("itemTags", tagRepository.findAll());
        mav.addObject("collectionId", id);
        return mav;
    }

    @PostMapping("/collection/{id}/createItem")
    public String createItem(RedirectAttributes redirectAttributes, @ModelAttribute Item item, @PathVariable("id") Integer id, @RequestParam(value="tags[]") List<String> tags) {
        System.out.println(tags);
        itemService.addItem(item, id, tags);
        return "redirect:/collection/{id}/overview";


    }
}
