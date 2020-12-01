package minesweeper.game

import java.awt.Color
import javax.swing.JPanel

class GameDisplay(private val boardSize: Int, private val bombNum: Int): JPanel() {

    /**
     *  ボード
     */
    private val gameBoard = Board(bombNum, boardSize)

    init{
        layout = null

        setButton(50)

    }


    /**
     * ボタンを配置するメソッド
     */
    private fun setButton(buttonSize: Int) {
        gameBoard.board.forEachIndexed { i, row ->
            row.forEachIndexed { j, button ->
                button.background = Color.LIGHT_GRAY
                button.setBounds(i * buttonSize, j * buttonSize, buttonSize, buttonSize)

                add(button)

                button.addActionListener(ElementButtonListener(gameBoard, i, j))
            }
        }
    }


}