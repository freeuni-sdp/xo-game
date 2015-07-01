package ge.edu.freeuni.sdp.xo.game;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class GameServiceTest extends JerseyTest {

	private static final String GAME_ID = "828a9e4";

	@Override
	protected Application configure() {
		return new ResourceConfig(GameService.class);
	}

	@Test
	public void testGetNotExisting() {
		Response actual = target(GAME_ID).request().get();
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testPost() {
		// TODO: Not yet implemented
	}

	@Test
	public void testPut() {
		// TODO: Not yet implemented
	}

}
