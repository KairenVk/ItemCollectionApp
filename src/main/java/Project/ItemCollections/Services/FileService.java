package Project.ItemCollections.Services;

import Project.ItemCollections.Exceptions.StorageException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@Service
public class FileService {

    private StorageService storageService;

    public FileService(StorageService storageService) {
        this.storageService = storageService;
    }


    public String uploadFile(MultipartFile file, Integer id) {
        if (!file.isEmpty()) {
            String name = storageService.store(file, id);

            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(name)
                    .toUriString();
            return uri;
        }
        return "";
    }

    public void validateFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {

            throw new StorageException(
                    "Cannot store file with relative path outside current directory "
                            + filename);
        }
        if (!FilenameUtils.getExtension(filename).equals("png") && !FilenameUtils.getExtension(filename).equals("jpg")) {
            throw new StorageException("Wrong file extension " + filename);
        }
    }
    public void deleteFile(String filePath) {
        storageService.deleteFile(filePath);
    }
}
