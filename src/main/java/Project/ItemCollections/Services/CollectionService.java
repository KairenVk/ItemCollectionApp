package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionTopics;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.CollectionTopicsRepository;
import Project.ItemCollections.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionTopicsRepository collectionTopicsRepository;

    @Autowired
    private UserRepository userRepository;
    public void createCollection(Collection collection, String topicName) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());

        CollectionTopics topic = collectionTopicsRepository.findByTopicName(topicName);

        Collection n = new Collection();

        n.setUser(loggedInUser);
        n.setName(collection.getName());
        n.setCollectionTopic(topic);
        n.setDescription(collection.getDescription());
        collectionRepository.save(n);
    }

    public void editCollection(Collection collection, Integer id, String topicName) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());

        CollectionTopics topic = collectionTopicsRepository.findByTopicName(topicName);
        Collection n = collectionRepository.getById(id);
        n.setDescription(collection.getDescription());
        n.setName(collection.getName());
        n.setCollectionTopic(topic);
        collectionRepository.save(n);
    }

    public void deleteCollection(Integer id) {
        collectionRepository.deleteById(id);
    }
}