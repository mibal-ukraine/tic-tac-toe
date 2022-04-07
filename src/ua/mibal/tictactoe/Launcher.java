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

package ua.mibal.tictactoe;

import ua.mibal.tictactoe.component.*;
import ua.mibal.tictactoe.component.keypad.CellNumberConverter;
import ua.mibal.tictactoe.component.keypad.DesktopNumericKeypadCellNumberConverter;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public final class Launcher {

    public static void main(String[] args) {
        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
        final Game game = new Game(
                new DataPrinter(cellNumberConverter), new ComputerMove(), new UserMove(cellNumberConverter), new WinnerVerifier(), new CellVerifier()
        );
        game.play();
    }
}
