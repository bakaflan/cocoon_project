package com.ruiling.cocoon.access.about.representation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutInfo {
    private final String environment;
    private final String deployTime;

    public AboutInfo(String environment, String deployTime) {
        this.environment = environment;
        this.deployTime = deployTime;
    }
}
