package com.bvk.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping(value = "/validate")
    public ResponseEntity<Integer> validate() {
        return ResponseEntity.status(HttpStatus.OK).body(200);
    }
}
