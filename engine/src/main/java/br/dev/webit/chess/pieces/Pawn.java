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

public class Pawn extends Piece {

    public Pawn(Alliance pieceAlliance) {
        super(pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMoves = new HashSet<>();

        final int direction = Alliance.BLACK.equals(this.getAlliance()) ? -1 : 1;

        final Tile tile = board.getTile(this);
        final TileCoordinate origin = tile.getCoordinate();
        TileCoordinate candidate;
        Optional<Piece> piece;

        try {
            candidate = origin.move(0, 1 * direction);
            piece = board.getTile(candidate).getPiece();

            if (piece.isEmpty()) {
                legalMoves.add(new Move(this, origin, candidate));

                if (tile.isSecondRank()) {
                    candidate = origin.move(0, 2 * direction);
                    piece = board.getTile(candidate).getPiece();

                    if (piece.isEmpty()) {
                        legalMoves.add(new Move(this, origin, candidate));
                    }
                }
            }

            // possible capturing moves
            for (int[] offset : new int[][] { { -1, +1 }, { +1, +1 } }) {
                candidate = origin.move(offset[0], offset[1] * direction);
                piece = board.getTile(candidate).getPiece();

                if (piece.isPresent() && !this.getAlliance().equals(piece.get().getAlliance())) {
                    legalMoves.add(new Move(this, origin, candidate));
                }
            }

            // TODO: implement the en passant logic
        } catch (InvalidTileCoordinateException ex) {
        }

        return Set.copyOf(legalMoves);
    }
}
