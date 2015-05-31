package org.arago.showcase.repository;

import java.util.List;

import org.arago.showcase.model.Session;
import org.arago.showcase.model.rest.Board;

public interface BoardRepository {
	List<Board> findAll(Session session);
}
