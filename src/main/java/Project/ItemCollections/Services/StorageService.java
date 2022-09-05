package Project.ItemCollections.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {

    void init();

    String store(MultipartFile file, Integer id);

    void deleteAll();

    void deleteFile(String filePath);
}
