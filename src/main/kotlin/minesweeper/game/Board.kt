package minesweeper.game

class Board(private val bombNum: Int, private val boardSize: Int) {
    /**
     * マインスイーパーのマス目を表現する二次元配列
     */
    private val board = Array(boardSize) { Array(boardSize) { Element() } }

    /**
     * ユーザーが選択したマス目
     */
    private var selectPosition: Pair<Int, Int> = Pair(0, 0)

    /**
     * ボードを初期化する関数
     */
    fun initial() {
        setBomb()
        countAroundBomb()
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
    private fun countAroundBomb() {
        var aroundBomb = 0

        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, element ->
                //  周りの爆弾の個数を数える
                aroundBomb = 0
                for (n in -1..1) {
                    for (m in -1..1) {
                        if (n == 0 && m == 0) {  // 自分自身のとき数えない
                            continue
                        }
                        val temp = board.getOrNull(i + n)?.getOrNull(j + m)

                        if (temp?.state == State.BOMB) {
                            aroundBomb += 1
                        }

                    }
                }
                element.aroundBomb = aroundBomb
            }
        }
    }

    /**
     * マス目を空ける関数
     *
     * @param i 行
     * @param j 列
     * @param user ユーザーがクリックしたものかどうか(ユーザーがクリックしたときは爆弾でも空ける)
     * @return 空けることに成功したかどうか
     */
    fun openBoard(i: Int, j: Int, user: Boolean): Boolean{
        // 範囲外のとき
        if (board.getOrNull(i)?.getOrNull(j) == null) {
            return true
        }

        // ユーザーが開けたとき
        if (user) {
            if (board[i][j].state == State.BOMB)
                board[i][j].hiddenFlag = false
                return false
        }

        if (board[i][j].state == State.EMPTY) {
            board[i][j].hiddenFlag = false
            // 周りを探索
            for (n in -1..1) {
                for (m in -1..1) {
                    if (n == 0 && m == 0) {  // 自分自身のとき
                        continue
                    }
                    openBoard(i + n, j + m, false)
                }
            }
        }

    }

}