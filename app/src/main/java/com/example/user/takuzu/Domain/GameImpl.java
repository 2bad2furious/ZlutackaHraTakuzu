package com.example.user.takuzu.Domain;

import com.example.user.takuzu.Domain.Game.Game;
import com.example.user.takuzu.Domain.Model.Coordinates;
import com.example.user.takuzu.Domain.Model.GameBoard;
import com.example.user.takuzu.Domain.Model.Solver;

/**
 * Created by user on 06.03.2017.
 */

public class GameImpl implements Game {
    private GameBoard gameBoard;

    public GameImpl(int size){
        gameBoard = new GameBoardImpl(size);
    }

    @Override
    public GameBoard onMoveMade(Coordinates coordinates) {
        if(!isMovePossible(coordinates)) throw new IllegalArgumentException("Move not possible");
        gameBoard = gameBoard.change(coordinates);
        return gameBoard;
    }

    @Override
    public boolean isMovePossible(Coordinates coordinates) {
        return (coordinates.getX() >= 0 &&
                coordinates.getX() < gameBoard.columns() &&
                coordinates.getY() >= 0 &&
                coordinates.getY() < gameBoard.rows());
    }

    @Override
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public boolean isGameOver() {
        return gameBoard.isBoardFull() && Solver.isSolved(gameBoard.fields());
    }
}
