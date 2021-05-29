package com.skilldistillery.jets.entity;

public class JetImpl extends Jet {

	public JetImpl(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println("Ladies and Gentleman this is your captain speaking.  Prepare for a bumpy ride!");
	}

	@Override
	public String toString() {
		return "JetImpl [getModel()=" + getModel() + ", getTailNumber()="
				+ getTailNumber() + ", getSpeed()=" + getSpeed() + ", getRange()=" + getRange() + ", getPrice()="
				+ getPrice() + "]";
	}



}
