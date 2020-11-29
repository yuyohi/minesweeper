package minesweeper.game

class Board(private val bombNum: Int, private val boardSize: Int, private val selectPosition: Pair<Int, Int>) {
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

        while (i < bombNum) {
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
        var aroundBomb = 0

        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, element ->
                for (n in -1..1) {
                    for (m in -1..1) {
                        if (n == 0 && m == 0) {  // 自分自身のとき数えない
                            continue
                        }
                        val temp = board.getOrNull(i + n)?.getOrNull(j + m)

                        if (temp != null) {
                            if (temp.state == State.BOMB) {
                                aroundBomb += 1
                            }
                        }

                    }
                }
            }
        }
    }

}