package com.SuperMonacoDesigns.CountdownLettersRound.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
public class OxfordDictionariesController {

    @Autowired
    public OxfordDictionariesController() {

    }

    public final String app_id = "b378f415";
    public final String app_key = "fbc8a0f062b2d4d78c1383c5c596db3d";

    @GetMapping(value = "https://od-api.oxforddictionaries.com/api/v2/entries/en-gb/deltoid", headers = {"app_id = b378f415", "app_key = fbc8a0f062b2d4d78c1383c5c596db3d"})
    public ResponseEntity<String> checkWord(HttpServletResponse response) {

        response.addHeader("app_id", app_id);
        response.addHeader("app_key", app_key);

        System.out.println("deltoid");
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
