package com.connect4.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connect4.entity.Game;
import com.connect4.model.BaseResponse;
import com.connect4.model.Connect4Board;
import com.connect4.model.Connect4RegResponse;
import com.connect4.model.Connect4Response;
import com.connect4.model.ErrorResponse;
import com.connect4.model.PlayerList;
import com.connect4.service.GameService;
import com.connect4.utils.Connect4Solver;

@RestController
public class Connect4Controller {

	private static final Logger logger = LoggerFactory.getLogger(Connect4Controller.class);
	@Autowired
	private GameService gameService;

	/**
	 * Initial Matrix.
	 */
	private int[][] matrix = new int[6][7];

//	@RequestMapping("/connect4/player/{playerId}/col/{col}")
//	public ResponseEntity<BaseResponse> connect4(@PathVariable Integer playerId, @PathVariable Integer col)
//			throws JSONException {
//		logger.info("Start Connect4 controller");
//		logger.debug("Path Variables player Id : " + playerId + " Col: " + col);
//
//		GameService x = new GameService();
//		String y = x.convertToString(matrix);
//		x.convertToArray(y);
//
//		// initiate the COONNECT4 board
//		Connect4Solver solver = new Connect4Solver(new Connect4Board(matrix));
//		if (col != null) {
//			int row = solver.getNearstRow(solver.getBoard(), col);
//			if (row != -1) {
//				boolean isValidEntry = solver.checkEntry(playerId, row, col);
//				boolean isWinner = solver.checkWin(playerId, solver.getBoard().getMatrix(), row, col);
//				new Connect4Response(solver.getBoard().getMatrix(), isWinner, isValidEntry);
//
//				return new ResponseEntity<BaseResponse>(
//						new Connect4Response(solver.getBoard().getMatrix(), isWinner, isValidEntry), HttpStatus.OK);
//
//			}
//
//			return new ResponseEntity<BaseResponse>(new Connect4Response(solver.getBoard().getMatrix(), false, false),
//					HttpStatus.OK);
//		}
//
//		return new ResponseEntity<BaseResponse>(new ErrorResponse("Error Playing the Game"),HttpStatus.OK);
//	}

	@RequestMapping(value = "/connect4/createPlayers", method = POST)
	public ResponseEntity<BaseResponse> register(@RequestBody PlayerList playerList) {

		logger.info("Pass 2 players 2 initiate a new game");
		logger.info("This method returns Game ID , and Players ID");
		logger.debug("Request Body Variables player1 Name : " + playerList.getPlayer1Name() + " player2 Name: "
				+ playerList.getPlayer2Name());

		Game game = gameService.createGame(playerList);
		if (game != null) {
			return new ResponseEntity<BaseResponse>(new Connect4RegResponse(game), HttpStatus.CREATED);
		}
		return new ResponseEntity<BaseResponse>(HttpStatus.OK);
	}

	@RequestMapping(value = "/connect4/game/{gameID}/player/{playerId}/col/{col}", method = PUT)
	public ResponseEntity<BaseResponse> updateGame(@PathVariable Integer gameID, @PathVariable Integer playerId,
			@PathVariable Integer col) throws JSONException {
		logger.info("Start Playing Game with Game Id "+ gameID);
		logger.debug("Path Variables player Id : " + playerId + " Col: " + col);

		Game game = gameService.getGame(gameID, playerId);
		if (game != null) {

			// check if move more than 42
			if(game.getCount() >=41 || !game.isStatus()){
				return new ResponseEntity<BaseResponse>(new ErrorResponse("Game is already Finished"),HttpStatus.OK);
			}
			
			matrix = gameService.convertToArray(game.getGameArray());
			Connect4Solver solver = new Connect4Solver(new Connect4Board(matrix));
			
			boolean isValidPlayer = solver.checkPlayer(game,playerId);
			if(!isValidPlayer){
				return new ResponseEntity<BaseResponse>(new ErrorResponse("This is not Player "+playerId+" turn"),HttpStatus.OK);
			}
			
			if (col != null) {
				if(col < 0 || col >= matrix[0].length){
					return new ResponseEntity<BaseResponse>(new ErrorResponse("This is not avlid Column Number"),HttpStatus.OK);					
				}
				int row = solver.getNearstRow(solver.getBoard(), col);
				if (row != -1) {
					boolean isValidEntry = solver.checkEntry(playerId, row, col);
					boolean isWinner = solver.checkWin(playerId, solver.getBoard().getMatrix(), row, col);
					if (isValidEntry) {
						game.setGameArray(gameService.convertToString(solver.getBoard().getMatrix()));
						game = gameService.updateGame(game, playerId, isWinner);
						if (game != null) {
							return new ResponseEntity<BaseResponse>(
									new Connect4Response(solver.getBoard().getMatrix(), isWinner, isValidEntry),
									HttpStatus.CREATED);
						}
					}
					return new ResponseEntity<BaseResponse>(new ErrorResponse("This is not avlid Entry"),HttpStatus.OK);
				}
				
				return new ResponseEntity<BaseResponse>(new ErrorResponse("This is not valid Column"),HttpStatus.OK);
			}
			return new ResponseEntity<BaseResponse>(new Connect4Response(solver.getBoard().getMatrix(), false, false),
					HttpStatus.OK);
		}
		// initiate the COONNECT4 board
		return new ResponseEntity<BaseResponse>(new ErrorResponse("Error Retriving game with the provided input"),HttpStatus.NOT_FOUND);

	}

}
