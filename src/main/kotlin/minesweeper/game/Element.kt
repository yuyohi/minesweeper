package minesweeper.game

/**
 * マス目を表すクラス
 *
 * @property hiddenFlag 隠れているかどうか
 * @property state マス目の状態
 * @property aroundBomb 周りの爆弾の数
 */
class Element (var hiddenFlag :Boolean = true, var state: State = State.EMPTY, var aroundBomb: Int = 0) {

}