package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.Item.Tag;
import Project.ItemCollections.Repositories.TagRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class TagController
{
    @Autowired
    TagRepository tagRepository;

    @GetMapping({"/tags"})
    public ResponseEntity<String> getTags(@RequestParam(value="term", required = false, defaultValue = "") String term) throws JsonProcessingException
    {
        Iterable<Tag> tags = tagRepository.findAllByTagName(term);

        List<String> tagsToReturn = new ArrayList<>();
        for (Tag tag : tags) {
            tagsToReturn.add(tag.getTagName());
        }

        ObjectMapper mapper = new ObjectMapper();

        return new ResponseEntity<>(mapper.writeValueAsString(tagsToReturn), HttpStatus.OK);
    }
}
