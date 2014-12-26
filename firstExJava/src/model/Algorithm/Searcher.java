package model.Algorithm;

import java.util.ArrayList;

import model.Algorithm.SearchDomain;

public interface Searcher {

	public ArrayList<Action> search(SearchDomain domain);
}
