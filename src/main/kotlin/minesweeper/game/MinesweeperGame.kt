package minesweeper.game

import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class MinesweeperGame : JFrame() {
    /**
     * ゲーム画面
     */
    private val gamePanel = GameDisplay(9, 10)

    /**
     * 下部のパネル
     */
    private val setupPanel = JPanel()

    /**
     * リセットボタン
     */
    private val resetButton = JButton("Reset")

    init {
        initialize()
    }

    /**
     * ゲームの初期化をする
     */
    private fun initialize() {
        title = "Minesweeper"
        setSize(460, 530)
        defaultCloseOperation = DISPOSE_ON_CLOSE

        contentPane.add(gamePanel, BorderLayout.CENTER)

        contentPane.add(setupPanel, BorderLayout.SOUTH)

        setupPanel.add(resetButton)
        resetButton.addActionListener(ResetButtonListener(this))

    }

    /**
     * ボードをリセットする
     */
    fun reset() {
        gamePanel.gameBoard.resetBoard()
    }
}