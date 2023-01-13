package br.dev.webit.chess.board;

import java.util.Objects;

// TODO: maybe an interface in the future
public class Move {

    private final Piece piece;
    private final TileCoordinate origin;
    private final TileCoordinate destination;

    public Move(Piece movingPiece, TileCoordinate origin, TileCoordinate destination) {
        this.piece = Objects.requireNonNull(movingPiece);
        this.origin = Objects.requireNonNull(origin);
        this.destination = Objects.requireNonNull(destination);
    }

    public Piece getPiece() {
        return piece;
    }

    public TileCoordinate getOrigin() {
        return origin;
    }

    public TileCoordinate getDestination() {
        return destination;
    }
}
