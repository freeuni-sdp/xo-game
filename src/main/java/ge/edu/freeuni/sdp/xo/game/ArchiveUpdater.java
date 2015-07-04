package ge.edu.freeuni.sdp.xo.game;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchiveUpdater implements Updater {

	private static final String ACHEVE_SERVICE_URL = "http://xo-achiev.herokuapp.com/webapi/";

	@Override
	public void update(String player1, String player2, String winner) {
		if (winner == null) {
			setScore(player1, getScore(player1) + 2);
			setScore(player2, getScore(player2) + 2);
		} else if (player1.equals(winner)) {
			setScore(player1, getScore(player1) + 3);
			setScore(player2, getScore(player2) + 1);
		} else if (player2.equals(winner)) {
			setScore(player1, getScore(player1) + 1);
			setScore(player2, getScore(player2) + 3);
		}

	}

	private int getScore(String id) {
		URL url;
		String message;
		try {
			url = new URL(ACHEVE_SERVICE_URL + id);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
			if (con.getResponseCode() == 200) {
				message = con.getResponseMessage();
				return parse(message);
			}
			con.disconnect();
		} catch (MalformedURLException ex) {
			Logger.getLogger(GameService.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GameService.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return 0;
	}

	private int parse(String message) {
		// TODO: Parse response!
		return 0;
	}

	private void setScore(String id, int score) {
		URL url;
		try {
			url = new URL(ACHEVE_SERVICE_URL + "/" + id);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
			// TODO: Send request!
			con.disconnect();
		} catch (MalformedURLException ex) {
			Logger.getLogger(GameService.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GameService.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

}
