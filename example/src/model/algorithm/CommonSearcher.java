package model.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class CommonSearcher implements Searcher{
		
	// place all common things to all searchers here
	protected PriorityQueue<State> openList = new PriorityQueue<State>();
	protected PriorityQueue<State> closedList = new PriorityQueue<State>();
}

