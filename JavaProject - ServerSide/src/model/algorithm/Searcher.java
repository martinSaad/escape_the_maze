package model.algorithm;

import java.util.ArrayList;

import model.algorithm.SearchDomain;
/**
 * All common method of any type of searcher.
 */
public interface Searcher {

	/**
	 * Given a searchDomain the method returns ArrayList of action that solve the domain.
	 * @param domain
	 * @return ArrayList<Action>
	 */

	public ArrayList<Action> search(SearchDomain domain);
}
