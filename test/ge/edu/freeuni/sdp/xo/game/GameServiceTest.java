package ge.edu.freeuni.sdp.xo.game;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

/* 
 * Tried this but didn't work.
 * 
 * import org.junit.runner.RunWith;
 * import org.powermock.core.classloader.annotations.PrepareForTest;
 * import org.powermock.modules.junit4.PowerMockRunner;
 * 
 * @RunWith(PowerMockRunner.class)
 * @PrepareForTest({ GameService.class })
 */
public class GameServiceTest extends JerseyTest {

	private static final String GAME_ID = "828a9e4";
	private static final String USER_ID_1 = "013a9e6";
	@SuppressWarnings("unused")
	private static final String USER_ID_2 = "321b1e4";

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
	public void testGetExisting() {

	}

	@Test
	public void testPutBadRequest() {
		Response actual = target(GAME_ID).request().put(
				Entity.json(new State()));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testPutRegisterNullPlayer() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = null;
		Response actual = target(GAME_ID).request().put(Entity.json(jr));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testPutRegisterOnePlayer() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = USER_ID_1;
		Response actual = target(GAME_ID).request().put(Entity.json(jr));
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testPost() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = USER_ID_1;
		Response actual = target(GAME_ID).request().post(Entity.json(jr));
		assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testPostBadRequest() {
		Response actual = target(GAME_ID).request().post(
				Entity.json(new State()));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testPostRegisterNullPlayer() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = null;
		Response actual = target(GAME_ID).request().post(Entity.json(jr));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@XmlRootElement
	private static class State {

	}

}
