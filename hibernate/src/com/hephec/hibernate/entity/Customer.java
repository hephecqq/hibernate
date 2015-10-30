package com.hephec.hibernate.entity;

public class Customer {

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName="
				+ customerName + "]";
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	private Integer customerId;
	private String customerName;
	
}
