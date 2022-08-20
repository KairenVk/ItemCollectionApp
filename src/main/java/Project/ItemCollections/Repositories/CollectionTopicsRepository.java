package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.CollectionTopics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CollectionTopicsRepository extends CrudRepository <CollectionTopics, Integer> {

    public CollectionTopics findByTopicName(String topicName);

}
