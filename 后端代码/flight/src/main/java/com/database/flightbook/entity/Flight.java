package com.database.flightbook.entity;

public class Flight {

  private long flightId;
  private String flightName;
  private String leaveCity;
  private String arriveCity;
  private String leaveAirport;
  private String arriveAirport;
  private java.sql.Timestamp leaveTime;
  private java.sql.Timestamp arriveTime;
  private long capacity;
  private double price;
  private long bookSum;
  private long timezone1;
  private long timezone2;


  public long getFlightId() {
    return flightId;
  }

  public void setFlightId(long flightId) {
    this.flightId = flightId;
  }


  public String getFlightName() {
    return flightName;
  }

  public void setFlightName(String flightName) {
    this.flightName = flightName;
  }


  public String getLeaveCity() {
    return leaveCity;
  }

  public void setLeaveCity(String leaveCity) {
    this.leaveCity = leaveCity;
  }


  public String getArriveCity() {
    return arriveCity;
  }

  public void setArriveCity(String arriveCity) {
    this.arriveCity = arriveCity;
  }


  public String getLeaveAirport() {
    return leaveAirport;
  }

  public void setLeaveAirport(String leaveAirport) {
    this.leaveAirport = leaveAirport;
  }


  public String getArriveAirport() {
    return arriveAirport;
  }

  public void setArriveAirport(String arriveAirport) {
    this.arriveAirport = arriveAirport;
  }


  public java.sql.Timestamp getLeaveTime() {
    return leaveTime;
  }

  public void setLeaveTime(java.sql.Timestamp leaveTime) {
    this.leaveTime = leaveTime;
  }


  public java.sql.Timestamp getArriveTime() {
    return arriveTime;
  }

  public void setArriveTime(java.sql.Timestamp arriveTime) {
    this.arriveTime = arriveTime;
  }


  public long getCapacity() {
    return capacity;
  }

  public void setCapacity(long capacity) {
    this.capacity = capacity;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public long getBookSum() {
    return bookSum;
  }

  public void setBookSum(long bookSum) {
    this.bookSum = bookSum;
  }


  public long getTimezone1() {
    return timezone1;
  }

  public void setTimezone1(long timezone1) {
    this.timezone1 = timezone1;
  }


  public long getTimezone2() {
    return timezone2;
  }

  public void setTimezone2(long timezone2) {
    this.timezone2 = timezone2;
  }

}
