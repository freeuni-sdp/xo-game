/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.xo.game;

import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author toka
 */

@Path("{gameID}")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class GameService {

	static final HashMap<String, GameState> games = new HashMap<>();

	@GET
	public GameState getState(@PathParam("gameID") String gameID) {
		GameState state = games.get(gameID);
		if (state == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		return state;
	}

	@POST
	public Response registerPlayer(@PathParam("gameID") String gameID,
			JsonRequest request) {
		if (request == null || request.user_id == null)
			throw new WebApplicationException(Status.BAD_REQUEST);

		GameState state = games.get(gameID);
		if (state == null) {
			state = new GameState();
			games.put(gameID, state);
		}
		if (state.addPlayer(request.user_id))
			return Response.ok().build();

		return Response.status(Status.CONFLICT).build();
	}

	@PUT
	public GameState makeMove(@PathParam("gameID") String gameID,
			JsonRequest request) {
		if (request == null || request.user_id == null)
			throw new WebApplicationException(Status.BAD_REQUEST);

		GameState state = games.get(gameID);
		if (state == null)
			throw new WebApplicationException(Status.NOT_FOUND);

		if (!state.makeMove(request.user_id, request.cell))
			throw new WebApplicationException(Status.NOT_ACCEPTABLE);

		return state;
	}
}
