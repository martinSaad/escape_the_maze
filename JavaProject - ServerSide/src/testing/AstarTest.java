package testing;

import static org.junit.Assert.*;

import model.algorithm.Astar;
import model.algorithm.SearchDomain;
import model1.SearchDomainFactory;

import org.junit.Test;

public class AstarTest {

	
	private SearchDomain domain;
	private SearchDomainFactory domainsFactory;

	
	
	@Test
	public void test() {
		Astar test = new Astar();
		domainsFactory = new SearchDomainFactory();
		domain = domainsFactory.createDomain("NumbersGame", "123", "321");

		String action = test.search(domain).get(0).toString();
		
		assertEquals("switch '1'(0) and '3'(2)", action);
		
		
	}

}
