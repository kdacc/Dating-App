package com.example.datingapp.controller;

import com.example.datingapp.model.Profile;
import com.example.datingapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String listProfiles(Model model) {
        model.addAttribute("profiles", profileService.getAllProfiles());
        return "profiles-list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Profile> result = profileService.searchByKeyword(keyword);
        model.addAttribute("profiles", result);
        return "profiles-list";
    }
}