package minesweeper.game

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class ElementButtonListener(private val board: Board, val i: Int, val j: Int): ActionListener {

    public override fun actionPerformed(e: ActionEvent) {
        board.openBoard(i, j, true)
    }

}