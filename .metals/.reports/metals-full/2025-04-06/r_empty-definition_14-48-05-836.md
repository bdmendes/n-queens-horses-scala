error id: `<none>`.
file://<WORKSPACE>/n-queen-horses/src/test/scala/example/SolutionSpec.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -solution.
	 -solution#
	 -solution().
	 -.
	 -#
	 -().
	 -scala/Predef.
	 -scala/Predef#
	 -scala/Predef().
offset: 214
uri: file://<WORKSPACE>/n-queen-horses/src/test/scala/example/SolutionSpec.scala
text:
```scala
package example

import solution._

class SolutionSpec extends munit.FunSuite {
  test("Board.pieces") {
    val board =
      Board
        .empty(4)
        .set(FromTopLeftPosition(0, 0), Piece.Queen)
        .s@@et(FromTopLeftPosition(1, 2), Piece.Queen)
        .set(FromTopLeftPosition(2, 3), Piece.Queen)

    assertEquals(
      board.pieces,
      Vector(
        FromTopLeftPosition(0, 0) -> Piece.Queen,
        FromTopLeftPosition(1, 2) -> Piece.Queen
      )
    )
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.