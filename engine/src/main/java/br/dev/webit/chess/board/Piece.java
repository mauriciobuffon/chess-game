package br.dev.webit.chess.board;

import java.util.Collection;
import java.util.Objects;

public abstract class Piece {

    private final Color color;

    protected Piece(Color pieceColor) {
        this.color = Objects.requireNonNull(pieceColor);
    }

    public abstract Collection<Move> calculateLegalMoves(Board board);

    public final Color getColor() {
        return color;
    }
}
