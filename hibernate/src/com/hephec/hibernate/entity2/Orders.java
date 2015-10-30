package com.hephec.hibernate.entity2;

public class Orders {
	private Integer orderId;
	private String ordersName;
	private Customer customer; 
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", ordersName=" + ordersName
				+ ", customer=" + customer + "]";
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrdersName() {
		return ordersName;
	}
	public void setOrdersName(String ordersName) {
		this.ordersName = ordersName;
	}
	
}
