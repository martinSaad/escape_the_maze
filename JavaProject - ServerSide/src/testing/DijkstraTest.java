package testing;

import static org.junit.Assert.*;
import model.algorithm.Dijkstra;
import model.algorithm.SearchDomain;
import model1.SearchDomainFactory;

import org.junit.Test;

public class DijkstraTest {

	private SearchDomain domain;
	private SearchDomainFactory domainsFactory;
	
	
	@Test
	public void test() {
		
		Dijkstra test = new Dijkstra();
		domainsFactory = new SearchDomainFactory();
		domain = domainsFactory.createDomain("NumbersGame", "123", "321");

		String action = test.search(domain).get(0).toString();
		
		assertEquals("switch '1'(0) and '3'(2)", action);
	}

}
