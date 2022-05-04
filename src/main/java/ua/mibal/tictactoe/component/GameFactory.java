/*
 * Copyright (c) 2022. http://t.me/mibal_ua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ua.mibal.tictactoe.component;

import ua.mibal.tictactoe.component.console.ConsoleDataPrinter;
import ua.mibal.tictactoe.component.console.ConsoleUserInputReader;
import ua.mibal.tictactoe.component.keypad.DesktopNumericKeypadCellNumberConverter;
import ua.mibal.tictactoe.model.Player;
import ua.mibal.tictactoe.model.PlayerType;

import static ua.mibal.tictactoe.model.PlayerType.USER;
import static ua.mibal.tictactoe.model.Sign.O;
import static ua.mibal.tictactoe.model.Sign.X;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    public GameFactory(final String[] args) {
        final CommandLineArgumentParser.PlayerTypes playerTypes =
                new CommandLineArgumentParser(args).parse();
        this.player1Type = playerTypes.getPlayer1Type();
        this.player2Type = playerTypes.getPlayer2Type();
    }

    public Game create() {
        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
        final DataPrinter dataPrinter = new ConsoleDataPrinter(cellNumberConverter);
        final UserInputReader userInputReader = new ConsoleUserInputReader(cellNumberConverter, dataPrinter);
        final boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;

        final Player player1;
        final Player player2;
        if (player1Type == USER) {
            player1 = new Player(X, new UserMove(userInputReader, dataPrinter));
        } else {
            player1 = new Player(X, new ComputerMove());
        }
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(userInputReader, dataPrinter));
        } else {
            player2 = new Player(O, new ComputerMove());
        }
        return new Game(
                dataPrinter,
                player1,
                player2,
                new WinnerVerifier(),
                new CellVerifier(),
                canSecondPlayerMakeFirstMove
        );
    }
}