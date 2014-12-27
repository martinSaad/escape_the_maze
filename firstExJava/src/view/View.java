package view;

import tasks.Task;
import model1.Solution;

public interface View extends Task {
	void start();
	void displayCurrentState();
	void displaySolution(Solution solution);
	String getUserAction();
}
