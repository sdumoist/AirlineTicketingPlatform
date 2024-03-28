package com.database.flightbook.entity;



public class Orderandpassenger {

  private long orderId;
  private String passengerIden;
  private String trueName;
  private String phone;


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public String getPassengerIden() {
    return passengerIden;
  }

  public void setPassengerIden(String passengerIden) {
    this.passengerIden = passengerIden;
  }


  public String getTrueName() {
    return trueName;
  }

  public void setTrueName(String trueName) {
    this.trueName = trueName;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
