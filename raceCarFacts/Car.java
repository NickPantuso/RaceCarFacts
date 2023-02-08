package raceCarFacts;

import java.util.Objects;

/**
 * Creates a car.
 * 
 * @author Nick Pantuso
 *
 */
public class Car {

	private int year;
	private String make;
	private String model;
	private String desc;
	private int power;
	private int torque;
	private int cylinderNum;
	private Aspiration aspiration;
	private String image;
	
	/**
	 * Defines a car via the below parameters.
	 * 
	 * @param year
	 * @param make
	 * @param model
	 * @param desc
	 * @param power
	 * @param torque
	 * @param cylinderNum
	 * @param aspiration
	 * @param image
	 */
	public Car(int year, String make, String model, String desc, int power, int torque, int cylinderNum,
			Aspiration aspiration, String image) {
		super();
		this.year = year;
		this.make = make;
		this.model = model;
		this.desc = desc;
		this.power = power;
		this.torque = torque;
		this.cylinderNum = cylinderNum;
		this.aspiration = aspiration;
		this.image = image;
	}

	public int getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getDesc() {
		return desc;
	}

	public int getPower() {
		return power;
	}

	public int getTorque() {
		return torque;
	}

	public int getCylinderNum() {
		return cylinderNum;
	}

	public Aspiration getAspiration() {
		return aspiration;
	}
	
	public String getImage() {
		return image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aspiration, cylinderNum, desc, image, make, model, power, torque, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return aspiration == other.aspiration && cylinderNum == other.cylinderNum && Objects.equals(desc, other.desc)
				&& Objects.equals(image, other.image) && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model) && power == other.power && torque == other.torque
				&& year == other.year;
	}

	/**
	 * Uses html to format the String in a JLabel.
	 */
	@Override
	public String toString() {
		String car = "";
		car += "<html>Year: " + year + "<br>";
		car += "Make: " + make + "<br>";
		car += "Model: " + model + "<br>";
		car += "Horsepower: " + power + "<br>";
		car += "Torque: " + torque + " ft lbs<br>";
		car += "Number of cylinders: " + cylinderNum + "<br>";
		car += "Aspiration: " + aspiration + "<br>";
		return car;
	}
	
}
