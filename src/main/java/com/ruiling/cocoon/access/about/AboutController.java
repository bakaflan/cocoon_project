package com.ruiling.cocoon.access.about;

import com.ruiling.cocoon.access.about.representation.AboutInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class AboutController {
    private static final ZonedDateTime DEPLOY_ZONE_TIME = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
    private final Environment environment;

    @GetMapping(value = "/about")
    public AboutInfo about() {
        String activeProfiles = Arrays.toString(environment.getActiveProfiles());
        String deployTime = DEPLOY_ZONE_TIME.toString();
        return new AboutInfo(activeProfiles, deployTime);
    }
}
