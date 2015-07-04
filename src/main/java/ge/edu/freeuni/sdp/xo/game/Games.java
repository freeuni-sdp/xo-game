package ge.edu.freeuni.sdp.xo.game;

import java.util.HashMap;
import java.util.HashSet;

public class Games {

	private static HashMap<String, GameState> games = new HashMap<>();
        private static HashSet<GameState> finished_games = new HashSet<>();

	public Games(HashMap<String, GameState> games) {
		Games.games = games;
	}

	public static GameState get(String id) {
                GameState state = games.get(id);
                if(finished_games.contains(state)){
                    games.remove(id);
                    finished_games.remove(state);
                }
		return state;
	}

	public static void put(String id, GameState state) {
		games.put(id, state);
	}
        
        public synchronized static void finishGame(GameState game){
            finished_games.add(game);
        }

}
