package com.example.racing_game;

import com.example.racing_game.game.RaceManager;
import com.example.racing_game.game.RacingGame;
import com.example.racing_game.input.InputDto;
import com.example.racing_game.input.InputView;
import com.example.racing_game.moving_strategy.MovingByRandomNumber;
import com.example.racing_game.moving_strategy.MovingStrategy;
import com.example.racing_game.output.OutputView;

public class RacingGameApplication {

	public static void main(String[] args) {
		InputDto input = InputView.input();
		MovingStrategy strategy = new MovingByRandomNumber();
		RaceManager raceManager = RaceManager.create(input, strategy);
		RacingGame game = RacingGame.create(raceManager);
		game.raceStart();
		OutputView.printHistory(raceManager.getHistory());
	}
}
