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
		app.input.close();
	}

	private void start() {
		//Welcome message
		intro();
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
	
	private void intro() {
		System.out.println("*******************************");
		System.out.println("Welcome to the Jets Application");
		System.out.println("*******************************");
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
		// User choice in menus
		int choice = 0;
		// While menu is running
		boolean isRunning = true;

		while (isRunning) {
			// Show user Choice menu
			presentUserChoiceMenu();
			// get user choice validate input
			choice = getUserSelection(input, 1, 9);
			// Create Iterator for looping conditions
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
				userRequestAddJet(input, airfield);
				break;
			case 8:
				userRequestRemoveJet(input, airfield);
				break;
			case 9:
				System.out.println("Thank you for using the app.");
				System.out.println("Good bye");
				isRunning = false;
				break;
			}

		}
	}

	private int getUserSelection(Scanner input, int startNumber, int stopNumber) {
		int userInput = -1;
		boolean isInvalid = true;
		while (isInvalid) {
			System.out.println();
			System.out.print("What is your choice: ");
			try {
				userInput = input.nextInt();
				if (userInput >= startNumber && userInput <= stopNumber) {
					isInvalid = false;
					input.nextLine();
					return userInput;
				} else {
					System.out.println("Please enter " + startNumber + "-" + stopNumber);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input type. Please enter " + startNumber + "-" + stopNumber);
				input.nextLine();
				continue;
			}
		}
		return userInput;
	}

	private Iterator<Jet> createJetIterator(AirField airField) {
		List<Jet> getJets = airField.getJets();
		Iterator<Jet> it = getJets.iterator();

		return it;
	}

	private void fuelUpTheFleet(Iterator<Jet> it) {
		while (it.hasNext()) {
			Jet aJet = it.next();
			if (aJet instanceof TankerJet) {
				((TankerJet) aJet).refuel();
				System.out.println();
			}
		}
	}

	private void dogFight(Iterator<Jet> it) {
		while (it.hasNext()) {
			Jet aJet = it.next();
			if (aJet instanceof FighterJet) {
				((FighterJet) aJet).fight();
				System.out.println();
			}
		}
	}

	private void userRequestAddJet(Scanner input, AirField airField) {
		
		while(true) {
			System.out.println("Create a jet?");
			System.out.println();
			System.out.println("Y|Yes|N|No");
			System.out.println();
			String createJet = input.nextLine();
			
			if(createJet.contains("N") || createJet.contains("n")) {
				System.out.println("back to main menu");
				break;
			}
		
			System.out.println("Please choose what kind of jet: ");
			System.out.println("1) JetImpl");
			System.out.println("2) FighterJet");
			System.out.println("3) TankerJet");
		
			//Get user plane to create, used generic list for multiple return value types
			int userChoicePlaneType = getUserSelection(input, 1, 3);
			List<String> userData = getUserJetData(input);
			
			//Create Jet return it to be added to airfield
			//Could have just passed in airfield and added it at creation
			Jet userJet = userCreatedJetMaker(userChoicePlaneType, userData);
			airField.addJetToAirField(userJet);
		}
	}

	private List<String> getUserJetData(Scanner input) {
		List<String> userData = new ArrayList();
		Double speed = 0.0;
		Integer range = 0;
		Long price = (long) 0;

		System.out.print("Please enter model: ");
		userData.add(input.nextLine());
		while (true) {

			try {
				if (speed == 0.0) {
					System.out.print("\nPlease enter speed: ");
					speed = input.nextDouble();
					userData.add(String.valueOf(speed));
				}

				if (range == 0) {
					System.out.print("\nPlease enter range: ");
					range = input.nextInt();
					userData.add(String.valueOf(range));
				}

				if (price == 0) {
					System.out.print("\nPlease enter price: ");
					price = input.nextLong();
					input.nextLine();
					userData.add(String.valueOf(price));
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter numbers.");
				input.nextLine();
			}
		}
		return userData;
	}

	private Jet userCreatedJetMaker(int userChoice, List<String> userData) {
		Jet newJet;

		// Parse Strings back to wrapper classes

		String model = userData.get(0);
		Double speed = Double.parseDouble(userData.get(1));
		Integer range = Integer.parseInt(userData.get(2));
		Long price = Long.parseLong(userData.get(3));

		//userChoice came from userRequestAddJet
		switch (userChoice) {
		case 1:
			newJet = new JetImpl(model, speed, range, price);
			break;
		case 2:
			newJet = new FighterJet(model, speed, range, price);
			break;
		case 3:
			newJet = new TankerJet(model, speed, range, price);
			break;
		default:
			newJet = null;
		}

		return newJet;
	}
	
	private void userRequestRemoveJet(Scanner input, AirField airfield) {
		boolean notValid = true;
		
		//Verify Jets on airfield
		if(airField.getJets().size() != 0 ) {
			
			int maxSize = Integer.MAX_VALUE;
			//List out options of jets
			airField.listAllJetsInAirField();
			
			while(notValid) {
				System.out.print("Please Enter what Jet Tailnumber to remove: ");
				int jetToRemove = getUserSelection(input, 1, maxSize);
				
				//Check Jet Tailnumbers for match 
				for (Jet jet : airfield.getJets()) {
					if(jet.getTailNumber() == jetToRemove) {
						airField.removeJetFromAirField(jet);
						notValid = false;
					}
				}
				
				//If Jet was not removed
				if(notValid) {
					System.out.println();
					System.out.println("Tail number not found please try again!");
					System.out.println();
				}
			}
		}else {
			System.out.println();
			System.out.println("Airfield is empty add jets.");
			System.out.println();
		}
	}
}
