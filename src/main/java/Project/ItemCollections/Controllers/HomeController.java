package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Item.Tag;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.ItemRepository;
import Project.ItemCollections.Repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @GetMapping({"/", "/main"})
    public ModelAndView getHome() {
        ModelAndView mav = new ModelAndView("main");

        Iterable<Tag> tags = tagRepository.findAll();
        List<Map<String, String>> tagsToReturn = new ArrayList<>();
        for (Tag tag : tags) {
            Map<String, String> tagToReturn = new HashMap<>();
            tagToReturn.put("text", tag.getTagName());
            tagToReturn.put("weight", String.valueOf(tag.getItems().size()));
            tagsToReturn.add(tagToReturn);
        }
        List<Object[]> queryObjects = itemRepository.findLargestCollections();
        List<Collection> largestCollections = new ArrayList<>();
        List<Integer> itemCounts = new ArrayList<>();
        for (Object[] obj: queryObjects) {
            largestCollections.add(collectionRepository.getById( (Integer) obj[0] ));
            itemCounts.add( ((BigInteger)obj[1]).intValue() );
        }

        mav.addObject("latestItems", itemRepository.findLatestItems());
        mav.addObject("largestCollections", largestCollections);
        mav.addObject("itemCounts", itemCounts);
        mav.addObject("tags", tagsToReturn);
        return mav;
    }
}
