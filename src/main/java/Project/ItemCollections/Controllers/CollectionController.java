package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionTopics;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.CollectionTopicsRepository;
import Project.ItemCollections.Repositories.UserRepository;
import Project.ItemCollections.Services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CollectionController {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    CollectionTopicsRepository collectionTopicsRepository;

    @GetMapping({"/collections"})
    public ModelAndView getCollectionsPage() {
        ModelAndView modelAndView = new ModelAndView("collections");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());
            List<Collection> collectionList = collectionRepository.findByUserId(loggedInUser.getId());
            modelAndView.addObject("collections", collectionList);
            modelAndView.addObject("topics", collectionTopicsRepository.findAll());
        }
        return modelAndView;
    }

    @GetMapping("/collections/create")
    public ModelAndView createCollectionPage() {
        ModelAndView mav = new ModelAndView("collectionForm");
        mav.addObject("collectionTopics", collectionTopicsRepository.findAll());
        return mav;
    }

    @PostMapping("/collections/create")
    public String createCollection(RedirectAttributes redirectAttributes, @ModelAttribute Collection collection, @RequestParam(value="topicName") String topicName) {
        collectionService.createCollection(collection, topicName);
        redirectAttributes.addFlashAttribute("message", "Collection has been created!");
        return "redirect:/collections";
    }

    @GetMapping("/collections/{id}/edit")
    public ModelAndView editCollectionPage(@PathVariable("id") Integer id) {
        System.out.println("Testing");
        ModelAndView mav = new ModelAndView("collectionEditForm");
        Collection targetCollection = collectionRepository.getById(id);
        mav.addObject("collection", targetCollection);
        mav.addObject("collectionTopics", collectionTopicsRepository.findAll());
        return mav;
    }

    @PostMapping("/collections/{id}/edit")
    public String editCollection(RedirectAttributes redirectAttributes, @ModelAttribute Collection collection, @RequestParam(value="topicName") String topicName, @PathVariable("id") Integer id) {
        collectionService.editCollection(collection, id, topicName);
        redirectAttributes.addFlashAttribute("message", "Collection has been updated!");
        return "redirect:/collections";
    }

    @GetMapping("/collections/{id}/delete")
    public String deleteCollection(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
        collectionService.deleteCollection(id);
        redirectAttributes.addFlashAttribute("message", "Collection has been deleted!");
        return "redirect:/collections";
    }
}
