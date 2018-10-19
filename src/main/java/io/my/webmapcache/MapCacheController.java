package io.my.webmapcache;

import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

@Slf4j(topic = "TEST")
@RestController
public class MapCacheController {

    @Autowired
    private MapCacheConfig config;

    static MapConfig getMatchedConfig(List<MapConfig> configs, String uri) {
        return null;
    }

    private MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();

    @RequestMapping(value = "**", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> cacheMap(HttpServletRequest request) throws IOException, MimeTypeException {

        //1. get the request url
        String url = request.getRequestURL().toString();
        if (request.getQueryString()!=null) {
            url += "?"+request.getQueryString();
        }
//        MimeType mimeType = allTypes.forName(request.getContentType());
//        String ext = mimeType.getExtension();
        log.debug("Request Url is {}", url);
//        log.debug("Request Res is {}", ext);
        //2. match url to config url, if not match, throw exception.
        for (MapConfig mapConfig:config.getMaps()) {
            MapParams params = mapConfig.checkUrl(url);
            if (params != null) {
                //3. check if the tile is cached, response the tile directly.
                log.debug("Match Url Success!");
                String localPath = params.getLocalPath()+"."+mapConfig.getTileType();
                File file = new File(localPath);
                boolean exist = file.exists();
                log.debug("Local File: {}", localPath);
                log.debug("Exist: {}", exist);
                if (exist) {
                    return ResponseEntity.ok()
                            .contentType(new MediaType(request.getContentType()))
                            .body(com.google.common.io.Files.toByteArray(file));
                } else {
                    //4. proxy request to the web map, and get the tile.
                    URL source = new URL(params.getSourceUrl());
                    HttpURLConnection connection = (HttpURLConnection) source.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(2000);
                    connection.setReadTimeout(2000);
                    return ResponseEntity.ok()
                            .contentType(new MediaType(connection.getContentType()))
                            .body(ByteStreams.toByteArray(connection.getInputStream()));
                    //                    BufferedReader in = new BufferedReader(
//                            new InputStreamReader(connection.getInputStream()));
//
                    //5. save the tile file by x,y,z defined directory.
                    //6. response the tile.
                }
            }
        }

//        File img = new File("D:\\2723.png");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .body(Files.toByteArray(img));
        return null;
    }

}
