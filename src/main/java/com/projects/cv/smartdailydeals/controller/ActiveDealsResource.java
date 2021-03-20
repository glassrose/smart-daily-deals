package com.projects.cv.smartdailydeals.controller;

import com.projects.cv.smartdailydeals.model.Deal;
import com.projects.cv.smartdailydeals.service.DealsWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deals")
@CrossOrigin
public class ActiveDealsResource {

    @Autowired
    DealsWSService dealsWSService;

    @PostMapping("/publish-active-deals")
    public void postDeals(@RequestBody List<Deal> deals) {
        dealsWSService.notifyFrontend(deals);
    }
}
