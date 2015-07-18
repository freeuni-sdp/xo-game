package ge.edu.freeuni.sdp.xo.game;

import java.util.HashMap;

public class Games {

	private static HashMap<String, GameState> games = new HashMap<>();

	public Games(HashMap<String, GameState> games) {
		Games.games = games;
	}

	public static GameState get(String id) {
		return games.get(id);
	}

	public static void put(String id, GameState state) {
		games.put(id, state);
	}
        
        public static void remove(String id){
                games.remove(id);
        }
        
}
