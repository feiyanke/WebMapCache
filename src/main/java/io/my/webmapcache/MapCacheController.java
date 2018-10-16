package io.my.webmapcache;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class MapCacheController {

    @Autowired
    private MapCacheConfig config;

    @RequestMapping(value = "**", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> cacheMapTiles(HttpServletRequest request) throws IOException {

        //1. get the request url
        String url = request.getRequestURL().toString();
        //2. match url to config url, if not match, throw exception.
        //3. fetch the x,y,z,s parameters, check if the tile is cached, response the tile directly.
        //4. proxy request to the web map, and get the tile.
        //5. save the tile file by x,y,z defined directory.
        //6. response the tile.


        String a = config.getBbb();
        File img = new File("D:\\2723.png");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(Files.toByteArray(img));
    }

}
