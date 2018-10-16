package io.my.webmapcache;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import com.google.common.io.Files;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class WebMapCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMapCacheApplication.class, args);
    }
}




