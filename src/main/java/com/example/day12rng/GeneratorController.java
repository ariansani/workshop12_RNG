package com.example.day12rng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.day12rng.RandomNumberException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class GeneratorController {
  private Logger logger = LoggerFactory.getLogger(GeneratorController.class);


  @GetMapping("/generator")
  public String generatorForm(Model model) {
    try {
      model.addAttribute("generator", new Generator());
      return "generator";
    } catch (Exception ex) {
      System.out.println(ex);
      return "error";
    }

  }

  @PostMapping("/generator")
  public String generatorSubmit(@ModelAttribute Generator generator, Model model) {
    try {
      model.addAttribute("generator", generator.getId());


      long numbersNeeded = generator.getId();
      if (numbersNeeded > 30 || numbersNeeded < 0) {
        throw new RandomNumberException();
      }
      String[] imgNumbers = {
        "number0.jpg", 
        "number1.jpg",
        "number2.jpg",
        "number3.jpg",
        "number4.jpg",
        "number5.jpg",
        "number6.jpg",
        "number7.jpg",
        "number8.jpg",
        "number9.jpg",
        "number10.jpg",
        "number11.jpg",
        "number12.jpg",
        "number13.jpg",
        "number14.jpg",
        "number15.jpg",
        "number16.jpg",
        "number17.jpg",
        "number18.jpg",
        "number19.jpg",
        "number20.jpg",
        "number21.jpg",
        "number22.jpg",
        "number23.jpg",
        "number24.jpg",
        "number25.jpg",
        "number26.jpg",
        "number27.jpg",
        "number28.jpg",
        "number29.jpg",
        "number30.jpg",
      };
      List<String> selectedImg = new ArrayList<String>();

      Random rng = new Random();
      // Note: use LinkedHashSet to maintain insertion order
      Set<Integer> generated = new LinkedHashSet<Integer>();
      while (generated.size() < numbersNeeded) {
        Integer next = rng.nextInt(30) + 1;
        // As we're adding to a set, this will automatically do a containment check
        generated.add(next);
        selectedImg.add(imgNumbers[next]);
      }

      //Change the Set to a List so that we can sort it
      ArrayList<Integer> sortedGenerated = new ArrayList<>(generated);
      Collections.sort(sortedGenerated);

      model.addAttribute("randNumsResult",selectedImg.toArray());
      model.addAttribute("listOfRandomNumbers",sortedGenerated);

      return "result";
    } catch (RandomNumberException ex) {
      model.addAttribute("errorMessage", "Only accept numbers between 0 and 30(inclusive)");
      return "error";
    }

  }

}