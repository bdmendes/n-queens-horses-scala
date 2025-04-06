package solution

def attacks(entry1: PieceEntry, entry2: PieceEntry): Boolean = ???

extension (board: Board) {
  def isSafe(toPlace: PieceEntry): Boolean = ???
}

object Search {
  def search(board: Board, put: Piece, many: Int): List[Board] = ???
}
