package com.skilldistillery.jets.entity;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	private List<Jet> jets = new ArrayList<>();
	
	public AirField() {
	}
	
	public void addListOfJets(List<Jet> jets) {
		this.jets.addAll(jets);
	}
	
	public void addJetToAirField(Jet jet) {
		this.jets.add(jet);
	}
	
	public List<Jet> getJets() {
		List<Jet> clonedJets = new ArrayList<Jet>(jets);
		return clonedJets;
	}

	public void removeJetFromAirField(Jet jet) {
		if(this.jets.contains(jet)) {
			this.jets.remove(jet);
		}
		System.out.println("You have removed " + jet.getModel() + " Tailnumber: " + jet.getTailNumber());
	}
	
	public void listAllJetsInAirField() {
		System.out.println("************** ALL JETS ON AIRFIELD **************");
		System.out.println();
		for (Jet jet : jets) {
			System.out.println(jet.toString());
		}
		System.out.println();
		System.out.println("***********************************************");
		System.out.println();
	}
}
