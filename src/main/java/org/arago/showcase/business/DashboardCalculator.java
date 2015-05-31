package org.arago.showcase.business;

import java.util.List;

import org.arago.showcase.model.DashboardElement;
import org.arago.showcase.model.rest.Boardlist;
import org.arago.showcase.model.rest.Card;

public interface DashboardCalculator {
	List<DashboardElement> calculateBoardInfo(List<Boardlist> boardlists, List<Card> cards);
}
