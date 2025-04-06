package solution

case class FromTopLeftPosition(x: Int, y: Int)

enum Piece {
  case Queen
  case Knight
}

type PieceEntry = (FromTopLeftPosition, Piece)

case class Board(squares: Vector[Option[Piece]]) {
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
          .map {
            case Piece.Queen  => "Q"
            case Piece.Knight => "K"
          }
          .getOrElse(".")
      }.mkString
    }.toList
  }

  lazy val hash: String = toStr.mkString

  lazy val size: Int = math.sqrt(squares.size).toInt

  def at(position: FromTopLeftPosition): Option[Piece] = {
    squares(position.y * size + position.x)
  }

  def set(pieceEntry: PieceEntry): Board = ???
}

object Board {
  def empty(size: Int): Board = {
    val l = Vector.fill(size * size)(None)
    Board(l)
  }
}
