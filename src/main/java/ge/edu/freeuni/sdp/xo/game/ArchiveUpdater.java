package ge.edu.freeuni.sdp.xo.game;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
		AchievEntity p1Achieve = getScore(player1);
		AchievEntity p2Achieve = getScore(player2);
		int p1Score, p2Score;
		if (winner == null) {
			p1Score = p2Score = 2;
		} else if (player1.equals(winner)) {
			p1Score = 3;
			p2Score = 1;
		} else {
			p1Score = 1;
			p2Score = 3;
		}
		setScore(player1, p1Achieve.getScore() + p1Score);
		setScore(player2, p2Achieve.getScore() + p2Score);

	}

	private AchievEntity getScore(String id) {
		Client client = ClientBuilder.newClient(new ClientConfig());
		return client.target(ACHEVE_SERVICE_URL + id).request().get(AchievEntity.class);
	}

	private void setScore(String id, int score) {
		String payload = "{\"score\": "+score+"}";
		Client client = ClientBuilder.newClient(new ClientConfig());
		Response response = client.target(ACHEVE_SERVICE_URL + id).request().put(Entity.json(payload));
	}

}
