package ge.edu.freeuni.sdp.xo.game;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserValidator implements Validator {

	private static final String LOGIN_SERVICE_URL = "http://xo-login.herokuapp.com/webapi/login/users";

	@Override
	public boolean validate(String id) {
		URL url;
		try {
			url = new URL(LOGIN_SERVICE_URL + "/" + id);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
			boolean res = con.getResponseCode() == 200;
			con.disconnect();
			return res;
		} catch (MalformedURLException ex) {
			Logger.getLogger(GameService.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GameService.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return false;
	}

}
