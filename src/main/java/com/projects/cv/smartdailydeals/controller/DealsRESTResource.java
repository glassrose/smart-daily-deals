package com.projects.cv.smartdailydeals.controller;

import com.projects.cv.smartdailydeals.model.Deal;
import com.projects.cv.smartdailydeals.service.DealsService;
import com.projects.cv.smartdailydeals.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/deals")
public class DealsRESTResource {
    @Autowired
    DealsService dealsService;
    @Autowired
    SchedulingService schedulingService;

    @GetMapping("/getActiveDeals")
    public Map<String, Deal> getActiveDeals() {
        return dealsService.getActiveDeals();
    }

    @PostMapping("/activateDealEngine")
    public void activateDealEngine() {
        schedulingService.activateDealEngine();
    }

    @PostMapping("/deactivateDealEngine")
    public void deactivateDealEngine() {
        schedulingService.deactivateDealEngine();
    }
}
