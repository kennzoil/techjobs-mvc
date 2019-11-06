package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public String search(Model model, HttpServletRequest request) {

        model.addAttribute("columns", ListController.columnChoices);

        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");
        ArrayList<HashMap<String, String>> allJobs = JobData.findAll();
        ArrayList<HashMap<String, String>> searchedJobs = new ArrayList<>();

        if (searchType.equals("all")) {
            searchedJobs = JobData.findByValue(searchTerm);
        } else {
            searchedJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("jobs", searchedJobs);

        return "search";
    }

}
