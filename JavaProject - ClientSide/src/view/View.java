package view;

import tasks.Task;
import model.algorithm.State;
import model1.Solution;

/**
 * The View Interface represent the functionality of UI
 */

public interface View extends Task {
	void start() throws Exception;
	void displayCurrentState(State[][] matrix);
	void displaySolution(Solution solution);
	String getUserAction();
	String getProblemDescreption(); 
}
