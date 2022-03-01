package tn.happ.Model;

import java.sql.Date;

public class Event {
    private int idEvent;
    public String nameEvent;
    private int priceEvent;
    private String DescriptionEvent;
    private Date startDateEvent;

    public Event(String nameEvent, int priceEvent, String descriptionEvent, Date startDateEvent) {
        this.nameEvent = nameEvent;
        this.priceEvent = priceEvent;
        DescriptionEvent = descriptionEvent;
        this.startDateEvent = startDateEvent;
    }

    public Event(int idEvent, String nameEvent, int priceEvent, String descriptionEvent, Date startDateEvent) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.priceEvent = priceEvent;
        DescriptionEvent = descriptionEvent;
        this.startDateEvent = startDateEvent;
    }

    public Event() {

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

    public Date getStartDateEvent() {
        return startDateEvent;
    }

    public void setStartDateEvent(Date startDateEvent) {
        this.startDateEvent = startDateEvent;
    }
}



