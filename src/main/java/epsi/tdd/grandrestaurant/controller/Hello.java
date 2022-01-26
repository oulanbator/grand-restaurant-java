package epsi.tdd.grandrestaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("hello")
    public String hello() {
		System.out.println("Dans la route");
        return "Hello World !";
    }
}
