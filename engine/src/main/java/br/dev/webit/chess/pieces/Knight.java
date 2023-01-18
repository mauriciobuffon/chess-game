package br.dev.webit.chess.pieces;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.Color;
import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.Piece;
import br.dev.webit.chess.board.TileCoordinate;

public final class Knight extends Piece {

    private static final int[] OFFSETS = { -33, -31, -18, -14, +14, +18, +31, +33 };

    public Knight(Color pieceColor) {
        super(pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final TileCoordinate origin = board.getCoordinate(this);

        for (int offset : OFFSETS) {
            try {
                TileCoordinate destination = origin.move(offset);

                board.getSquare(destination).getPiece().ifPresentOrElse(
                        piece -> {
                            if (!piece.getColor().equals(this.getColor())) {
                                legalMoves.add(new Move(this, destination));
                            }
                        }, () -> legalMoves.add(new Move(this, destination)));
            } catch (InvalidTileCoordinateException ex) {
            }
        }

        return Set.copyOf(legalMoves);
    }
}
