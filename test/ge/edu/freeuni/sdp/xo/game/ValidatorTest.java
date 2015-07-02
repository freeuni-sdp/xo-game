package ge.edu.freeuni.sdp.xo.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidatorTest {

	private Validator v;

	@Before
	public void setUp() throws Exception {
		v = new UserValidator();
	}

	@Test
	public void testValidate() {
		// assertTrue(v.validate("d092c35b-ef40-4e88-8b1a-6350ca022dfb"));
	}

}
