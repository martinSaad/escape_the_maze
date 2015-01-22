package model.algorithm;

import java.io.Serializable;

/**
 * The class represent an action needed to be done.<p>
 * Implements Serializable.
 */
public class Action implements Serializable{
	
	String description;
	private double price;
	
	
	public Action(String description) {
		this.description=description;
	}
	
	public Action() {	
	}

	@Override
	public int hashCode(){ // convert string to the hash map
		return description.hashCode();
	}
	
	@Override
	public String toString(){
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
