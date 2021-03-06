package com.horyu1234.husuabieventlotteryapply.service;

import com.horyu1234.husuabieventlotteryapply.constant.EventStatus;
import com.horyu1234.husuabieventlotteryapply.domain.Applicant;
import com.horyu1234.husuabieventlotteryapply.domain.Event;
import org.apache.commons.math3.random.MersenneTwister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by horyu on 2018-04-16
 */
@Service
public class LotteryService {
    private EventService eventService;
    private ApplicantService applicantService;
    private EventWinnerService eventWinnerService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setApplicantService(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Autowired
    public void setEventWinnerService(EventWinnerService eventWinnerService) {
        this.eventWinnerService = eventWinnerService;
    }

    public Applicant lottery(int prizeId) {
        Event currentEvent = eventService.getCurrentEvent();

        if (currentEvent.getEventStatus() != EventStatus.LOTTERY) {
            currentEvent.setEventStatus(EventStatus.LOTTERY);
            eventService.updateEvent(currentEvent);
        }

        List<Applicant> applicantList = applicantService.getApplicantList();

        MersenneTwister mersenneTwister = new MersenneTwister();
        int randomIndex = mersenneTwister.nextInt(applicantList.size());

        Applicant lotteryedApplicant = applicantList.get(randomIndex);

        int eventId = currentEvent.getEventId();
        eventWinnerService.addEventWinner(eventId, prizeId, lotteryedApplicant);

        return lotteryedApplicant;
    }
}
