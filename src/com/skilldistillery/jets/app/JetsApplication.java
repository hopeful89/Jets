package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entity.AirField;
import com.skilldistillery.jets.entity.FighterJet;
import com.skilldistillery.jets.entity.Jet;
import com.skilldistillery.jets.entity.JetImpl;
import com.skilldistillery.jets.entity.TankerJet;

public class JetsApplication {

	private AirField airField;
	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();
		app.start();
	}

	private void start() {
		String fileName = "jets.txt";
		AirField airField = new AirField();

		List<List<String>> jetStartInfo = pullJetInfoFromFile(fileName);

		List<Jet> starterJets = createJetsFromData(jetStartInfo);
		System.out.println(starterJets);

	}

	private List<Jet> createJetsFromData(List<List<String>> list) {
		List<Jet> newJets = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			//model, speed, range, price
			String typeOfJet = list.get(i).get(0);
			String model = list.get(i).get(1);
			Double speed = Double.parseDouble(list.get(i).get(2));
			Integer range = Integer.parseInt(list.get(i).get(3));
			Long price = Long.parseLong(list.get(i).get(4));
			
			switch(typeOfJet) {
			case "TankerJet":
				newJets.add(new TankerJet(model, speed, range, price));
			case "FighterJet":
				newJets.add(new FighterJet(model, speed, range, price));
			case "JetImpl":
				newJets.add(new JetImpl(model, speed, range, price));
			}
		}
		
		return newJets;
	}

	private List<List<String>> pullJetInfoFromFile(String fileName) {
		List<List<String>> jetInfo = new ArrayList<>();

		try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				String[] splitLine = line.split(", ");
				
				List<String> temp = Arrays.asList(splitLine);
				if(temp.size() > 1) {
					jetInfo.add(temp);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		return jetInfo;
	}

}
