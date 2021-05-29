package com.skilldistillery.jets.entity;

public class TankerJet extends Jet implements IsATanker{

	public TankerJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println("I love being able to fly and not give out my fuel.");
	}

	public void refuel() {
		System.out.println("Lets go boys...dump it out...fill 'em up!");
	}

	@Override
	public String toString() {
		return "TankerJet [getModel()=" + getModel() + ", getTailNumber()="
				+ getTailNumber() + ", getSpeed()=" + getSpeed() + ", getRange()=" + getRange() + ", getPrice()="
				+ getPrice() + "]";
	}



}
