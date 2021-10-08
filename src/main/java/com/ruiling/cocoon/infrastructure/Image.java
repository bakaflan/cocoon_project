package com.ruiling.cocoon.infrastructure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    private final String url;
    private final String thumbnailUrl;

    @JsonCreator
    public Image(@JsonProperty("url") String url,
                 @JsonProperty("thumbnailUrl") String thumbnailUrl) {
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
