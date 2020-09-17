package com.chxbca.custom.controller.rest;

import com.chxbca.custom.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chxbca
 */
@RestController
public class CustomController {

    @Autowired
    private CustomService customService;

    @GetMapping(path = "/message")
    public Object message(String message) {
        return customService.sendAndSave(message);
    }

    @GetMapping(path = "/localMessage")
    public Object localMessage(String message) {
        return customService.localMessage(message);
    }

}
