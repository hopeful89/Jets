package com.skilldistillery.jets.entity;

import java.util.List;

public class AirField {
	private List<Jet> jet;
	
	public AirField() {
		
	}
	
	public void addJetToAirField(Jet jet) {
		this.jet.add(jet);
	}
	
	public void removeJetFromAirField(Jet jet) {
		if(this.jet.contains(jet)) {
			this.jet.remove(jet);
		}
		System.out.println("You have removed " + jet.getModel());
	}
}
