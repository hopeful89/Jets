package com.skilldistillery.jets.entity;

public abstract class Jet {

	public static final double machSpeed = 767.269;
	public static int numCreated = 1;
	private int tailNumber;
	private String model;
	private double speed;
	private int range;
	private long price;
	private Pilot pilot;

	public Jet(String model, double speed, int range, long price) {
		super();
		this.tailNumber = numCreated;
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.pilot = new Pilot();
		numCreated++;
	}

	@Override
	public String toString() {
		return "Jet [tailNumber=" + tailNumber + ", model=" + model + ", speed=" + speed + ", range=" + range
				+ ", price= " + price + "]";
	}
	
	

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public abstract void fly();

	public void timeTilEmpty() {
		System.out.printf("I am able to fly: %.2f hours before empty!\n", range / speed);
	}

	public String getModel() {
		return model;
	}

	public int getTailNumber() {
		return tailNumber;
	}

	public void setTailNumber(int tailNumber) {
		this.tailNumber = tailNumber;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public double getSpeedInMach() {
		return speed / machSpeed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + range;
		long temp;
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + tailNumber;
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
		Jet other = (Jet) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (pilot == null) {
			if (other.pilot != null)
				return false;
		} else if (!pilot.equals(other.pilot))
			return false;
		if (price != other.price)
			return false;
		if (range != other.range)
			return false;
		if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed))
			return false;
		if (tailNumber != other.tailNumber)
			return false;
		return true;
	}

	

}
