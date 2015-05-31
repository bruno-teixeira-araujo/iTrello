package org.arago.showcase.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.arago.showcase.model.DashboardElement;
import org.arago.showcase.model.rest.Boardlist;
import org.arago.showcase.model.rest.Card;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

@Component
public class DashboardDueDateByBoardCalculator implements DashboardCalculator {

	@Override
	public List<DashboardElement> calculateBoardInfo(List<Boardlist> boardlists, List<Card> cards) {
		Map<String, DashboardElement> dashboard = initDashboardMap(boardlists, cards.size());
		DateTime now = new DateTime(DateTimeZone.UTC);
		
		// field DashboardElement fields
		for(Card card : cards) {
			DashboardElement element = dashboard.get(card.getIdList());
			
			if(card.getDueDate() == null || now.isBefore(new DateTime(card.getDueDate(), DateTimeZone.UTC))) {
				// card done on time
				element.incNrValidElems();
			}
		}
		
		//calculate percentage
		for(Map.Entry<String, DashboardElement> entry : dashboard.entrySet()) {
			calculatePercentage(entry.getValue());
		}
		
		return new LinkedList<DashboardElement>(dashboard.values());
	}
	
	public void calculatePercentage(DashboardElement element) {
		if(element.getNrDashboardTotalElems() != BigDecimal.valueOf(0)) {
			element.setPercent(element.getNrValidElems().multiply(BigDecimal.valueOf(100.0)).divide(element.getNrDashboardTotalElems(), RoundingMode.HALF_UP));
		} else {
			// if nrDashboardTotalElems == 0 then percent is 100%
			element.setPercent(BigDecimal.valueOf(100.0));
		}
	}
	
	private Map<String, DashboardElement> initDashboardMap(List<Boardlist> boardlists, long totalElems) {
		Map<String, DashboardElement> dashboard = new LinkedHashMap<>();
		for(Boardlist boardlist : boardlists) {
			dashboard.put(boardlist.getId(), new DashboardElement(boardlist.getId(), boardlist.getName(), totalElems));
		}
		return dashboard;
	}
}
