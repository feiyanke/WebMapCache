package io.my.webmapcache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UrlPathHelper;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@ConfigurationProperties("app")
public class MapCacheConfig {

    private List<MapConfig> maps;

    public List<MapConfig> getMaps() {
        return maps;
    }

    public void setMaps(List<MapConfig> maps) {
        this.maps = maps;
    }
}

class MapParams {

    @Getter
    private String localPath;
    @Getter
    private String sourceUrl;

    MapParams(String path, String url) {
        this.localPath = path;
        this.sourceUrl = url;
    }
}

class MapConfig {

    private String cachePath;
    private String capabilityUrl;
    private String tileUrl;
    private String featureInfoUrl;

    @Getter
    private UriTemplate tileUriTemplate;
    @Getter
    private URL capabilityURL;
    @Getter
    private URL featureInfoURL;

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    public String getCapabilityUrl() {
        return capabilityUrl;
    }

    public void setCapabilityUrl(String capabilityUrl) throws MalformedURLException {
        this.capabilityUrl = capabilityUrl;
        this.capabilityURL = new URL(capabilityUrl);
    }

    public String getTileUrl() {
        return tileUrl;
    }

    public void setTileUrl(String tileUrl) throws MalformedURLException {
        this.tileUrl = tileUrl;
        this.tileUriTemplate = new UriTemplate(new URL(tileUrl).getPath());
    }

    public String getFeatureInfoUrl() {
        return featureInfoUrl;
    }

    public void setFeatureInfoUrl(String featureInfoUrl) throws MalformedURLException {
        this.featureInfoUrl = featureInfoUrl;
        this.featureInfoURL = new URL(featureInfoUrl);
    }

    //if match, return params, else return null
    private MapParams checkTileUri(String url) {
        Map<String, String> map = tileUriTemplate.match(url);
        if (map.size() != 3) {
            return null;
        }
        String localPath = String.format("%s//L%s//R%s//C%s", cachePath, map.get("z"), map.get("x"), map.get("y"));
        String sourceUrl = null;
        try {
            sourceUrl = new URL(tileUrl).getHost() + new URL(url).getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return new MapParams(localPath, sourceUrl);
    }

    private MapParams checkCapabilityUrl(String url) {
        try {
            String uri = new URL(url).getPath();
            if (capabilityURL.getPath().equals(uri)) {
                String localPath = String.format("%s/GetCapability", cachePath);
                return new MapParams(localPath, capabilityUrl);
            } else return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private MapParams checkFeatureInfoUrl(String url) {
        try {
            String uri = new URL(url).getPath();
            if (capabilityURL.getPath().equals(uri)) {
                String localPath = String.format("%s/GetFeatureInfo", cachePath);
                return new MapParams(localPath, featureInfoUrl);
            } else return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MapParams checkUrl(String url) {
        MapParams result = checkTileUri(url);
        if (result != null) {
            return result;
        }
        result = checkCapabilityUrl(url);
        if (result != null) {
            return result;
        }
        result = checkFeatureInfoUrl(url);
        return result;
    }
}
