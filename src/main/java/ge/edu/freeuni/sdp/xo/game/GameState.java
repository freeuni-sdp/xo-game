/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.xo.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author toka
 */
@XmlRootElement
public class GameState {
	public static final int STATUS_PENDING = 0;
	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_FINISHED = 2;

	private static final Set<Integer> validCells;
	private static final int[] winners;
	static {
		validCells = new HashSet<>();
		for (int i = 0; i < 9; i++)
			validCells.add((int) Math.pow(2, i));

		winners = new int[] { 7, 56, 448, 73, 146, 292, 273, 84 };
	}

	@XmlElement
	public int status;

	@XmlElement
	public List<Integer> table;

	@XmlElement
	public String winner;

	private String player1;
	private String player2;
	private String active;
        private String gameID;
        private GameState nextGame;

	public GameState() {
		status = STATUS_PENDING;
		table = new ArrayList<>();
		winner = null;
		player1 = player2 = active = null;
	}
        
        public GameState(String gameID){
            this();
            this.gameID = gameID;
        }

	public boolean addPlayer(String playerID) {
                if (status == STATUS_FINISHED){
                        if(nextGame == null)
                                nextGame = new GameState(gameID);

                        boolean res = nextGame.addPlayer(playerID);
                        if(nextGame.status == STATUS_RUNNING){
                                // replace current GameState with nextGame
                                // after that, current GameState will be deleted
                                Games.put(gameID, nextGame);
                        }

                        return res;
                }
		if (status == STATUS_PENDING) {
			if (player1 == null)
				player1 = playerID;
			else {
				if (player1.equals(playerID) || player2 != null)
					return false;

				player2 = playerID;
				status = STATUS_RUNNING;
				active = player1;
			}
			return true;
		}
		return false;
	}

	public boolean makeMove(String playerID, int cell) {
		if (status != STATUS_RUNNING)
			return false;
		if (!checkValidCell(cell))
			return false;
		if (!active.equals(playerID))
			return false;
		table.add(cell);
		if (checkWinner(playerID)) {
			winner = playerID;
			status = STATUS_FINISHED;
		} else if (table.size() == 9){
			status = STATUS_FINISHED;
                }else
			switchActivePlayer();

		return true;
	}

	public String getWinner() {
		return winner;
	}

	public String getPlayerOne() {
		return player1;
	}

	public String getPlayerTwo() {
		return player2;
	}

	protected boolean checkWinner(String player) {
		int i = player.equals(player1) ? 0 : 1;
		int sum = 0;
		for (; i < table.size(); i += 2) {
			sum += table.get(i);
		}
		for (i = 0; i < winners.length; i++) {
			if ((winners[i] & sum) == winners[i])
				return true;
		}
		return false;
	}

	protected void switchActivePlayer() {
		if (active.equals(player1))
			active = player2;
		else
			active = player1;
	}

	private boolean checkValidCell(int cell) {
		return (!table.contains(cell)) && validCells.contains(cell);
	}

	@Override
	public String toString() {
		return status + ", " + player1 + " : " + player2 + ", " + table + ", "
				+ active;
	}

}
