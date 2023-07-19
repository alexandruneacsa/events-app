package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private EventController eventController;

    @Test
    public void testEventController() {
        assertThat(eventController).isNotNull();
    }

    @Autowired
    private UserController userController;

    @Test
    public void testUserController() {
        assertThat(userController).isNotNull();
    }
}
