package model1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import model1.Solution;
import model1.SolutionManager;


/**
 * The class maintains HashMap of solution.<p>
 * Every time the class is created it loads all the solution form the file to the HashMap. Every new solution is saved in the file.<p><p>
 * The class is written in Singleton pattern.
 */
public class SolutionManager {
	private HashMap<String, Solution> solutionsMap;
	private static SolutionManager instance = null;
	private static final String FILE_NAME = "resources/solutions.dat";
	
	
	/**
	 * Default constructor. Loads all the solutions from the file to the HashMap.
	 */

	protected SolutionManager(){	
		solutionsMap = new HashMap<String, Solution>();
		File f = new File("resources/solutions.dat");
		//if we have solutions in the "solutions.dat" - read from there to the hashMap
		if (f.exists()){
			try {
				ObjectInputStream in= new ObjectInputStream(new FileInputStream("resources/solutions.dat"));
				try {
					solutionsMap = (HashMap<String, Solution>) in.readObject();
					in.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * The method is used to create an instance of SolutionManager
	 * @return SolutionManager
	 */
	public static SolutionManager getInstance() {
		if (instance == null) {
			instance = new SolutionManager();			
		}
		return instance;
	}
	
	public void addSolution(Solution solution) {
		solutionsMap.put(solution.getProblemDescription(), solution);
	}
	
	public Solution getSolution(String problemDescription) {
		return solutionsMap.get(problemDescription);
	}
	
	/**
	 * Save the solution in the file.
	 */
	public void saveSolutionsInFile() {
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			out = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(out);
			oos.writeObject(solutionsMap);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
