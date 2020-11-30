package minesweeper.game

import java.awt.Color
import javax.swing.JButton
import javax.swing.JPanel

class GameDisplay(private val boardSize: Int, private val bombNum: Int): JPanel() {

    /**
     * ボタン
     */
    private val board = Array(boardSize) { Array(boardSize) { JButton() } }

    /**
     * ボタンの中身
     */
    private val boardCore = Board(bombNum, boardSize)

    init{
        layout = null

        setButton(50)

    }


    /**
     * ボタンを配置するメソッド
     */
    private fun setButton(buttonSize: Int) {
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, button ->
                button.background = Color.LIGHT_GRAY
                button.setBounds(i * buttonSize, j * buttonSize, buttonSize, buttonSize)

                add(button)

                button.addActionListener(ElementButtonListener(boardCore, i, j))
            }
        }
    }


}