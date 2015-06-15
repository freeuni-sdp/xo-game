/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.xo.game;

import java.util.Arrays;
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

	static final String GAME_EMPTY = "EmptyGame";
	static final String GAME_RUNNING = "RunningGame";
	static final String GAME_FINISHED = "FinishedGame";

	static final String GAME_CLOSED = "ClosedGame";
	static final String GAME_OPENED = "OpenedGame";

	static final HashMap<String, GameState> games = new HashMap<>();
	static {
		GameState state;
		state = new GameState();
		state.status = GameState.STATUS_PENDING;
		games.put(GAME_EMPTY, state);

		state = new GameState();
		state.status = GameState.STATUS_RUNNING;
		state.table = Arrays.asList(16, 1, 2);
		games.put(GAME_RUNNING, state);

		state = new GameState();
		state.status = GameState.STATUS_FINISHED;
		state.table = Arrays.asList(16, 1, 4, 64, 32, 8, 256);
		state.winner = "x-player_id";
		games.put(GAME_FINISHED, state);
	}

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
		if (request.user_id == null)
			return Response.status(Status.BAD_REQUEST).build();
		System.out.println(request);
		System.out.println("game: " + gameID);
		switch (gameID) {
		case GAME_CLOSED:
			return Response.status(Status.CONFLICT).build();
		case GAME_OPENED:
			return Response.status(Status.OK).build();
		default:
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@PUT
	public GameState makeMove(@PathParam("gameID") String gameID,
			JsonRequest request) {
		System.out.println("user: " + request.user_id + ", cell: "
				+ request.cell);
		if (request.user_id == null || request.cell == 0)
			throw new WebApplicationException(Status.BAD_REQUEST);

		GameState state = games.get(gameID);
		if (state == null)
			throw new WebApplicationException(Status.NOT_FOUND);

		return state;
	}
}
