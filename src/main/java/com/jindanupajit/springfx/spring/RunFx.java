package com.jindanupajit.springfx.spring;

import com.jindanupajit.springfx.fx.FxApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunFx implements CommandLineRunner {

    @Value("${server.port:8080}")
    private Integer serverPort;

    @Override
    public void run(String... args) {
        System.out.println("Start FxApplication ...");
        FxApplication.run(serverPort, args);
    }
}
