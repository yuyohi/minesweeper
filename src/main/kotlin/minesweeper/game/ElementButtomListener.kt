package minesweeper.game

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class ElementButtonListener(private val gameBoard: Board, private val i: Int, private val j: Int) : ActionListener {

    override fun actionPerformed(e: ActionEvent) {
        if (gameBoard.firstTime) {
            gameBoard.initial(i, j)
        }

        val success = gameBoard.openBoard(i, j, true)
        if (success) {
            gameBoard.board[i][j].openElement()
        }
    }

}