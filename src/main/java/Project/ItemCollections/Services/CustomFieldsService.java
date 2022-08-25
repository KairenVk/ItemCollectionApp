package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.CollectionCustomFieldsData;
import Project.ItemCollections.Entities.Collection.CollectionItemFields;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Repositories.CollectionCustomFieldsDataRepository;
import Project.ItemCollections.Repositories.CollectionItemFieldsRepository;
import Project.ItemCollections.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomFieldsService {

    @Autowired
    private CollectionCustomFieldsDataRepository collectionCustomFieldsDataRepository;

    @Autowired
    private CollectionItemFieldsRepository collectionItemFieldsRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void CreateItemCustomFields(Item item, List<String> customFieldsNames, List<String> customFieldsValues) {
        for (int i = 0; i < customFieldsNames.size(); i++) {
            CollectionItemFields newField = new CollectionItemFields();
            newField.setFieldContent(customFieldsValues.get(i));
            newField.setItemId(item);
            newField.setCustomFieldsData(collectionCustomFieldsDataRepository.findByName(customFieldsNames.get(i)));
            collectionItemFieldsRepository.save(newField);
            item.addCustomItemField(newField);
            itemRepository.save(item);
        }
    }
}
