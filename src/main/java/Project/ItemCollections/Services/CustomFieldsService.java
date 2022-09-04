package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionsFields;
import Project.ItemCollections.Entities.Collection.CollectionsFieldsData;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Repositories.CollectionsFieldsRepository;
import Project.ItemCollections.Repositories.CollectionsFieldsDataRepository;
import Project.ItemCollections.Repositories.FieldTypesRepository;
import Project.ItemCollections.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomFieldsService {

    @Autowired
    private CollectionsFieldsRepository collectionsFieldsRepository;

    @Autowired
    private CollectionsFieldsDataRepository collectionsFieldsDataRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FieldTypesRepository fieldTypesRepository;

    public void createItemCustomFields(Item item, List<String> customFieldsNames, List<String> customFieldsValues) {
        for (int i = 0; i < customFieldsNames.size(); i++) {
            CollectionsFieldsData newField = new CollectionsFieldsData();
            newField.setFieldContent(customFieldsValues.get(i));
            newField.setItemId(item);
            newField.setCollectionField(collectionsFieldsRepository.findByNameAndCollectionId(customFieldsNames.get(i), item.getItemCollection().getId()));
            collectionsFieldsDataRepository.save(newField);
            item.addCustomItemField(newField);
            itemRepository.save(item);
        }
    }

    public void updateItemCustomFields(Item item, List<String> customFieldsNames, List<String> customFieldsValues) {
        collectionsFieldsDataRepository.deleteByItemId(item);
        createItemCustomFields(item, customFieldsNames, customFieldsValues);
    }


    public void createCollectionCustomFields(List<String> customFields, List<String> fieldNames, Collection n) {
        for (int i = 0; i < fieldNames.size(); i++) {
            CollectionsFields field = new CollectionsFields();
            field.setFieldType(fieldTypesRepository.getByNameType(customFields.get(i)));
            field.setName(fieldNames.get(i));
            field.setCollection(n);
            n.addFieldToCollection(field);
            collectionsFieldsRepository.save(field);
        }
    }
}
