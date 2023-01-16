package br.dev.webit.chess.board;

import java.util.Objects;

public class Move {

    private final Piece piece;
    private final TileCoordinate destination;

    public Move(Piece movingPiece, TileCoordinate destination) {
        this.piece = Objects.requireNonNull(movingPiece);
        this.destination = Objects.requireNonNull(destination);
    }

    public Piece getPiece() {
        return piece;
    }

    public TileCoordinate getDestination() {
        return destination;
    }
}
