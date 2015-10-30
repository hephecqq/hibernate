package com.hephec.hibernate.entity2;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Set<Orders> orders=new HashSet<Orders>();
	
	public Set<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
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
