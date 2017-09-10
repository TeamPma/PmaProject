package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Announcement;

/**
 * Created by Maja on 7.9.2017.
 */

public class UpdateNewsEvent extends BaseEvent{

    private Announcement announcement;
    public UpdateNewsEvent( Announcement announcement) {
        super(EventType.UPDATE_NEWS_EVENT);
        this.announcement = announcement;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }
}
