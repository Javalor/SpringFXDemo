package com.jindanupajit.springfx.spring.controller;

import com.jindanupajit.springfx.fx.FxApplication;
import javafx.application.Platform;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"/","/start"})
    public String start() {
        return "start";
    }

    @GetMapping(value={"/shutdown"})
    public String shutdown() {
        return "shutdown";
    }

    @GetMapping(value={"/shutdown-final"})
    public String shutdownfinal() {
        Platform.exit();
        return "shutdown-final";
    }

}
