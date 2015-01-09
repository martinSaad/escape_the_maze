package model.algorithm;

import model.algorithm.State;

abstract public class State implements Comparable<State>{

	// the string representation of the state
		protected String state;
		protected State cameFrom;
		protected double price;
		protected Action cameWithAction; 
		protected double gFunc; // g Func
		
		
		
		
		public double getGFunc() {
			return gFunc;
		}
		public void setGFunc(double gFunc) {
			this.gFunc = gFunc;
		}
		public State getCameFrom() {
			return cameFrom;
		}
		public void setCameFrom(State cameFrom) {
			this.cameFrom = cameFrom;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Action getCameWithAction() {
			return cameWithAction;
		}
		public void setCameWithAction(Action cameWithAction) {
			this.cameWithAction = cameWithAction;
		}
		
		public String getState(){
			return state;
		}

		
		@Override
		public String toString(){
			return state;
		}
		
		@Override
		public boolean equals(Object o){
			return state.equals(((State)o).getPrice());
		}
		
		// state's evaluation with respect to the goal - will be the Heuristic function in A*
		public abstract double getEvaluation(State goal);
		
		@Override
		public int compareTo(State state)//compare 2 states to decide which state will be the first in queue
		{
			if (this.getPrice() > state.getPrice())
				return 1;
			else if (this.getPrice() < state.getPrice())
				return -1;
			return 0;
		}
		
		

}
