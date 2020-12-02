package minesweeper.game

import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    SwingUtilities.invokeLater {
        val frame = MinesweeperGame()
        frame.isVisible = true
    }
}