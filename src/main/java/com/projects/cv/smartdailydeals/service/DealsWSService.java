package com.projects.cv.smartdailydeals.service;

import com.projects.cv.smartdailydeals.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealsWSService {


    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public DealsWSService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notifyFrontend(List<Deal> deals) {
        System.out.println("DEBUG: Publishing new deals:"+ deals);
        simpMessagingTemplate.convertAndSend("/topic/active-deals", deals);
    }
}
