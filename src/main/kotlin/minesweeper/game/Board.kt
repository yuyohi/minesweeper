package minesweeper.game

class Board(bombnum: Int, boardSize: Int, selectPosition: Pair<Int, Int>) {
    /**
     * マインスイーパーの爆弾と旗の位置を表す二次元配列
     */
    private val board = Array(boardSize) { Array(boardSize) { State.EMPTY } }

    /**
     * 周りに存在する爆弾の数を表す二次元配列
     */

    /**
     * 対応するボードが隠れているかを表す二次元配列
     */
    private val hiddenBlocks = Array(boardSize) { Array(boardSize) { true } }

    init {
        board.forEach {
            for (i in it.indices) {
                it[i] = State.EMPTY
            }
        }
        hiddenBlocks.forEach {
            for (i in it.indices) {
                it[i] = true
            }
        }

        // 爆弾の場所を決める
        val bombPosition = MutableList(boardSize * boardSize) { it }
        bombPosition.shuffle()
        var i = 0
        while (i < bombnum) {
            val position = Pair(bombPosition[i] / boardSize, bombPosition[i] % boardSize)

            // ユーザが選択した場所に爆弾が配置されてしまった場合
            if (selectPosition.first == position.first && selectPosition.second == position.second) {
                continue
            }

            board[position.first][position.second] = State.BOMB
            i += 1
        }
    }
}