/*
 * Copyright 2022 http://t.me/mibal_ua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.mibal.tictactoe.component;

import ua.mibal.tictactoe.model.GameTable;
import ua.mibal.tictactoe.model.Player;

import java.util.Random;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class Game {

    private final DataPrinter dataPrinter;

    private final Player player1;

    private final Player player2;

    private final WinnerVerifier winnerVerifier;

    private final CellVerifier drawVerifier;

    private final boolean canSecondPlayerMakeFirstMove;


    public Game(final DataPrinter dataPrinter,
                final Player player1,
                final Player player2,
                final WinnerVerifier winnerVerifier,
                final CellVerifier drawVerifier,
                final boolean canSecondPlayerMakeFirstMove) {
        this.dataPrinter = dataPrinter;
        this.player1 = player1;
        this.player2 = player2;
        this.winnerVerifier = winnerVerifier;
        this.drawVerifier = drawVerifier;
        this.canSecondPlayerMakeFirstMove = canSecondPlayerMakeFirstMove;
    }

    public void play() {
        dataPrinter.printInfoMessage("Use the following mapping table to specify a cell using numbers from 1 to 9:");
        dataPrinter.printMappingTable();
        final GameTable gameTable = new GameTable();

        if (canSecondPlayerMakeFirstMove && new Random().nextBoolean()) {
            player2.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
        }

        final Player[] players = {player1, player2};
        while (true) {
            for (final Player player : players) {
                player.makeMove(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (winnerVerifier.isWinner(gameTable, player)) {
                    dataPrinter.printInfoMessage(player + " WIN!");
                    printGameOver();
                    return;
                }
                if (drawVerifier.allCellsFilled(gameTable)) {
                    dataPrinter.printInfoMessage("DRAW!");
                    printGameOver();
                    return;
                }
            }
        }
    }

    private void printGameOver() {
        dataPrinter.printInfoMessage("GAME OVER!");
    }
}
