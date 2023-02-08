package raceCarFacts;

import java.util.Objects;

/**
 * Creates a track car.
 * 
 * @author Nick Pantuso
 * 
 */
public class TrackCar extends Car {
	
	private int weight;
	private int topSpeed;
	private String racingType;
	
	/**
	 * Defines a track car via the below parameters.
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
	 * @param weight
	 * @param topSpeed
	 * @param racingType
	 */
	public TrackCar(int year, String make, String model, String desc, int power, int torque, int cylinderNum,
			Aspiration aspiration, String image, int weight, int topSpeed, String racingType) {
		super(year, make, model, desc, power, torque, cylinderNum, aspiration, image);
		this.weight = weight;
		this.topSpeed = topSpeed;
		this.racingType = racingType;
	}

	public int getWeight() {
		return weight;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public String getRacingType() {
		return racingType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(racingType, topSpeed, weight);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackCar other = (TrackCar) obj;
		return Objects.equals(racingType, other.racingType) && topSpeed == other.topSpeed && weight == other.weight;
	}

	/**
	 * Uses html to format the String in a JLabel.
	 */
	@Override
	public String toString() {
		String car = super.toString();
		car += "Weight: " + weight + " lbs<br>";
		car += "Top Speed: " + topSpeed + " mph<br>";
		car += "Racing Type: " + racingType + "</html>";
		return car;
	}

}
