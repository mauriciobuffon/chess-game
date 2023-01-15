package br.dev.webit.chess.pieces;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import br.dev.webit.chess.board.Alliance;
import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.Piece;
import br.dev.webit.chess.board.Tile;
import br.dev.webit.chess.board.TileCoordinate;

public class King extends Piece {

    private static final int[][] POSSIBLE_MOVES = {
            { -1, -1 }, { -1, 0 }, { -1, +1 }, { 0, -1 },
            { 0, +1 }, { +1, -1 }, { +1, 0 }, { +1, +1 } };

    public King(Alliance pieceAlliance) {
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

                Optional<Piece> piece = board.getTile(candidate).getPiece();

                if (piece.isPresent()) {
                    if (!this.getAlliance().equals(piece.get().getAlliance())) {
                        legalMoves.add(new Move(this, origin, candidate));
                    }
                } else {
                    legalMoves.add(new Move(this, origin, candidate));
                }
            } catch (InvalidTileCoordinateException ex) {
            }
        }

        return Set.copyOf(legalMoves);
    }
}
