package ge.edu.freeuni.sdp.xo.game;

public class ValidatorFactory {

	private static final UserValidator validator = new UserValidator();

	public static Validator getValidator() {
		return validator;
	}

}
