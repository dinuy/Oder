package com.idl;

import java.util.Date;

public class SpecialDiscountRule {
	String product;
	int quantity;
	double price;
	double priceAtTheQuantity;
	Date discountDay;
	double percent;
	
	SpecialDiscountRule(String product, int quantity, double price, double priceAtTheQuantity, Date discountDay, double percent){
		this.product=product;
		this.quantity=quantity;
		this.price=price;
		this.priceAtTheQuantity=priceAtTheQuantity;
		this.discountDay=discountDay;
		this.percent=percent;
	}
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPriceAtTheQuantity() {
		return priceAtTheQuantity;
	}
	public void setPriceAtTheQuantity(double priceAtTheQuantity) {
		this.priceAtTheQuantity = priceAtTheQuantity;
	}
	public Date getDiscountDay() {
		return discountDay;
	}
	public void setDiscountDay(Date discountDay) {
		this.discountDay = discountDay;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
}
