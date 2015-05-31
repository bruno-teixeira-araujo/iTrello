package org.arago.showcase.service;

import java.util.List;

import org.arago.showcase.model.DashboardElement;
import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Board;

public interface ITrelloService {
	List<Board> findBoards(Session session);
	List<DashboardElement> calculateDashboardElements(Session session, String boardId);
}
