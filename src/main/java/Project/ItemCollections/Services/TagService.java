package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.ItemTags;
import Project.ItemCollections.Entities.Item.Tag;
import Project.ItemCollections.Repositories.ItemTagsRepository;
import Project.ItemCollections.Repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemTagsRepository itemTagsRepository;

    public void addTagsToItem(Item item, List<String> tags) {
        if (tags != null) {
            for (String tag : tags) {
                if (tag.isEmpty())
                    continue;
                Tag m = tagRepository.findByTagName(tag);
                if (m == null) {
                    m = createNewTag(tag);
                }
                ItemTags newTag = new ItemTags();
                newTag.setItemTag(m);
                newTag.setTaggedItem(item);
                itemTagsRepository.save(newTag);
            }
        }
    }

    public void editItemTags(Item item, List<String> tags) {
        itemTagsRepository.deleteByTaggedItem(item);
        if (tags != null)
            addTagsToItem(item, tags);
    }

    public Tag createNewTag(String tagName) {
        Tag newTag = new Tag();
        newTag.setTagName(tagName);
        tagRepository.save(newTag);
        return newTag;
    }
}
