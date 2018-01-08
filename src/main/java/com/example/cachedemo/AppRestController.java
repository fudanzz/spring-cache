package com.example.cachedemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class AppRestController {

    private static final Logger log = LoggerFactory.getLogger(AppRestController.class);

    private AppService appService;

    public AppRestController(AppService appService) {
        this.appService = appService;
    }

    @RequestMapping(value = "/app",method = RequestMethod.POST)
    public String createApp(@RequestBody App app) {
        appService.newApp(app);
        return "ok";
    }

    @RequestMapping(value = "/app/{name}",method = RequestMethod.GET)
    public String getAppToken(@PathVariable("name") String name) {
        String token=appService.getAppToken(name);
        return token;
    }

    @RequestMapping(value = "/app/",method = RequestMethod.GET)
    public Object getAppToken() {
        return appService.appList();
    }


    @RequestMapping(value = "/app/{name}",method = RequestMethod.POST)
    public String updateAppToken(@PathVariable("name") String name) {
        return appService.updateAppToken(name);
    }

}
