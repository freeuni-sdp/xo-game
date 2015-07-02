package ge.edu.freeuni.sdp.xo.game;

import java.util.HashMap;

public class Games {

	private static final HashMap<String, GameState> games = new HashMap<>();

	public static GameState get(String id) {
		return games.get(id);
	}

	public static void put(String id, GameState state) {
		games.put(id, state);
	}

}
