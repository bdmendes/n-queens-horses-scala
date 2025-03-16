package example

import solution._

class SolutionSpec extends munit.FunSuite {
  test("Board.pieces") {
    val board =
      Board
        .empty(4)
        .set(FromTopLeftPosition(0, 0), Piece.Queen)
        .set(FromTopLeftPosition(1, 2), Piece.Queen)

    assertEquals(
      board.pieces,
      Vector(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(1, 2) -> Piece.Queen
      )
    )
  }
}
