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

@Component
@ConfigurationProperties("app")
class MapCacheConfig {
    private String aaa;
    private String bbb;
    private List<MapConfig> maps;

    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bbb) {
        this.bbb = bbb;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public List<MapConfig> getMaps() {
        return maps;
    }

    public void setMaps(List<MapConfig> maps) {
        this.maps = maps;
    }
}


class MapConfig {
    private String cachePath;
    private String capabilityUrl;
    private String tileUrl;
    private String featureInfoUrl;

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    public String getCapabilityUrl() {
        return capabilityUrl;
    }

    public void setCapabilityUrl(String capabilityUrl) {
        this.capabilityUrl = capabilityUrl;
    }

    public String getTileUrl() {
        return tileUrl;
    }

    public void setTileUrl(String tileUrl) {
        this.tileUrl = tileUrl;
    }

    public String getFeatureInfoUrl() {
        return featureInfoUrl;
    }

    public void setFeatureInfoUrl(String featureInfoUrl) {
        this.featureInfoUrl = featureInfoUrl;
    }
}

@RestController
class ImageController {

    @Autowired
    private MapCacheConfig config;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadUserAvatarImage() throws IOException {
        String a = config.getBbb();
        File img = new File("D:\\2723.png");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(Files.toByteArray(img));
    }

}


