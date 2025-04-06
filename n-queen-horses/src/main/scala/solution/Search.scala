package solution

import scala.collection.mutable
import solution.Board.empty

def attacks(entry1: PieceEntry, entry2: PieceEntry) = {
  val (pos1, piece1) = entry1
  val (pos2, piece2) = entry2
  (piece1, piece2) match {
    case (Piece.Queen, _) =>
      pos1.x == pos2.x || pos1.y == pos2.y || (pos1.x - pos2.x).abs == (pos1.y - pos2.y).abs
    case (Piece.Knight, _) =>
      (pos1.x - pos2.x).abs == 1 && (pos1.y - pos2.y).abs == 2 ||
      (pos1.x - pos2.x).abs == 2 && (pos1.y - pos2.y).abs == 1
  }
}

extension (board: Board) {
  def isValid = {
    val pieces = board.pieces
    pieces.forall { entry =>
      pieces.forall { other =>
        entry == other || !attacks(entry, other)
      }
    }
  }

  def isSafe(toPlace: PieceEntry) = {
    val pieces = board.pieces
    pieces.forall { entry =>
      entry == toPlace || (!attacks(entry, toPlace) && !attacks(toPlace, entry))
    }
  }
}

object Search {
  private def go(
      board: Board,
      put: Piece,
      many: Int,
      possiblePositions: Seq[FromTopLeftPosition],
      seen: mutable.HashSet[Vector[PieceEntry]],
      ply: Int
  ): Seq[Board] = {
    if (seen.contains(board.pieces)) {
      Seq.empty
    } else if (many == 0) {
      seen += board.pieces
      Seq(board)
    } else {
      if (ply > 1) {
        seen += board.pieces
      }

      val emptyPositions = possiblePositions
        .filter { position =>
          !board.at(position).isDefined && board.isSafe((position, put))
        }

      emptyPositions.flatMap { position =>
        val newBoard = board.set((position, put))
        go(newBoard, put, many - 1, possiblePositions, seen, ply + 1)
      }
    }
  }

  def search(
      board: Board,
      put: Piece,
      many: Int
  ): List[Board] = {
    val possiblePositions = (0 until board.size)
      .flatMap { y =>
        (0 until board.size).map { x =>
          FromTopLeftPosition(x, y)
        }
      }
    go(
      board,
      put,
      many,
      possiblePositions,
      mutable.HashSet.empty,
      0
    ).toList
  }
}
