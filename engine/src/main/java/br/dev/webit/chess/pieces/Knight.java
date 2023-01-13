package br.dev.webit.chess.pieces;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.dev.webit.chess.board.Alliance;
import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.Piece;
import br.dev.webit.chess.board.Tile;
import br.dev.webit.chess.board.TileCoordinate;

public final class Knight extends Piece {

    /**
     * Candidates destination tiles are calculated adding an offset to the current
     * piece position.
     */
    private static final int[][] POSSIBLE_MOVES = {
            { -2, -1 }, { -2, +1 }, { -1, -2 }, { -1, +2 },
            { +1, -2 }, { +1, +2 }, { +2, -1 }, { +2, +1 } };

    public Knight(Alliance pieceAlliance) {
        super(pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        for (int[] offset : POSSIBLE_MOVES) {
            try {
                Tile tile = board.getTile(this);
                TileCoordinate origin = tile.getCoordinate();
                TileCoordinate candidate = origin.move(offset[0], offset[1]);
                board.getTile(candidate).getPiece().ifPresentOrElse(
                        piece -> {
                            if (!piece.getAlliance().equals(this.getAlliance())) {
                                legalMoves.add(new Move(this, origin, candidate));
                            }
                        }, () -> legalMoves.add(new Move(this, origin, candidate)));
            } catch (InvalidTileCoordinateException ex) {
            }
        }

        return Set.copyOf(legalMoves);
    }
}
