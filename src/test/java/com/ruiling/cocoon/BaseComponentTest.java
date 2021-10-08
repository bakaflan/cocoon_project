package com.ruiling.cocoon;

import com.github.database.rider.spring.api.DBRider;
import org.springframework.boot.test.context.SpringBootTest;

@DBRider
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BaseComponentTest {
}
