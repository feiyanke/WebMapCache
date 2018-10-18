package io.my.webmapcache;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UriTemplateHandler;

import javax.activation.MimetypesFileTypeMap;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;

@Slf4j
@Component
public class InitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.debug("11111111111111");
//        MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
//        MimeType jpg = allTypes.forName("image/png");
//        String ext = jpg.getExtension();

        //D:\2723

        log.debug("ext");
    }
}
