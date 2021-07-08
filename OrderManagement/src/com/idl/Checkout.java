package com.idl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Checkout {
	 
	private static final String BLACK_FRIDAY_DATE = "27.11.2020";
	static SimpleDateFormat sdfo = new SimpleDateFormat("dd.MM.yyyy");
	HashMap<String, Integer> countMap = new HashMap<>();
	HashMap<String, Double> productPriceMap = new HashMap<>();
	HashSet<String> dateDiscountSet = new HashSet<>();

	HashMap<Product,SpecialDiscountRule> rule;

	public Checkout(HashMap<Product,SpecialDiscountRule> rule) {
		this.rule = rule;
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		Date date=null;
		try {
			date = sdfo.parse("27.11.2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		SpecialDiscountRule rule1 = new SpecialDiscountRule("A", 3, 40, 100, sdfo.parse("7.17.2021"), 0);
//		SpecialDiscountRule rule2 = new SpecialDiscountRule("B", 2, 50, 80, sdfo.parse("7.17.2021"), 0);
//		SpecialDiscountRule rule3 = new SpecialDiscountRule("C", 0, 25, 0,sdfo.parse("7.17.2021"), 0);
//		SpecialDiscountRule rule4 = new SpecialDiscountRule("D", 0, 20, 100, sdfo.parse("7.17.2021"), 0);
//		SpecialDiscountRule rule5 = new SpecialDiscountRule("E", 0, 100, 0, sdfo.parse("27.11.2020"), 10);
//
//		List<SpecialDiscountRule> listRule = new ArrayList<SpecialDiscountRule>();
//		listRule.add(rule1);
//		listRule.add(rule2);
//		listRule.add(rule3);
//		listRule.add(rule4);
//		listRule.add(rule5);
		
		HashMap<Product,SpecialDiscountRule> ruleMap = new HashMap<>();
		ruleMap.put(new Product("A",40),new SpecialDiscountRule("A", 3, 40, 100, sdfo.parse("7.17.2021"), 0));
		ruleMap.put(new Product("B",50),new SpecialDiscountRule("B", 2, 50, 80, sdfo.parse("7.17.2021"), 0));
		ruleMap.put(new Product("C",20),new SpecialDiscountRule("C", 0, 25, 0,sdfo.parse("7.17.2021"), 0));
		ruleMap.put(new Product("D",50),new SpecialDiscountRule("D", 0, 20, 100, sdfo.parse("7.17.2021"), 0));
		ruleMap.put(new Product("E",100),new SpecialDiscountRule("E", 0, 100, 0, sdfo.parse("27.11.2020"), 10));
		

		Checkout checkout = new Checkout(ruleMap);
		// String goods = "AAAAB";
		String goods = "AABBCDCAEEEE";
		//String goods = "AAA";

		for (int i = 0; i < goods.length(); i++) {
			checkout.scan(String.valueOf(goods.charAt(i)));
		}
		double t = checkout.total();
		System.out.println("the total price is -->" + t);

	}

	private void scan(String item) throws ParseException {
		// TODO Auto-generated method stub
		// System.out.println("the item is --"+item);
		if (countMap.containsKey(item)) {
			countMap.put(item, countMap.get(item) + 1);
			for(Entry<Product,SpecialDiscountRule> productMap : rule.entrySet()){
				
				if(productMap.getKey().getName().equals(item)){
					
					if(productMap.getValue().getQuantity() != 0){
						if (countMap.get(item) % productMap.getValue().getQuantity() == 0) {
							productPriceMap.put(item,
									productMap.getValue().getPriceAtTheQuantity() * (countMap.get(item) / productMap.getValue().getQuantity()));
						} else {
							productPriceMap.put(item, productPriceMap.get(item) + productMap.getValue().getPrice());
						}
					} else {
						productPriceMap.put(item, productPriceMap.get(item) + productMap.getValue().getPrice());
						discountDay(item,productMap);
					}
				}
			}
		} else {
			System.out.println("else the item is --" + item);
			countMap.put(item, 1);
			for (Entry<Product,SpecialDiscountRule> productMap : rule.entrySet()) {
				if(productMap.getKey().getName().equals(item)) {
					productPriceMap.put(item, productMap.getValue().getPrice());
					discountDay(item,productMap);
				}
			}
		}
	}

	private void discountDay(String item, Entry<Product, SpecialDiscountRule> productMap) throws ParseException {
		// TODO Auto-generated method stub
		if(productMap.getValue().getDiscountDay().compareTo(sdfo.parse(BLACK_FRIDAY_DATE)) == 0){
			dateDiscountSet.add(item);
		}
		
	}

	
	private double total() throws ParseException {
		// TODO Auto-generated method stub
		double total = 0;
//		for(Entry<String, Double> price: productPriceMap.entrySet()) {
//			for(Entry<Product,SpecialDiscountRule> productMap : rule.entrySet()) {
//				if(price.getKey().equals(productMap.getValue().getProduct()) && (productMap.getValue().getDiscountDay().compareTo(sdfo.parse(BLACK_FRIDAY_DATE)) == 0)){
//					double val = price.getValue().doubleValue() - price.getValue().doubleValue()*0.1;
//					productPriceMap.put(price.getKey(), val);
//				}
//			}
//		}
		
		dateDiscountSet.forEach(set->{
			double val = (productPriceMap).get(set) - productPriceMap.get(set).doubleValue()*0.1;
			productPriceMap.put(set, val);
		});
		
		
		for (Map.Entry count : countMap.entrySet()) {
			System.out.println("stock --" + count.getKey() + " stock value count -->" + count.getValue());
		}
		for (Entry<String, Double> price : productPriceMap.entrySet()) {
			System.out.println("key --" + price.getKey() + " value -->" + price.getValue());
			total += price.getValue().doubleValue();

		}
		return total;
	}

}
