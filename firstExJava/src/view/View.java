package view;

import model1.Solution;

public interface View {
	void start();
	void displayCurrentState();
	void displaySolution(Solution solution);
	String getUserAction();
}
