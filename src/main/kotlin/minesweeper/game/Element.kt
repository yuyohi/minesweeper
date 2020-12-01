package minesweeper.game

import java.awt.Color
import javax.swing.JButton

/**
 * マス目を表すクラス
 *
 * @property hiddenFlag 隠れているかどうか
 * @property bomb 爆弾があるかどうか
 * @property aroundBomb 周りの爆弾の数
 * @property flag 旗が立っているかどうか
 */
class Element(
    var hiddenFlag: Boolean = true,
    var bomb: Boolean = false,
    var aroundBomb: Int = 0,
    var flag: Boolean = false
) : JButton() {

    /**
     * マス目を開ける関数
     */
    fun openElement() {
        hiddenFlag = false
        this.background = Color.WHITE
    }
}