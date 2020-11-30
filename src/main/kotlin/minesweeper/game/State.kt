package minesweeper.game

/**
 * マス目の状態を表すEnum
 *
 * @property FLAG 旗が立っている状態
 * @property BOMB 爆弾がある状態
 * @property EMPTY 何もない状態
 */
enum class State {
    FLAG, BOMB, EMPTY
}