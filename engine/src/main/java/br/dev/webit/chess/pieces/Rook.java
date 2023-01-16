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

public class Rook extends Piece {

    private static final int[][] POSSIBLE_MOVES = { { -1, 0 }, { 0, -1 }, { +1, 0 }, { 0, +1 } };

    public Rook(Color pieceColor) {
        super(pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final Square tile = board.getTile(this);
        final TileCoordinate origin = tile.getCoordinate();

        for (int[] offset : POSSIBLE_MOVES) {
            for (int i = 1; i < 8; i++) {
                try {
                    TileCoordinate desination = origin.move(offset[0] * i, offset[1] * i);
                    Optional<Piece> piece = board.getTile(desination).getPiece();

                    if (piece.isPresent()) {
                        if (!this.getColor().equals(piece.get().getColor())) {
                            legalMoves.add(new Move(this, desination));
                        }
                        break;
                    } else {
                        legalMoves.add(new Move(this, desination));
                    }
                } catch (InvalidTileCoordinateException ex) {
                    break;
                }
            }
        }

        return Set.copyOf(legalMoves);
    }
}
