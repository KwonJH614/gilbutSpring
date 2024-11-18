package com.example.gilbutSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

  @GetMapping("/hi")
  public String niceToMeetYou(Model model) {
    model.addAttribute("username", "권재현");
    return "greetings";
  }

  @GetMapping("/bye")
  public String GooBye(Model model) {
    model.addAttribute("nickname", "Kwon");
    return "goodbye";
  }
}
