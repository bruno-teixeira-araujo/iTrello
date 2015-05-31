package org.arago.showcase.repository;

import java.util.List;

import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Card;

public interface CardRepository {
	List<Card> findAll(Session session, String boardId);
}
