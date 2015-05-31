package org.arago.showcase.repository.rest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Boardlist;
import org.arago.showcase.repository.BoardListRepository;
import org.arago.showcase.util.RestClientUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository
public class RestBoardListRepositoryImpl implements BoardListRepository {
	private static final String GET_ALL = "https://api.trello.com/1/boards/{boardId}/lists";

	@SuppressWarnings("unchecked")
	@Override
	public List<Boardlist> findAll(Session session, String boardId) {
		List<Boardlist> boardLists = new LinkedList<>();
		Response response = RestClientUtil.get(session, GET_ALL.replaceAll("\\{boardId\\}", boardId));
		JSONArray json;
		try {
			json = (JSONArray) new JSONParser().parse(response.readEntity(String.class));
			
			Iterator<JSONObject> iterator = json.iterator();
			while(iterator.hasNext()) {
				JSONObject jsonObject = iterator.next();
				
				Boardlist board = new Boardlist();
				board.setId((String)jsonObject.get("id"));
				board.setName((String)jsonObject.get("name"));
				boardLists.add(board);
			}
			
		} catch (ParseException e) {
			throw new RuntimeException();
		}
		
		return boardLists;
	}
}
