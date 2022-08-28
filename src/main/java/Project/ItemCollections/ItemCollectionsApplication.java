package Project.ItemCollections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Project.ItemCollections.Properties.StorageProperties.class)
public class ItemCollectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemCollectionsApplication.class, args);
	}

}
