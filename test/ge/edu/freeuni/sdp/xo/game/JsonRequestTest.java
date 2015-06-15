package ge.edu.freeuni.sdp.xo.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JsonRequestTest {

	private static String comma = ", ";

	@Test
	public void testToString() {
		String id = "DF674561AJSKDH";
		int cell = 16;
		JsonRequest jr = new JsonRequest();
		jr.user_id = id;
		jr.cell = cell;
		assertEquals("JsonRequestToString",
				id.concat(comma).concat(String.valueOf(cell)), jr.toString());
	}

}
