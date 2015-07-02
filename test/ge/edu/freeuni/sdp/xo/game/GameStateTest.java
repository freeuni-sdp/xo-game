package ge.edu.freeuni.sdp.xo.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameStateTest {
	private GameState gs;
	private static final String WINNER = "yd5uiy63";

	private static final String USER_ID_1 = "013a9e6";
	private static final String USER_ID_2 = "321b1e4";

	@Before
	public void setUp() throws Exception {
		gs = new GameState();
	}

	@Test
	public void testAddPlayer() {
		assertTrue(gs.addPlayer(USER_ID_1));
	}

	@Test
	public void testAddPlayerContinuous() {
		assertTrue(gs.addPlayer(USER_ID_1));
		assertFalse(gs.addPlayer(USER_ID_1));
		assertTrue(gs.addPlayer(USER_ID_2));
		assertFalse(gs.addPlayer(USER_ID_2));
	}

	@Test
	public void testMakeMove() {
		assertTrue(gs.addPlayer(USER_ID_1));
		assertFalse(gs.addPlayer(USER_ID_1));
		assertTrue(gs.addPlayer(USER_ID_2));
		assertFalse(gs.addPlayer(USER_ID_2));
		assertFalse(gs.makeMove(USER_ID_2, 1));
		assertFalse(gs.makeMove(USER_ID_1, 7));
		assertTrue(gs.makeMove(USER_ID_1, 1));
		assertTrue(gs.makeMove(USER_ID_2, 8));
		assertTrue(gs.makeMove(USER_ID_1, 16));
		assertFalse(gs.makeMove(USER_ID_2, 16));
		assertTrue(gs.makeMove(USER_ID_2, 2));
		assertTrue(gs.makeMove(USER_ID_1, 256));
		assertFalse(gs.makeMove(USER_ID_1, 4));
	}

	@Test
	public void testGetWinner() {
		gs.winner = WINNER;
		assertEquals(WINNER, gs.getWinner());
	}

}
