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

public final class Bishop extends Piece {

    private static final int[] OFFSETS = { -17, -15, +15, +17 };

    public Bishop(Color pieceColor) {
        super(pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final TileCoordinate origin = board.getCoordinate(this);

        for (int offset : OFFSETS) {
            for (int i = 1; i < 8; i++) {
                try {
                    TileCoordinate destination = origin.move(offset * i);
                    Optional<Piece> piece = board.getSquare(destination).getPiece();

                    if (piece.isPresent()) {
                        if (!this.getColor().equals(piece.get().getColor())) {
                            legalMoves.add(new Move(this, destination));
                        }
                        break;
                    } else {
                        legalMoves.add(new Move(this, destination));
                    }
                } catch (InvalidTileCoordinateException ex) {
                    break;
                }
            }
        }

        return Set.copyOf(legalMoves);
    }
}
