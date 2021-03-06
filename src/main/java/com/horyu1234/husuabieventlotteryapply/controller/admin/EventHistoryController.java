package com.horyu1234.husuabieventlotteryapply.controller.admin;

import com.horyu1234.husuabieventlotteryapply.constant.ModelAttributeNames;
import com.horyu1234.husuabieventlotteryapply.constant.ViewNames;
import com.horyu1234.husuabieventlotteryapply.service.EventService;
import com.horyu1234.husuabieventlotteryapply.service.EventWinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by horyu on 2018-04-16
 */
@RequestMapping("/admin/eventHistory")
@Controller
public class EventHistoryController {
    private EventService eventService;
    private EventWinnerService eventWinnerService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setEventWinnerService(EventWinnerService eventWinnerService) {
        this.eventWinnerService = eventWinnerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String eventHistory(Model model) {
        model.addAttribute("eventList", eventService.getEventList());
        model.addAttribute(ModelAttributeNames.VIEW_NAME, "view/admin/eventHistory");

        return ViewNames.LAYOUT;
    }

    @RequestMapping(value = "/view/{eventId}", method = RequestMethod.GET)
    public String eventHistory(Model model, @PathVariable int eventId) {
        model.addAttribute("eventResult", eventWinnerService.getCurrentEventResult(eventId));
        model.addAttribute(ModelAttributeNames.VIEW_NAME, "view/admin/eventHistoryView");

        return ViewNames.LAYOUT;
    }
}
