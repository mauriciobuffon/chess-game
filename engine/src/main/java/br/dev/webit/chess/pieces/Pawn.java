package br.dev.webit.chess.pieces;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.Color;
import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.Piece;
import br.dev.webit.chess.board.Square;
import br.dev.webit.chess.board.TileCoordinate;

public class Pawn extends Piece {

    public Pawn(Color pieceColor) {
        super(pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final int direction = Color.BLACK.equals(this.getColor()) ? -1 : 1;

        final Square tile = board.getTile(this);
        final TileCoordinate origin = tile.getCoordinate();
        TileCoordinate destination;
        Optional<Piece> piece;

        try {
            destination = origin.move(0, 1 * direction);
            piece = board.getTile(destination).getPiece();

            if (piece.isEmpty()) {
                legalMoves.add(new Move(this, destination));

                if (tile.isSecondRank()) {
                    destination = origin.move(0, 2 * direction);
                    piece = board.getTile(destination).getPiece();

                    if (piece.isEmpty()) {
                        legalMoves.add(new Move(this, destination));
                    }
                }
            }

            // possible capturing moves
            for (int[] offset : new int[][] { { -1, +1 }, { +1, +1 } }) {
                destination = origin.move(offset[0], offset[1] * direction);
                piece = board.getTile(destination).getPiece();

                if (piece.isPresent() && !this.getColor().equals(piece.get().getColor())) {
                    legalMoves.add(new Move(this, destination));
                }
            }

            // TODO: implement the en passant logic
        } catch (InvalidTileCoordinateException ex) {
        }

        return Set.copyOf(legalMoves);
    }
}
