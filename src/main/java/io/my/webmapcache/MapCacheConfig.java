package io.my.webmapcache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("app")
public class MapCacheConfig {
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
