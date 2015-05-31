package org.arago.showcase.business;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.arago.showcase.model.DashboardElement;
import org.arago.showcase.model.rest.Boardlist;
import org.arago.showcase.model.rest.Card;
import org.junit.Assert;
import org.junit.Test;

public class DashboardDueDateByBoardCalculatorTest {
	@Test
	public void testcalculateBoardInfo() {
		Boardlist boardlist = new Boardlist();
		boardlist.setId("1");
		boardlist.setName("Icy");
		
		Card card = new Card();
		card.setId("1");
		card.setIdList("1");
		
		List<Boardlist> boardlists = Arrays.asList(boardlist);
		List<Card> cards = Arrays.asList(card);
		
		DashboardElement dashboardElement = new DashboardElement("1", "Icy", cards.size());
		dashboardElement.setNrValidElems(BigDecimal.valueOf(1));
		dashboardElement.setPercent(BigDecimal.valueOf(100.0));
		List<DashboardElement> expectedDashboardElements = Arrays.asList(dashboardElement);
		
		DashboardDueDateByBoardCalculator boardCalculator = new DashboardDueDateByBoardCalculator();
		List<DashboardElement> dashboardElements = boardCalculator.calculateBoardInfo(boardlists, cards);
		
		Assert.assertArrayEquals(expectedDashboardElements.toArray(), dashboardElements.toArray());
	}
}
