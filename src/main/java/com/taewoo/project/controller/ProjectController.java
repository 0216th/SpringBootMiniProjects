package com.taewoo.project.controller;

import com.taewoo.project.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class ProjectController {

    @Autowired
    private final LottoService lottoService;

    public ProjectController(LottoService lottoService) {
        this.lottoService = lottoService;
        System.out.println(this.lottoService);
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index.html";
    }

    @GetMapping("/calculator")
    public String calculator(Model model) {
        return "calculator";
    }

    @GetMapping("/lotto")
    public String lotto(Model model) {

        try {
            ArrayList<ArrayList<String>> lottoHistory = lottoService.getLottoHistory();

            for(int i = 0; i<lottoHistory.size(); i++) {
                System.out.println(lottoHistory.get(i));
            }
           // System.out.println("[in controller] : " + lottoHistory.get(0).get(0));
            model.addAttribute("lottoHistory" , lottoHistory);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "lotto";
    }


}
