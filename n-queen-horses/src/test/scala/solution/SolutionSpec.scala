package solution

import solution.Piece.{Knight, Queen}

class SolutionSpec extends munit.FunSuite {
  test("Board.set") {
    val board =
      Board
        .empty(4)
        .set(FromTopLeftPosition(0, 0), Piece.Queen)
        .set(FromTopLeftPosition(1, 2), Piece.Queen)
        .set(FromTopLeftPosition(2, 3), Piece.Knight)

    assertEquals(
      board.pieces,
      Vector(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(1, 2) -> Piece.Queen,
        FromTopLeftPosition(2, 3) -> Piece.Knight
      )
    )
  }

  test("attacks - queen") {
    // Queen in a diagonal.
    assert(
      attacks(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(1, 1) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(1, 1) -> Piece.Queen,
        FromTopLeftPosition(0, 0) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(3, 3) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(3, 3) -> Piece.Queen,
        FromTopLeftPosition(0, 0) -> Piece.Knight
      )
    )

    // Queen in a row.
    assert(
      attacks(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(1, 0) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(1, 0) -> Piece.Queen,
        FromTopLeftPosition(0, 0) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(3, 0) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(3, 0) -> Piece.Queen,
        FromTopLeftPosition(0, 0) -> Piece.Queen
      )
    )

    // Queen in a file.
    assert(
      attacks(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(0, 1) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(0, 1) -> Piece.Queen,
        FromTopLeftPosition(0, 0) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(0, 3) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(0, 3) -> Piece.Queen,
        FromTopLeftPosition(0, 0) -> Piece.Queen
      )
    )
  }

  test("attacks - knight") {
    // Knight in the center.
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(3, 0) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(3, 4) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(1, 0) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(1, 4) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(4, 1) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(4, 3) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(0, 1) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(2, 2) -> Piece.Knight,
        FromTopLeftPosition(0, 3) -> Piece.Knight
      )
    )

    // Knight in the corner.
    assert(
      attacks(
        FromTopLeftPosition(1, 0) -> Piece.Knight,
        FromTopLeftPosition(0, 2) -> Piece.Queen
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(1, 0) -> Piece.Knight,
        FromTopLeftPosition(2, 2) -> Piece.Knight
      )
    )
    assert(
      attacks(
        FromTopLeftPosition(1, 0) -> Piece.Knight,
        FromTopLeftPosition(3, 1) -> Piece.Queen
      )
    )

    // Some random squares that should not pass.
    assert(
      !attacks(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(3, 4) -> Piece.Queen
      )
    )
    assert(
      !attacks(
        FromTopLeftPosition(1, 1) -> Piece.Queen,
        FromTopLeftPosition(2, 3) -> Piece.Knight
      )
    )
    assert(
      !attacks(
        FromTopLeftPosition(0, 0) -> Piece.Knight,
        FromTopLeftPosition(3, 3) -> Piece.Queen
      )
    )
    assert(
      !attacks(
        FromTopLeftPosition(3, 3) -> Piece.Knight,
        FromTopLeftPosition(7, 1) -> Piece.Knight
      )
    )
    assert(
      !attacks(
        FromTopLeftPosition(3, 3) -> Piece.Queen,
        FromTopLeftPosition(0, 2) -> Piece.Knight
      )
    )
  }

  test("Board.isSafe - only queens") {
    val board = Board
      .empty(5)
      .set(FromTopLeftPosition(2, 2) -> Piece.Queen)
      .set(FromTopLeftPosition(0, 1) -> Piece.Queen)

    // Same diagonal.
    assert(!board.isSafe(FromTopLeftPosition(0, 0) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(3, 3) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(3, 3) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(1, 0) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(3, 4) -> Piece.Queen))

    // Same row.
    assert(!board.isSafe(FromTopLeftPosition(4, 1) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(0, 2) -> Piece.Queen))

    // Same file.
    assert(!board.isSafe(FromTopLeftPosition(4, 1) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(0, 2) -> Piece.Queen))

    // Knight jumps.
    assert(board.isSafe(FromTopLeftPosition(1, 4) -> Piece.Queen))
    assert(board.isSafe(FromTopLeftPosition(4, 3) -> Piece.Queen))
    assert(board.isSafe(FromTopLeftPosition(3, 0) -> Piece.Queen))
  }

  test("Board.isSafe - knights against queens") {
    val board = Board
      .empty(5)
      .set(FromTopLeftPosition(1, 1) -> Piece.Queen)
      .set(FromTopLeftPosition(0, 3) -> Piece.Queen)

    // Safe spots.
    assert(board.isSafe(FromTopLeftPosition(4, 0) -> Piece.Knight))
    assert(board.isSafe(FromTopLeftPosition(4, 2) -> Piece.Knight))
    assert(board.isSafe(FromTopLeftPosition(3, 4) -> Piece.Knight))

    // Everywhere else is dangerous.
    assert(!board.isSafe(FromTopLeftPosition(0, 0) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(4, 3) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(3, 3) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(4, 1) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(2, 4) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(4, 4) -> Piece.Knight))
  }

  test("Board.isSafe - mixed") {
    val board = Board
      .empty(5)
      .set(FromTopLeftPosition(3, 0) -> Piece.Knight)
      .set(FromTopLeftPosition(2, 3) -> Piece.Queen)

    // Safe spots.
    assert(board.isSafe(FromTopLeftPosition(0, 0) -> Piece.Knight))
    assert(board.isSafe(FromTopLeftPosition(1, 0) -> Piece.Knight))
    assert(board.isSafe(FromTopLeftPosition(4, 0) -> Piece.Knight))
    assert(board.isSafe(FromTopLeftPosition(0, 4) -> Piece.Queen))
    assert(board.isSafe(FromTopLeftPosition(4, 4) -> Piece.Queen))
    assert(board.isSafe(FromTopLeftPosition(0, 2) -> Piece.Queen))

    // Unsafe spots.
    assert(!board.isSafe(FromTopLeftPosition(1, 1) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(2, 0) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(4, 3) -> Piece.Knight))
    assert(!board.isSafe(FromTopLeftPosition(4, 2) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(2, 1) -> Piece.Queen))
    assert(!board.isSafe(FromTopLeftPosition(2, 1) -> Piece.Queen))
  }

  test("search - leetcode nqueens") {
    val board = Board.empty(4)
    val results = Search.search(board, Queen, 4)
    val expected = List(
      List(".Q..", "...Q", "Q...", "..Q."),
      List("..Q.", "Q...", "...Q", ".Q..")
    )
    assertEquals(results.map(_.toStr), expected)
  }

  test("search - horseys") {
    val board = Board
      .empty(4)
      .set(FromTopLeftPosition(0, 2) -> Piece.Queen)
    val results = Search.search(board, Knight, 2)
    val expected = List(
      List("...K", "...K", "Q...", "...."),
      List("...K", "....", "Q...", "...K"),
      List("....", "...K", "Q...", "...K")
    )
    assertEquals(results.map(_.toStr), expected)
  }
}
