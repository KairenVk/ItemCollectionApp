package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.CollectionTopicsRepository;
import Project.ItemCollections.Repositories.FieldTypesRepository;
import Project.ItemCollections.Repositories.ItemRepository;
import Project.ItemCollections.Services.AuthService;
import Project.ItemCollections.Services.CollectionService;
import Project.ItemCollections.Services.HtmlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CollectionService collectionService;

    @Autowired
    private CollectionTopicsRepository collectionTopicsRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FieldTypesRepository fieldTypesRepository;

    @Autowired
    private AuthService authService;

    @GetMapping("/collections")
    public ModelAndView getCollectionsPage() {
        HtmlServiceImpl markdownToHtml = new HtmlServiceImpl();
        User loggedInUser = authService.getLoggedUser();
        ModelAndView modelAndView = new ModelAndView("collections");
        List<Collection> collectionList = collectionRepository.findByCollectionOwner(loggedInUser);
        modelAndView.addObject("collections", collectionList);
        modelAndView.addObject("markdownToHtml", markdownToHtml);
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
        collectionService.createCollection(collection, topicName, fieldNames, customFields, file);
        redirectAttributes.addFlashAttribute("message", "Collection has been created!");
        return "redirect:/collections";
    }

    @GetMapping("/collection/{id}/edit")
    public ModelAndView editCollectionPage(@PathVariable("id") Integer collectionId) {
        ModelAndView mav = new ModelAndView("collectionEditForm");
        Collection targetCollection = collectionRepository.getById(collectionId);
        mav.addObject("collection", targetCollection);
        mav.addObject("collectionTopics", collectionTopicsRepository.findAll());
        return mav;
    }

    @PostMapping("/collection/{id}/edit")
    public String editCollection(RedirectAttributes redirectAttributes, @ModelAttribute Collection collection, @RequestParam(value="topicName") String topicName, @PathVariable("id") Integer collectionId) {
        collectionService.editCollection(collection, collectionId, topicName);
        redirectAttributes.addFlashAttribute("message", "Collection has been updated!");
        return "redirect:/collection/{id}/overview";
    }

    @GetMapping("/collection/{id}/delete")
    public String deleteCollection(RedirectAttributes redirectAttributes, @PathVariable("id") Integer collectionId) {
        collectionService.deleteCollection(collectionId);
        redirectAttributes.addFlashAttribute("message", "Collection has been deleted!");
        return "redirect:/collections";
    }

    @GetMapping("/collection/{id}/overview")
    public ModelAndView collectionOverviewPage(@PathVariable("id") Integer collectionId) {
        ModelAndView mav = new ModelAndView("collectionItems");
        HtmlServiceImpl markdownToHtml = new HtmlServiceImpl();
        Collection collection = collectionRepository.getById(collectionId);
        mav.addObject("collectionItems", itemRepository.findByItemCollection(collection));
        mav.addObject("collection", collection);
        mav.addObject("markdownToHtml", markdownToHtml);
        return mav;
    }

    @GetMapping("/usersCollections")
    public ModelAndView usersCollectionsPage() {
        ModelAndView mav = new ModelAndView("usersCollections");
        HtmlServiceImpl markdownToHtml = new HtmlServiceImpl();
        mav.addObject("markdownToHtml", markdownToHtml);
        mav.addObject("collections", collectionRepository.findAll());

        return mav;
    }
}
