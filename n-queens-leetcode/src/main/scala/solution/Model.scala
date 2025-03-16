package solution

case class FromTopLeftPosition(x: Int, y: Int)

enum Piece {
  case Queen
}

type PieceEntry = (FromTopLeftPosition, Piece)

case class Board(val squares: Vector[Option[Piece]]) {
  lazy val pieces: Vector[PieceEntry] = {
    (0 until size * size).flatMap { i =>
      squares(i).map { piece =>
        val x = i % size
        val y = i / size
        (FromTopLeftPosition(x, y), piece)
      }
    }.toVector
  }

  lazy val toStr: List[String] = {
    (0 until size).map { y =>
      (0 until size).map { x =>
        squares(y * size + x)
          .map { case Piece.Queen =>
            "Q"
          }
          .getOrElse(".")
      }.mkString
    }.toList
  }

  lazy val hash = toStr.mkString

  lazy val size = math.sqrt(squares.size).toInt

  def at(position: FromTopLeftPosition) = {
    squares(position.y * size + position.x)
  }

  def set(pieceEntry: PieceEntry) = {
    val (position, piece) = pieceEntry
    Board(
      squares.updated(
        position.y * size + position.x,
        Some(piece)
      )
    )
  }
}

object Board {
  def empty(size: Int) = {
    val l = Vector.fill(size * size)(None)
    Board(l)
  }
}
