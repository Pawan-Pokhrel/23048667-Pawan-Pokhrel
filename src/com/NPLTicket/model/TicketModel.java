/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.NPLTicket.model;

/**
 *
 * @author Pawan Pokhrel
 */
public class TicketModel {
    private String ticketId;
    private int matchNo;
    private String ticketOwnerName;
    private String ticketOwnerPhone;
    private String ticketOwnerEmail;
    private String ticketZone;
    private String bookingDate;

    public TicketModel() {}
    
    public TicketModel(String ticketId, int matchNo ,String ticketOwnerName, String ticketOwnerPhone, String ticketOwnerEmail, String ticketZone, String bookingDate) {
        this.ticketId = ticketId;
        this.matchNo = matchNo;
        this.ticketOwnerName = ticketOwnerName;
        this.ticketOwnerPhone = ticketOwnerPhone;
        this.ticketOwnerEmail = ticketOwnerEmail;
        this.ticketZone = ticketZone;
        this.bookingDate = bookingDate;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketOwnerName() {
        return ticketOwnerName;
    }

    public void setTicketOwnerName(String ticketOwnerName) {
        this.ticketOwnerName = ticketOwnerName;
    }

    public String getTicketOwnerPhone() {
        return ticketOwnerPhone;
    }

    public void setTicketOwnerPhone(String ticketOwnerPhone) {
        this.ticketOwnerPhone = ticketOwnerPhone;
    }

    public String getTicketOwnerEmail() {
        return ticketOwnerEmail;
    }

    public void setTicketOwnerEmail(String ticketOwnerEmail) {
        this.ticketOwnerEmail = ticketOwnerEmail;
    }

    public String getTicketZone() {
        return ticketZone;
    }

    public void setTicketZone(String ticketZone) {
        this.ticketZone = ticketZone;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(int matchNo) {
        this.matchNo = matchNo;
    }
    
    
    
}
