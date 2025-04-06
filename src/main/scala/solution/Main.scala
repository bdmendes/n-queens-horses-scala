package solution

object Main extends App {
  private def solveNQueens(n: Int): List[List[String]] = {
    Search.search(Board.empty(n), Piece.Queen, n).map(_.toStr)
  }

  println(solveNQueens(4))
}
