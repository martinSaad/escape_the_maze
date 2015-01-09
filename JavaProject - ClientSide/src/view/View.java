package view;

import tasks.Task;
import model1.Solution;

public interface View extends Task {
	void start() throws Exception;
	void displayCurrentState();
	void displaySolution(Solution solution);
	String getUserAction();
	String getProblemDescreption();
}
