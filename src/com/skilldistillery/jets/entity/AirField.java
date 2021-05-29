package com.skilldistillery.jets.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AirField {
	private List<Jet> jets = new ArrayList<>();

	public AirField() {
	}
	
	public void fastestJet() {
		Jet fastestJet = jets.get(0);
		
		for (Jet jet : jets) {
			if(fastestJet.getSpeed() < jet.getSpeed()) {
				fastestJet = jet;
			}
		}
		System.out.println("The fastest Jet on the airfield is: " + fastestJet);
		System.out.println();
	}
	public void longestRangeJet() {
		Jet longestRangeJet = jets.get(0);
		
		for (Jet jet : jets) {
			if(longestRangeJet.getRange() < jet.getRange()) {
				longestRangeJet = jet;
			}
		}
		System.out.println("The Jet  with the most range on the airfield is: " + longestRangeJet);
		System.out.println();
	}

	public void flyAllJets() {
		Iterator<Jet> jet = jets.iterator();
		while (jet.hasNext()) {
			Jet temp = jet.next();
			temp.fly();
			temp.timeTilEmpty();
			System.out.println();
		}
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
		if (this.jets.contains(jet)) {
			this.jets.remove(jet);
		}
		System.out.println("You have removed " + jet.getModel() + " Tailnumber: " + jet.getTailNumber());
		System.out.println();
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
