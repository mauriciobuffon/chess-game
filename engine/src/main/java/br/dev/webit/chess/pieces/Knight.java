package br.dev.webit.chess.pieces;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.Color;
import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.Piece;
import br.dev.webit.chess.board.Square;
import br.dev.webit.chess.board.TileCoordinate;

public final class Knight extends Piece {

    private static final int[][] POSSIBLE_MOVES = {
            { -2, -1 }, { -2, +1 }, { -1, -2 }, { -1, +2 },
            { +1, -2 }, { +1, +2 }, { +2, -1 }, { +2, +1 } };

    public Knight(Color pieceColor) {
        super(pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final Square tile = board.getTile(this);
        final TileCoordinate origin = tile.getCoordinate();

        for (int[] offset : POSSIBLE_MOVES) {
            try {
                TileCoordinate destination = origin.move(offset[0], offset[1]);
                board.getTile(destination).getPiece().ifPresentOrElse(
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
