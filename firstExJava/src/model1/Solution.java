package model1;

import java.io.Serializable;
import java.util.ArrayList;

import model.Algorithm.Action;


public class Solution implements Serializable{
	private ArrayList<Action> actions;
	private String problemDescription;
	
	public ArrayList<Action> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	
	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
}
