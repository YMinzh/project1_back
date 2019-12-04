import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        registry.addResourceHandler(("/static/**"))
                .addResourceLocations("file:"+uploadFolder);
        super.addResourceHandlers(registry);

    }

}
