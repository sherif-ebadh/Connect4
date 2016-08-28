package com.connect4.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.connect4.dao.GameDAO;
import com.connect4.dao.PlayerDAO;
import com.connect4.entity.Game;
import com.connect4.entity.Player;
import com.connect4.model.PlayerList;

@Configuration

@Component
@Service
public class GameService {

	@Autowired
	private GameDAO gameDao;
	
	@Autowired
	private PlayerDAO playerDao;


	private final Logger logger = LoggerFactory.getLogger(GameService.class);


	public String convertToString(int[][] matrix) {
		JSONArray parentJsonArray = new JSONArray();
		// loop through your elements
		for (int i = 0; i < matrix.length; i++) {
			JSONArray childJsonArray = new JSONArray();
			for (int j = 0; j < matrix[i].length; j++) {
				childJsonArray.put(matrix[i][j]);
			}
			parentJsonArray.put(childJsonArray);
		}

		return parentJsonArray.toString();
	}
	
	public int[][] convertToArray(String parentJsonArray) throws JSONException {
		
		int [][] matrix = new int[6][7];
		JSONArray jsonArray = new JSONArray(parentJsonArray); 
		if (jsonArray != null) { 
		   int len = jsonArray.length();
		   for (int i=0;i<len;i++){ 
			   JSONArray jsonChildArray  = (JSONArray) jsonArray.get(i);
			   int childLen = jsonChildArray.length();
			   for (int j=0;j<childLen;j++){ 
				   matrix[i][j] = (int)jsonChildArray.get(j);;
			   }
			   
		   } 
		} 
		
		return matrix;
	}

	public Game createGame(PlayerList playerList) {
		// TODO Auto-generated method stub
		Player player1 = new Player(playerList.getPlayer1Name());
		playerDao.save(player1);
		Player player2 = new Player(playerList.getPlayer2Name());
		playerDao.save(player2);				
		
		Game game= new Game(player1,player2);
		game.setNextPlayer(player1.getId());
		game.setStatus(true);
		int[][] matrix = new int[6][7];
		game.setGameArray(convertToString(matrix));
		game = gameDao.save(game);
		if (game == null) {
			logger.info("game not created");
			return null;
		}
		logger.info("Game created with game ID" + game.getId());
		return game;
		
	}

	public Game getGame(Integer gameId, Integer playerId) {
		Game game = gameDao.findOne(gameId);
		if(game != null){
			return game;
		}
		return null;
	}

	public Game updateGame(Game game, Integer playerId, boolean isWinner) {
		int count = game.getCount();
		count++;
		game.setCount(count);
		if(isWinner || count == 41){
			game.setStatus(false);
		}
		if(game.getPlayer1().getId() == playerId.intValue() && game.getNextPlayer() == playerId.intValue()){
			game.setNextPlayer(game.getPlayer2().getId());
			
		}else if(game.getPlayer2().getId() == playerId.intValue() && game.getNextPlayer() == playerId.intValue()){
			game.setNextPlayer(game.getPlayer1().getId());
			
		}
		return gameDao.save(game);
		
	}
}