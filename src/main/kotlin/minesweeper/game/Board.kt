package minesweeper.game

class Board(private val bombnum: Int, private val boardSize: Int, private val selectPosition: Pair<Int, Int>) {
    /**
     * マインスイーパーのマス目を表現する二次元配列
     */
    private val board = Array(boardSize) { Array(boardSize) { Element() } }

    init {
        setBomb()


    }

    /**
     * 爆弾を配置するメソッド
     */
    private fun setBomb() {
        val bombPosition = MutableList(boardSize * boardSize) { it }
        bombPosition.shuffle()
        var i = 0

        while (i < bombnum) {
            val position = Pair(bombPosition[i] / boardSize, bombPosition[i] % boardSize)

            // ユーザが選択した場所に爆弾が配置されてしまった場合
            if (selectPosition.first == position.first && selectPosition.second == position.second) {
                continue
            }

            board[position.first][position.second].state = State.BOMB
            i += 1
        }
    }

    /**
     * 周りの爆弾を数える
     */
    fun countAroundBomb() {

    }

}