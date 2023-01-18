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
import br.dev.webit.chess.board.TileCoordinate;

public class Pawn extends Piece {

    public Pawn(Color pieceColor) {
        super(pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final int direction = Color.BLACK.equals(this.getColor()) ? -1 : 1;

        final TileCoordinate origin = board.getCoordinate(this);
        TileCoordinate destination;
        Optional<Piece> piece;

        try {
            destination = origin.move(16 * direction);
            piece = board.getSquare(destination).getPiece();

            if (piece.isEmpty()) {
                legalMoves.add(new Move(this, destination));

                if ((direction == 1 ? origin.getRank() : 9 - origin.getRank()) == 2) {
                    destination = origin.move(32 * direction);
                    piece = board.getSquare(destination).getPiece();

                    if (piece.isEmpty()) {
                        legalMoves.add(new Move(this, destination));
                    }
                }
            }

            // possible capturing moves
            for (int offset : new int[] { 15, 17 }) {
                destination = origin.move(offset * direction);
                piece = board.getSquare(destination).getPiece();

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
