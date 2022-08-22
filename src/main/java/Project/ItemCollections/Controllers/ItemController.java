package Project.ItemCollections.Controllers;


import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionCustomFieldsData;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Repositories.*;

import Project.ItemCollections.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionCustomFieldsDataRepository collectionCustomFieldsDataRepository;

    @GetMapping("/collection/{id}/createItem")
    public ModelAndView createItemPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("itemForm");
        Collection collection = collectionRepository.getById(id);
        Set<CollectionCustomFieldsData> customFields = collectionCustomFieldsDataRepository.findByCollection(collection);

        mav.addObject("itemTags", tagRepository.findAll());
        mav.addObject("collectionId", id);
        mav.addObject("customFields", customFields);
        return mav;
    }

    @PostMapping("/collection/{id}/createItem")
    public String createItem(RedirectAttributes redirectAttributes,
                             @ModelAttribute Item item,
                             @PathVariable("id") Integer id,
                             @RequestParam(value="tags[]") List<String> tags,
                             @RequestParam(value="customFieldsValues[]") List<String> customFieldsValues,
                             @RequestParam(value="customFieldsNames[]") List<String> customFieldsNames) {
        System.out.println(customFieldsValues);
        System.out.println(customFieldsNames);
        itemService.addItem(item, id, tags, customFieldsNames, customFieldsValues);
        return "redirect:/collection/{id}/overview";


    }

    @GetMapping("/item/{id}/overview")
    public ModelAndView viewItemPage(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("item");
        modelAndView.addObject("item", itemRepository.getById(id));
        return modelAndView;
    }
}
