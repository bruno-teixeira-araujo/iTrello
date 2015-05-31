package org.arago.showcase.service;

import java.util.List;

import org.arago.showcase.business.DashboardCalculator;
import org.arago.showcase.model.DashboardElement;
import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Board;
import org.arago.showcase.model.rest.Boardlist;
import org.arago.showcase.model.rest.Card;
import org.arago.showcase.repository.BoardListRepository;
import org.arago.showcase.repository.BoardRepository;
import org.arago.showcase.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ITrelloServiceImpl implements ITrelloService {
	private BoardRepository boardRepository;
	private BoardListRepository boardListRepository;
	private CardRepository cardRepository;
    @Autowired
    @Qualifier("dashboardCalculator")
	private DashboardCalculator dashboardCalculator;
	
    @Autowired
    public ITrelloServiceImpl(BoardRepository boardRepository, BoardListRepository boardListRepository, 
    		CardRepository cardRepository) {
        this.boardRepository = boardRepository;
        this.boardListRepository = boardListRepository;
        this.cardRepository = cardRepository;
    }

	@Override
	public List<Board> findBoards(Session session) {
		return boardRepository.findAll(session);
	}
	
	@Override
	public List<DashboardElement> calculateDashboardElements(Session session, String boardId) {
		List<Boardlist> boardLists = boardListRepository.findAll(session, boardId);
		List<Card> cards = cardRepository.findAll(session, boardId);
		
		return dashboardCalculator.calculateBoardInfo(boardLists, cards);
	}
}
