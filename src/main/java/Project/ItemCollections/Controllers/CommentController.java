package Project.ItemCollections.Controllers;

import Project.ItemCollections.Entities.Item.ItemsComments;
import Project.ItemCollections.Repositories.ItemRepository;
import Project.ItemCollections.Repositories.ItemsCommentsRepository;

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
import java.util.Map;

@Controller
public class CommentController
{
    @Autowired
    private ItemsCommentsRepository itemsCommentsRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping({"/comments"})
    public ResponseEntity<String> getComments(
            @RequestParam(value="itemId", required = false, defaultValue = "") Integer itemId,
            @RequestParam(value="latestCommentId", required = false, defaultValue = "") Integer itemCommentId
            ) throws JsonProcessingException
    {
        List<ItemsComments> itemsComments = itemsCommentsRepository.findAllByItemAndIdGreaterThan(
            itemRepository.getById(itemId),
            itemCommentId
        );

        List<Map<String, String>> preparedComments = new ArrayList<>();
        for (ItemsComments comment : itemsComments) {
            Map<String, String> preparedComment = new HashMap<>();
            preparedComment.put("id", String.valueOf(comment.getId()));
            preparedComment.put("author", comment.getAuthor().getUsername());
            preparedComment.put("comment", comment.getComment());
            preparedComments.add(preparedComment);
        }

        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(preparedComments), HttpStatus.OK);
    }
}
