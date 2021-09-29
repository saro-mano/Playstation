package com.example.PS.controller;


import com.example.PS.service.GetFF;
import com.example.PS.service.UpdateFF;
import org.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
public class Controller {
    @GetMapping("/getFF")
    public String run() throws IOException, JSONException {
        GetFF get = new GetFF();
        return get.run();
    }

    @GetMapping("/updateFF")
    public void update(@RequestParam String num) throws IOException, JSONException {
        UpdateFF update = new UpdateFF();
        update.update(num);
    }
}
