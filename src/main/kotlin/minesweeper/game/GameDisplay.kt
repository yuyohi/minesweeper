package minesweeper.game

import java.awt.Color
import java.awt.Font
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel

/**
 * ゲーム(ボード)画面のクラス
 *
 * @property gameBoard ゲームのボード
 */
class GameDisplay(boardSize: Int, bombNum: Int) : JPanel() {

    val gameBoard = Board(bombNum, boardSize)

    init {
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

                val listener = ElementButtonListener(this, gameBoard, i, j)
                button.addActionListener(listener)
                button.addMouseListener(listener)
            }
        }
    }

    /**
     * ゲームを終了する
     *
     * @param winner ユーザーが勝っているかどうか
     */
    fun finishGame(winner: Boolean) {
        val finishDialog = JLabel(
            if (winner) {
                "Game clear!!"
            } else {
                "Game over"
            }
        )
        finishDialog.font = Font("Arial", Font.PLAIN, 28)

        JOptionPane.showMessageDialog(this, finishDialog)

        gameBoard.resetBoard()
    }
}