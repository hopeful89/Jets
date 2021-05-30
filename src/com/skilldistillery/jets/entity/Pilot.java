package com.skilldistillery.jets.entity;

import java.util.Arrays;
import java.util.List;

public class Pilot {
	private static int numberOfPilots = 1;
	private String firstName;
	private String lastName;
	private int pilotNumber;
	private List<String> fNames = Arrays.asList("Bill", "Bob", "Peter", "Ted", "Braedan");
	private List<String> lNames = Arrays.asList("Nye", "Thorton", "Smith", "Bundy", "Parker");
	
	public Pilot() {
		this.firstName = randomPilotAssignment(fNames);
		this.lastName = randomPilotAssignment(lNames);
		this.pilotNumber = numberOfPilots;
		numberOfPilots++;
	}
	
	private String randomPilotAssignment(List<String> names) {
		int randomNum = (int)(Math.random() * names.size());
		String name = names.get(randomNum);

		return name;
	}

	public int getPilotNumber() {
		return pilotNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getfNames() {
		return fNames;
	}
	
	//add addition pilots to array
	public void setfNames(String fNames) {
		this.fNames.add(fNames);
	}

	public List<String> getlNames() {
		return lNames;
	}

	public void setlNames(List<String> lNames) {
		this.lNames = lNames;
	}
	
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fNames == null) ? 0 : fNames.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lNames == null) ? 0 : lNames.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + pilotNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilot other = (Pilot) obj;
		if (fNames == null) {
			if (other.fNames != null)
				return false;
		} else if (!fNames.equals(other.fNames))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lNames == null) {
			if (other.lNames != null)
				return false;
		} else if (!lNames.equals(other.lNames))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pilotNumber != other.pilotNumber)
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
