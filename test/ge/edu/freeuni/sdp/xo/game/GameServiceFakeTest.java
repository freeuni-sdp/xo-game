package ge.edu.freeuni.sdp.xo.game;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;

public class GameServiceFakeTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(GameService.class);
	}

	@Test
	@Ignore
	public void testGetStatusOk() {
		Response actual = target(/* GameService.GAME_EMPTY */).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
	}

	@Test
	public void testGetStatusError() {
		Response actual = target("kasgdh").request().get();
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

	@Test
	@Ignore
	public void testGetResponseOk() {
		Response actual = target(/* GameService.GAME_EMPTY */).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
		// TODO: Response needs to be checked!
	}

	@Test
	@Ignore
	public void testPutStatusOk() {
		JsonRequest jr = new JsonRequest();
		jr.cell = 16;
		jr.user_id = "me";
		Response actual = target(/* GameService.GAME_EMPTY */).request().put(
				Entity.json(jr));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
	}

	@Test
	public void testPutStatusNotFound() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = "me";
		jr.cell = 16;
		Response actual = target("ads").request().put(Entity.json(jr));
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

	@Test
	@Ignore
	public void testPutStatusBadRequest() {
		JsonRequest jr = new JsonRequest();
		Response actual = target("ads").request().put(Entity.json(jr));
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

	@Test
	@Ignore
	public void testPutStatusBadRequestNull() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = "me";
		jr.cell = 0;
		Response actual = target("ads").request().put(Entity.json(jr));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	@Ignore
	public void testPostStatusOk() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = "me";
		jr.cell = 0;
		Response actual = target(/* GameService.GAME_OPENED */).request().post(
				Entity.json(jr));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
	}

	@Test
	@Ignore
	public void testPostStatusConflict() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = "me";
		jr.cell = 0;
		Response actual = target(/* GameService.GAME_CLOSED */).request().post(
				Entity.json(jr));
		assertEquals(Response.Status.CONFLICT.getStatusCode(),
				actual.getStatus());
	}

	@Test
	@Ignore
	// ignore because user_id validation is not yet implemented
	public void testPostStatusNotFound() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = "me";
		jr.cell = 0;
		Response actual = target("dsfghjk").request().post(Entity.json(jr));
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

	@Test
	@Ignore
	public void testPostStatusBadRequst() {
		JsonRequest jr = new JsonRequest();
		Response actual = target(/* GameService.GAME_OPENED */).request().post(
				Entity.json(jr));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	@Ignore
	public void testPostStatusEmpty() {
		JsonRequest jr = new JsonRequest();
		jr.user_id = "me";
		Response actual = target(/* GameService.GAME_EMPTY */).request().post(
				Entity.json(jr));
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

}
