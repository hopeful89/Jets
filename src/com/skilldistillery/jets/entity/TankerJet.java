package com.skilldistillery.jets.entity;

public class TankerJet extends Jet implements IsATanker{

	public TankerJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(getPilot() + " " + getModel() + " I love being able to fly and not give out my fuel.");
	}

	public void refuel() {
		System.out.println("TailNumber: " + getTailNumber() + " Lets go boys...dump it out...fill 'em up!");
	}

	@Override
	public String toString() {
		return  getClass().getSimpleName() + " || Pilot: " + getPilot() +", model: " + getModel() + ", Tailnumber: " + getTailNumber() + ", Speed: "
				+ getSpeed() + ", Range: " + getRange() + ", Price" + getPrice() + ".";
	}



}
