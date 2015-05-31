package org.arago.showcase.repository;

import java.util.List;

import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Boardlist;

public interface BoardListRepository {
	List<Boardlist> findAll(Session session, String boardId);
}
