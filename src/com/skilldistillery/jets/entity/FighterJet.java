package com.skilldistillery.jets.entity;

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(getPilot() + " " + getModel() + "Keep the formation tight. Begin verticle climb.");
	}

	public void fight() {
		System.out.println(getPilot() + ": Im going to pull the airbrake and they will fly right by!");
		System.out.println("TailNumber: " + getTailNumber() + " Successfully defeated the enemy requesting tower fly by..");
	}

	@Override
	public String toString() {
		return  getClass().getSimpleName() + " || Pilot: " + getPilot() +", model: " + getModel() + ", Tailnumber: " + getTailNumber() + ", Speed: "
				+ getSpeed() + ", Range: " + getRange() + ", Price " + getPrice() + ".";
	}


}
