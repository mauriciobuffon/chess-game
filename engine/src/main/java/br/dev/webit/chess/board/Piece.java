package br.dev.webit.chess.board;

import java.util.Collection;
import java.util.Objects;

public abstract class Piece {

    private final Alliance alliance;

    protected Piece(Alliance pieceAlliance) {
        this.alliance = Objects.requireNonNull(pieceAlliance);
    }

    public abstract Collection<Move> calculateLegalMoves(Board board);

    public final Alliance getAlliance() {
        return alliance;
    }
}
