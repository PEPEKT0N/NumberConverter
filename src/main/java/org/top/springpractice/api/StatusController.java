package org.top.springpractice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.top.springpractice.api.ApiMessages.*;

@RestController
@RequestMapping("api")
public class StatusController {

    @GetMapping
    public StringMessage serverStatus() {return new StringMessage("server is running"); }

    @GetMapping("ping")
    public StringMessage ping() {return new StringMessage("pong"); }
}
