package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entity.AirField;
import com.skilldistillery.jets.entity.FighterJet;
import com.skilldistillery.jets.entity.Jet;
import com.skilldistillery.jets.entity.JetImpl;
import com.skilldistillery.jets.entity.TankerJet;

public class JetsApplication {

	private AirField airField = new AirField();
	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();
		app.start();
	}

	private void start() {
		// File name to read from
		String fileName = "jets.txt";

		// Pull in starter data from outside file
		List<List<String>> jetStartInfo = pullJetInfoFromFile(fileName);

		// Instantiates a List of jets
		List<Jet> starterJets = createJetsFromData(jetStartInfo);

		// Adds the starter jets to the airfield
		populateAirFieldFromData(starterJets, airField);
		selectUserChoice(airField);

	}

	private List<Jet> createJetsFromData(List<List<String>> list) {
		List<Jet> newJets = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			// model, speed, range, price
			String typeOfJet = list.get(i).get(0);
			String model = list.get(i).get(1);
			Double speed = Double.parseDouble(list.get(i).get(2));
			Integer range = Integer.parseInt(list.get(i).get(3));
			Long price = Long.parseLong(list.get(i).get(4));

			switch (typeOfJet) {
			case "TankerJet":
				newJets.add(new TankerJet(model, speed, range, price));
				continue;
			case "FighterJet":
				newJets.add(new FighterJet(model, speed, range, price));
				continue;
			case "JetImpl":
				newJets.add(new JetImpl(model, speed, range, price));
				continue;
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
				if (temp.size() > 1) {
					jetInfo.add(temp);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		return jetInfo;
	}

	private void populateAirFieldFromData(List<Jet> jetsList, AirField airfield) {
		airfield.addListOfJets(jetsList);
	}

	private void presentUserChoiceMenu() {
		System.out.println("1) List fleet");
		System.out.println("2) Fly all jets");
		System.out.println("3) View fastest jet");
		System.out.println("4) View jet with longest range");
		System.out.println("5) Fuel up the fleet");
		System.out.println("6) Dogfight!");
		System.out.println("7) Add a jet to fleet");
		System.out.println("8) Remove a jet from fleet");
		System.out.println("9) Quit Program");
	}

	private void selectUserChoice(AirField airfield) {
		//User choice in menus
		int choice = 0;
		//While menu is running
		boolean isRunning = true;
		
		while (isRunning) {
			//Show user Choice menu
			presentUserChoiceMenu();
			//get user choice validate input
			choice = getUserSelection(input);
			//Create Iterator for looping conditions
			Iterator<Jet> it = createJetIterator(airfield);
			
			switch (choice) {
			case 1:
				airfield.listAllJetsInAirField();
				break;
			case 2:
				airfield.flyAllJets();
				break;
			case 3:
				airfield.fastestJet();
				break;
			case 4:
				airfield.longestRangeJet();
				break;
			case 5:
				fuelUpTheFleet(it);
				break;
			case 6:
				dogFight(it);
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				System.out.println("Thank you for using the app.");
				System.out.println("Good bye");
				isRunning = false;
				break;
			}
			
		}
	}

	private int getUserSelection(Scanner input) {
		int userInput = -1;
		boolean isInvalid = true;
		while (isInvalid) {
			
			try {
				userInput = input.nextInt();
				if (userInput > 0 && userInput < 10) {
					isInvalid = false;
					return userInput;
				} else {
					System.out.println("Please enter 1-9");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input type. Please enter 1-9");
				input.nextLine();
				continue;
			}
		}
		return userInput;
	}
	
	private Iterator<Jet> createJetIterator(AirField airField){
		List<Jet> getJets = airField.getJets();
		Iterator<Jet> it = getJets.iterator();
		
		return it;
	}
	
	private void fuelUpTheFleet(Iterator<Jet> it) {
		while(it.hasNext()) {
			Jet aJet = it.next();
			if(aJet instanceof TankerJet) {
				((TankerJet) aJet).refuel();
				System.out.println();
			}
		}
	}
	
	private void dogFight(Iterator<Jet> it) {
		while(it.hasNext()) {
			Jet aJet = it.next();
			if(aJet instanceof FighterJet) {
				((FighterJet) aJet).fight();
				System.out.println();
			}
		}
	}
}
