package com.skilldistillery.jets.entity;

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println("Keep the formation tight. Begin verticle climb.");
	}

	public void fight() {
		System.out.println("Im going to pull the airbrake and they will fly right by!");
	}

}
