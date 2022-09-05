package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionsFields;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.ItemsComments;
import Project.ItemCollections.Repositories.*;
import Project.ItemCollections.Services.AuthService;
import Project.ItemCollections.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionsFieldsRepository collectionsFieldsRepository;

    @Autowired
    private ItemsCommentsRepository itemsCommentsRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsersLikesRepository usersLikesRepository;

    @GetMapping("/collection/{id}/createItem")
    public ModelAndView createItemPage(@PathVariable("id") Integer id) {
            ModelAndView mav = new ModelAndView("itemForm");
            Collection collection = collectionRepository.getById(id);
            List<CollectionsFields> customFields = collectionsFieldsRepository.findByCollection(collection);
            mav.addObject("itemTags", tagRepository.findAll());
            mav.addObject("item", null);
            mav.addObject("customFieldsTitles", customFields);
            mav.addObject("collectionId", id);
            return mav;
    }

    @PostMapping("/collection/{collectionId}/createItem")
    public String createItem(RedirectAttributes redirectAttributes,
                             @ModelAttribute Item item,
                             @PathVariable(value="collectionId") Integer id,
                             @RequestParam(value="tags[]", required = false) List<String> tags,
                             @RequestParam(value="customFieldsValues[]", required = false) List<String> customFieldsValues,
                             @RequestParam(value="customFieldsNames[]", required = false) List<String> customFieldsNames,
                             @RequestParam(value="image", required = false) MultipartFile file) {
        itemService.createItem(item, id, tags, customFieldsNames, customFieldsValues, file);
        redirectAttributes.addFlashAttribute("message", "Item has been created!");
        return "redirect:/collection/{collectionId}/overview";
    }

    @GetMapping("/item/{id}/overview")
    public ModelAndView viewItemPage(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("item");

        List<ItemsComments> itemsComments = itemsCommentsRepository.findByItem(itemRepository.getById(id));

        Integer latestCommentId = 0;
        if (itemsComments.size() > 0)
        {
            latestCommentId = itemsComments.stream().max(Comparator.comparing(ItemsComments::getId)).get().getId(); // Highest comment ID for retrieving new comments purposes
        }
        System.out.println(itemRepository.getById(id).getImageUrl());
        modelAndView.addObject("item", itemRepository.getById(id));
        modelAndView.addObject("comments", itemsComments);
        modelAndView.addObject("latestCommentId", latestCommentId);
        modelAndView.addObject("likes", usersLikesRepository.findUserWhoLikedByLikedItem(itemRepository.getById(id)));
        modelAndView.addObject("likedByUser", usersLikesRepository.findByLikedItemAndUserWhoLiked(itemRepository.getById(id), authService.getLoggedUser()));

        return modelAndView;
    }

    @GetMapping("/item/{id}/overview/likeItem")
    public String likeItem(@PathVariable("id") Integer id) {
        itemService.likeItem(id);
        return "redirect:/item/{id}/overview";
    }

    @GetMapping("/item/{id}/overview/dislikeItem")
    public String dislikeItem(@PathVariable("id") Integer id) {
        itemService.dislikeItem(id);
        return "redirect:/item/{id}/overview";
    }

    @GetMapping("/collection/{collectionId}/item/{itemId}/edit")
    public ModelAndView editItemPage(@PathVariable("itemId") Integer itemId, @PathVariable("collectionId") Integer collectionId) {
        ModelAndView mav = new ModelAndView("itemForm");
        Item item = itemRepository.getById(itemId);
        Collection collection = item.getItemCollection();
        mav.addObject("item", item);
        mav.addObject("itemTags", tagRepository.findAll());
        mav.addObject("collectionId", collectionId);
        return mav;
    }

    @PostMapping("/collection/{collectionId}/item/{itemId}/edit")
    public String editItem(@ModelAttribute Item item,
                           @PathVariable("collectionId") Integer collectionId,
                           @PathVariable("itemId") Integer itemId,
                           @RequestParam(value="tags[]", required = false) List<String> tags,
                           @RequestParam(value="customFieldsValues[]", required = false) List<String> customFieldsValues,
                           @RequestParam(value="customFieldsNames[]", required = false) List<String> customFieldsNames,
                           @RequestParam(value="image", required = false) MultipartFile file,
                           RedirectAttributes redirectAttributes) {
        itemService.editItem(item, itemId, collectionId, tags, customFieldsNames, customFieldsValues, file);
        redirectAttributes.addFlashAttribute("message", "Item has been updated!");
        return("redirect:/item/{itemId}/overview");
    }

    @PostMapping("item/{id}/overview/addComment")
    public String addComment(@PathVariable("id") Integer itemId,
                             @RequestParam("comment") String comment) {
        itemService.addComment(comment, itemId);
        return "redirect:/item/{id}/overview";
    }

    @GetMapping("collection/{collectionId}/item/{itemId}/delete")
    public String deleteItem(@PathVariable("itemId") Integer itemId, RedirectAttributes redirectAttributes){
        itemService.deleteItem(itemId);
        redirectAttributes.addFlashAttribute("message", "Item has been deleted!");
        return ("redirect:/collection/{collectionId}/overview");
    }
}