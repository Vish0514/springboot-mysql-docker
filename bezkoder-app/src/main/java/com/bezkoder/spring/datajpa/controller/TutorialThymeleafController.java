package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tutorials")
public class TutorialThymeleafController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping
    public String getAllTutorials(Model model) {
        model.addAttribute("tutorials", tutorialRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tutorial", new Tutorial());
        return "add";
    }

    @PostMapping("/add")
    public String addTutorial(@ModelAttribute("tutorial") Tutorial tutorial) {
        tutorialRepository.save(tutorial);
        return "redirect:/tutorials";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        tutorialRepository.findById(id).ifPresent(tutorial -> model.addAttribute("tutorial", tutorial));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTutorial(@PathVariable("id") long id, @ModelAttribute("tutorial") Tutorial tutorial) {
        tutorial.setId(id);
        tutorialRepository.save(tutorial);
        return "redirect:/tutorials";
    }

    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") long id) {
        tutorialRepository.deleteById(id);
        return "redirect:/tutorials";
    }
}
