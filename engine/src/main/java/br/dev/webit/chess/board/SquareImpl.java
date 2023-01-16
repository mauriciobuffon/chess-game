package br.dev.webit.chess.board;

import java.util.Objects;
import java.util.Optional;

class SquareImpl implements Square {

    private final TileCoordinate coordinate;
    private Piece piece;

    SquareImpl(TileCoordinate tileCoordinate, Piece pieceOnTile) {
        this.coordinate = Objects.requireNonNull(tileCoordinate);
        this.piece = pieceOnTile;
    }

    @Override
    public TileCoordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public Optional<Piece> getPiece() {
        return Optional.ofNullable(piece);
    }

    // TODO: work in progress
    // throw an exception when the current piece has the same color
    void setPiece(Piece aPiece) {
        this.piece = aPiece;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.coordinate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SquareImpl other = (SquareImpl) obj;
        return Objects.equals(this.coordinate, other.coordinate);
    }
}
