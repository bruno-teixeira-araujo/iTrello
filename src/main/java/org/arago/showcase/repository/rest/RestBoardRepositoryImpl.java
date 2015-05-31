package org.arago.showcase.repository.rest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Board;
import org.arago.showcase.repository.BoardRepository;
import org.arago.showcase.util.RestClientUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository
public class RestBoardRepositoryImpl implements BoardRepository {
	private static final String GET_ALL = "https://api.trello.com/1/members/me/boards";

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> findAll(Session session) {
		List<Board> boards = new LinkedList<>();
		Response response = RestClientUtil.get(session, GET_ALL);
		JSONArray json;
		try {
			json = (JSONArray) new JSONParser().parse(response.readEntity(String.class));
			
			Iterator<JSONObject> iterator = json.iterator();
			while(iterator.hasNext()) {
				JSONObject jsonObject = iterator.next();
				
				Board board = new Board();
				board.setId((String)jsonObject.get("id"));
				board.setName((String)jsonObject.get("name"));
				boards.add(board);
			}
			
		} catch (ParseException e) {
			throw new RuntimeException();
		}
		
		return boards;
	}
}
