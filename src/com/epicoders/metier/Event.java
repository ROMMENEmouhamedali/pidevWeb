package com.epicoders.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    private int idEvent;
    public String nameEvent;
    private int priceEvent;
    private String DescriptionEvent;
    private String startDateEvent;

    public Event(String nameEvent, int priceEvent, String descriptionEvent, String startDateEvent) {
        this.nameEvent = nameEvent;
        this.priceEvent = priceEvent;
        DescriptionEvent = descriptionEvent;
        this.startDateEvent = startDateEvent;
    }

    public Event(int idEvent, String nameEvent, int priceEvent, String descriptionEvent, String startDateEvent) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.priceEvent = priceEvent;
        DescriptionEvent = descriptionEvent;
        this.startDateEvent = startDateEvent;
    }



    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public int getPriceEvent() {
        return priceEvent;
    }

    public void setPriceEvent(int priceEvent) {
        this.priceEvent = priceEvent;
    }

    public String getDescriptionEvent() {
        return DescriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        DescriptionEvent = descriptionEvent;
    }

    public String getStartDateEvent() {
        return startDateEvent;
    }

    public void setStartDateEvent(String startDateEvent) {
        this.startDateEvent = startDateEvent;
    }
}


