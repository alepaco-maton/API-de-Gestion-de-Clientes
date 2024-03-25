package com.bisa.demo;

import com.bisa.demo.controller.ClientController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private ClientController controller;

    @Test
    void contextLoads() {

        assertThat(controller).isNotNull();
    }

}
