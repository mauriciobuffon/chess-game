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

public class King extends Piece {

    private static final int[] OFFSETS = { -17, -16, -15, -1, +1, +15, +16, +17 };

    public King(Color pieceColor) {
        super(pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final TileCoordinate origin = board.getCoordinate(this);

        for (int offset : OFFSETS) {
            try {
                TileCoordinate destination = origin.move(offset);
                Optional<Piece> piece = board.getSquare(destination).getPiece();

                if (piece.isPresent()) {
                    if (!this.getColor().equals(piece.get().getColor())) {
                        legalMoves.add(new Move(this, destination));
                    }
                } else {
                    legalMoves.add(new Move(this, destination));
                }
            } catch (InvalidTileCoordinateException ex) {
            }
        }

        return Set.copyOf(legalMoves);
    }
}
