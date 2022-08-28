package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.*;
import Project.ItemCollections.Services.AuthService;
import Project.ItemCollections.Services.CollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Transactional
public class CollectionController {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionTopicsRepository collectionTopicsRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FieldTypesRepository fieldTypesRepository;

    @GetMapping("/collections")
    public ModelAndView getCollectionsPage() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
        ModelAndView modelAndView = new ModelAndView("collections");
        List<Collection> collectionList = collectionRepository.findByCollectionOwner(user);
        modelAndView.addObject("collections", collectionList);
        return modelAndView;
    }

    @GetMapping("/collection/create")
    public ModelAndView createCollectionPage() {
        ModelAndView modelAndView = new ModelAndView("collectionForm");
        modelAndView.addObject("collectionTopics", collectionTopicsRepository.findAll());
        modelAndView.addObject("fieldTypes", fieldTypesRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/collection/create")
    public String createCollection(RedirectAttributes redirectAttributes,
                                   @ModelAttribute Collection collection,
                                   @RequestParam(value="topicName") String topicName,
                                   @RequestParam(value="fieldNames[]", required = false) List<String> fieldNames,
                                   @RequestParam(value="customField[]", required = false) List<String> customFields,
                                   @RequestParam(value="image", required = false) MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        collectionService.createCollection(collection, topicName, fieldNames, customFields, file);
        redirectAttributes.addFlashAttribute("message", "Collection has been created!");
        return "redirect:/collections";
    }

    @GetMapping("/collection/{id}/edit")
    public ModelAndView editCollectionPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("collectionEditForm");
        Collection targetCollection = collectionRepository.getById(id);
        mav.addObject("collection", targetCollection);
        mav.addObject("collectionTopics", collectionTopicsRepository.findAll());
        return mav;
    }

    @PostMapping("/collection/{id}/edit")
    public String editCollection(RedirectAttributes redirectAttributes, @ModelAttribute Collection collection, @RequestParam(value="topicName") String topicName, @PathVariable("id") Integer id) {
        collectionService.editCollection(collection, id, topicName);
        redirectAttributes.addFlashAttribute("message", "Collection has been updated!");
        return "redirect:/collections";
    }

    @GetMapping("/collection/{id}/delete")
    public String deleteCollection(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
        collectionService.deleteCollection(id);
        redirectAttributes.addFlashAttribute("message", "Collection has been deleted!");
        return "redirect:/collections";
    }

    @GetMapping("/collection/{id}/overview")
    public ModelAndView collectionOverviewPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("collectionItems");
        Collection collection = collectionRepository.getById(id);
        mav.addObject("collectionItems", itemRepository.findByItemCollection(collection));
        mav.addObject("collection", collectionRepository.getById(id));
        return mav;
    }

    @GetMapping("/usersCollections")
    public ModelAndView usersCollectionsPage() {
        ModelAndView mav = new ModelAndView("usersCollections");
        mav.addObject("collections", collectionRepository.findAll());
        return mav;
    }
}
