package minesweeper.game

class Board(private val bombNum: Int, private val boardSize: Int) {
    /**
     * マインスイーパーのマス目を表現する二次元配列
     */
    val board = Array(boardSize) { Array(boardSize) { Element() } }

    /**
     * ユーザーが一回もマス目を開けていないかどうか
     */
    var firstTime = true

    /**
     * ボードを初期化する関数
     *
     * @param i ユーザーが選択した行数
     * @param j ユーザーが選択した列数
     */
    fun initial(i: Int, j: Int) {
        setBomb(i, j)
        countAroundBomb()
    }

    /**
     * 爆弾を配置するメソッド
     *
     * @param x ユーザーが選択したマス目の行番号
     * @param y ユーザーが選択したマス目の列番号
     */
    private fun setBomb(x: Int, y: Int) {
        val bombPosition = MutableList(boardSize * boardSize) { it }
        bombPosition.shuffle()
        var i = 0

        while (i < bombNum) {
            val position = Pair(bombPosition[i] / boardSize, bombPosition[i] % boardSize)

            // ユーザが選択した場所に爆弾が配置されてしまった場合
            if (x == position.first && y == position.second) {
                continue
            }

            board[position.first][position.second].bomb = true
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

                        if (temp?.bomb == true) {
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
     * @return ゲームオーバーではないときtrue
     */
    fun openBoard(i: Int, j: Int, user: Boolean): Boolean {
        // 範囲外のとき
        if (board.getOrNull(i)?.getOrNull(j) == null) {
            return true
        }

        // 既に開けられているところを開けた場合
        if (!board[i][j].hiddenFlag) {
            return true
        }

        // ユーザーが開けたとき
        if (user) {
            if (board[i][j].bomb) {
                board[i][j].hiddenFlag = false
                board[i][j].openElement()
                return false
            }
        }

        if (board[i][j].flag) {
            return true
        }

        if (!board[i][j].bomb) {
            board[i][j].hiddenFlag = false
            board[i][j].openElement()
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

        return true
    }

}