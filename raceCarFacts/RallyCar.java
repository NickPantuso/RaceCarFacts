package raceCarFacts;

import java.util.Objects;

/**
 * Creates a rally car.
 * 
 * @author Nick Pantuso
 *
 */
public class RallyCar extends Car {
	
	private boolean fogLights;
	private Drivetrain drivetrain;
	private String group;
	
	/**
	 * Defines a rally car via the below parameters.
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
	 * @param fogLights
	 * @param drivetrain
	 * @param group
	 */
	public RallyCar(int year, String make, String model, String desc, int power, int torque, int cylinderNum,
			Aspiration aspiration, String image, boolean fogLights, Drivetrain drivetrain, String group) {
		super(year, make, model, desc, power, torque, cylinderNum, aspiration, image);
		this.fogLights = fogLights;
		this.drivetrain = drivetrain;
		this.group = group;
	}

	public boolean getFogLights() {
		return fogLights;
	}

	public Drivetrain getDrivetrain() {
		return drivetrain;
	}

	public String getGroup() {
		return group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(drivetrain, fogLights, group);
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
		RallyCar other = (RallyCar) obj;
		return drivetrain == other.drivetrain && fogLights == other.fogLights && Objects.equals(group, other.group);
	}

	/**
	 * Uses html to format the String in a JLabel.
	 */
	@Override
	public String toString() {
		String car = super.toString();
		car += "Has fog lights: " + fogLights + "<br>";
		car += "Drivetrain: " + drivetrain + "<br>";
		car += "Group: " + group + "</html>";
		return car;
	}

}
