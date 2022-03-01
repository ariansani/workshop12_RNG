package com.example.day12rng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GeneratorController {

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
      if (numbersNeeded > 30) {
        throw new IllegalArgumentException("Can't ask for more numbers than are available");
      }
      Random rng = new Random();
      // Note: use LinkedHashSet to maintain insertion order
      Set<Integer> generated = new LinkedHashSet<Integer>();
      while (generated.size() < numbersNeeded) {
        Integer next = rng.nextInt(30) + 1;
        // As we're adding to a set, this will automatically do a containment check
        generated.add(next);
      }

      //Change the Set to a List so that we can sort it
      ArrayList<Integer> sortedGenerated = new ArrayList<>(generated);
      Collections.sort(sortedGenerated);

      
      model.addAttribute("listOfRandomNumbers",sortedGenerated);

      return "result";
    } catch (Exception ex) {
      System.out.println(ex);
      return "error";
    }

  }

}