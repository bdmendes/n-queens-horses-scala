package solution

def solveNQueens(n: Int): List[List[String]] = {
  Search.search(Board.empty(n), Piece.Queen, n).map(_.toStr)
}

object Solution extends App {
  val board =
    Board
      .empty(4)
      .set(FromTopLeftPosition(0, 0), Piece.Queen)
      .set(FromTopLeftPosition(1, 2), Piece.Queen)
  println(board.toStr)
  println(board.pieces)
  println(board.isValid)

  val result = solveNQueens(4)
  println(result)
}
