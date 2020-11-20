package minesweeper.game

class Board(boardSize: Int, selectPosition: Pair<Int, Int>) {
    /**
     * マインスイーパーの爆弾と旗の位置を表す二次元リスト
     */
    private val board = Array(boardSize) { arrayOfNulls<Int>(boardSize) }

    /**
     * 対応するボードが隠れているかを表す二次元リスト
     */
    private val hiddenBlocks = Array(boardSize) { arrayOfNulls<Boolean>(boardSize) }

    init {
        hiddenBlocks.forEach {
            for (i in it.indices) {
                it[i] = true
            }
        }

        // 爆弾の場所を決める
        val bombPosition = MutableList(boardSize * boardSize) { it }

    }
}