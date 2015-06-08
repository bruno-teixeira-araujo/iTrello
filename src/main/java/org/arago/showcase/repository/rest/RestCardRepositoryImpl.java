package org.arago.showcase.repository.rest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Card;
import org.arago.showcase.repository.CardRepository;
import org.arago.showcase.util.RestClientUtil;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository
public class RestCardRepositoryImpl implements CardRepository {
	private static final String GET_ALL = "https://api.trello.com/1/boards/{boardId}/cards";

	@SuppressWarnings("unchecked")
	@Override
	public List<Card> findAll(Session session, String boardId) {
		List<Card> cards = new LinkedList<>();
		Response response = RestClientUtil.get(session, GET_ALL.replaceAll("\\{boardId\\}", boardId));
		JSONArray json;
		try {
			json = (JSONArray) new JSONParser().parse(response.readEntity(String.class));
			
			Iterator<JSONObject> iterator = json.iterator();
			while(iterator.hasNext()) {
				JSONObject jsonObject = iterator.next();
				
				Card card = new Card();
				card.setId((String)jsonObject.get("id"));
				card.setName((String)jsonObject.get("name"));
				card.setIdBoard((String)jsonObject.get("idBoard"));
				card.setIdList((String)jsonObject.get("idList"));
				String due = (String)jsonObject.get("due");
				if(due != null && !due.isEmpty()) {
					DateTime dt = ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC).parseDateTime(due);
					card.setDueDate(dt.toDate());
				}
				cards.add(card);
			}
			
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return cards;
	}
}
