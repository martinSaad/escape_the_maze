package model.algorithm;

import java.util.ArrayList;

import model.algorithm.SearchDomain;

public interface Searcher {

	public ArrayList<Action> search(SearchDomain domain);
}
