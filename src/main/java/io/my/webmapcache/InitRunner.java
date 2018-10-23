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


    //https://api.tiles.mapbox.com/v4/mapbox.streets/12/2047/1362.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw
    //https://localhost:8080/v4/mapbox.streets/12/2047/1362.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw
    @Override
    public void run(String... args) throws Exception {
        log.debug("11111111111111");
//        MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
//        MimeType jpg = allTypes.forName("image/png");
//        String ext = jpg.getExtension();

        //D:\2723
        UriTemplate template = new UriTemplate("/111/cc");
        Map<String, String> map = template.match("/111/c");
        log.debug(map.toString());
    }
}
