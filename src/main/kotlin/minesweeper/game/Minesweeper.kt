package minesweeper.game

import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class Minesweeper(): JFrame(){
    init{
        initialize()
    }

    private fun initialize(){
        title = "Minesweeper"
        setSize(450, 550)
        defaultCloseOperation = DISPOSE_ON_CLOSE

        // ゲーム画面
        val gamePanel = GameDisplay(9, 10);
        contentPane.add(gamePanel, BorderLayout.CENTER)

        // 下部のパネル
        val setupPanel = JPanel()
        contentPane.add(setupPanel, BorderLayout.SOUTH)

        // リセットボタン
        val btnReset = JButton("Reset")
        setupPanel.add(btnReset)

    }
}