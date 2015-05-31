package org.arago.showcase.web;

import java.util.List;
import java.util.Map;

import org.arago.showcase.model.DashboardElement;
import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Board;
import org.arago.showcase.service.ITrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"session"})
public class BoardsController {
    private final ITrelloService iTrelloService;

    @Autowired
    public BoardsController(ITrelloService iTrelloService) {
        this.iTrelloService = iTrelloService;
    }
    
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public void boards(@ModelAttribute("session") Session session, Map<String, Object> model) {
		List<Board> boards = iTrelloService.findBoards(session);
		model.put("boards", boards);
	}
	
	@RequestMapping(value = "/board/{boardId}", method = RequestMethod.GET)
	public String board(@ModelAttribute("session") Session session, @PathVariable("boardId") String boardId,
			Map<String, Object> model) {
		List<DashboardElement> dashboardElements = iTrelloService.calculateDashboardElements(session, boardId);
		model.put("dashboardElements", dashboardElements);
		return "board";
	}
}
