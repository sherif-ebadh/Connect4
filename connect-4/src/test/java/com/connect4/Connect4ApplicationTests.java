package com.connect4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.connect4.model.PlayerList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Connect4ApplicationTests {

	private static String player1Id;
	private static String gameId;
	private static String player2Id;

	final static String create_game = "http://localhost:8080/connect4/createPlayers";
	final static String play_game = "http://localhost:8080/connect4/game/{gameID}/player/{playerId}/col/{col}";

	public static void main(String[] args) throws JSONException {

		createNewGame();
		playWithValidPlayer(gameId, player1Id);
		playWithInValidPlayer(gameId, player1Id);
		playWithValidPlayer2(gameId, player2Id);
		playWithInvalidColumn(gameId, player1Id);
		repeatGametillPlayer1Win(gameId, player1Id, player2Id);
	}

	static RestTemplate restTemplate = new RestTemplate();

	private static void createNewGame() throws JSONException {
		PlayerList pl = new PlayerList();
		pl.setPlayer1Name("test");
		pl.setPlayer2Name("test2");

		ResponseEntity<String> result = restTemplate.postForEntity(create_game, pl, String.class);
		assertEquals("201", result.getStatusCode().toString());
		assertThat(result.getBody(), CoreMatchers.containsString("gameId"));
		assertThat(result.getBody(), CoreMatchers.containsString("player1Id"));

		// Use the next values in the rest of Testing methods
		JSONObject resultObject = new JSONObject(result.getBody());
		gameId = resultObject.getString("gameId");
		player1Id = resultObject.getString("player1Id");
		player2Id = resultObject.getString("player2Id");
	}

	private static void playWithValidPlayer(String gameId, String player1Id) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.set("Accept", "application/json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("gameID", gameId);
		params.put("playerId", player1Id);
		params.put("col", "0");

		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);
		ResponseEntity<String> result = restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		System.out.println(result);
		assertEquals("201", result.getStatusCode().toString());
		assertThat(result.getBody(), CoreMatchers.containsString("\"isValidEntry\":true"));
		assertThat(result.getBody(), CoreMatchers.containsString("\"isWinner\":false"));
	}

	private static void playWithInValidPlayer(String gameId, String player1Id) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("gameID", gameId);
		params.put("playerId", player1Id);
		params.put("col", "0");

		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);
		ResponseEntity<String> result = restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		System.out.println(result);
		assertEquals("200", result.getStatusCode().toString());
		assertThat(result.getBody(), CoreMatchers.containsString("This is not Player " + player1Id + " turn"));
	}

	private static void playWithValidPlayer2(String gameId, String player2Id) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.set("Accept", "application/json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("gameID", gameId);
		params.put("playerId", player2Id);
		params.put("col", "0");

		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);
		ResponseEntity<String> result = restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		System.out.println(result);
		assertThat(result.getBody(), CoreMatchers.containsString("\"isValidEntry\":true"));
		assertThat(result.getBody(), CoreMatchers.containsString("\"isWinner\":false"));
	}

	private static void playWithInvalidColumn(String gameId, String player1Id) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("gameID", gameId);
		params.put("playerId", player1Id);
		params.put("col", "7");

		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);
		ResponseEntity<String> result = restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		System.out.println(result);
		assertEquals("200", result.getStatusCode().toString());
		assertThat(result.getBody(), CoreMatchers.containsString("This is not avlid Column Number"));

	}

	private static void repeatGametillPlayer1Win(String gameId, String player1Id, String player2Id) {
		HttpHeaders headers = new HttpHeaders();

		headers.set("Accept", "application/json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("gameID", gameId);
		params.put("playerId", player1Id);
		params.put("col", "6");

		Map<String, String> params2 = new HashMap<String, String>();
		params2.put("gameID", gameId);
		params2.put("playerId", player2Id);
		params2.put("col", "5");

		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);

		restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params2);

		restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params2);

		restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params2);
		ResponseEntity<String> result = restTemplate.exchange(play_game, HttpMethod.PUT, entity, String.class, params);
		System.out.println(result);
		assertEquals("201", result.getStatusCode().toString());

		assertThat(result.getBody(), CoreMatchers.containsString("\"isValidEntry\":true"));
		assertThat(result.getBody(), CoreMatchers.containsString("\"isWinner\":true"));

	}

}
