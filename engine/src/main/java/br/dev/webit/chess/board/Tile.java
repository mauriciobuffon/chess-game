package br.dev.webit.chess.board;

import java.util.Optional;

public interface Tile {

    TileCoordinate getCoordinate();

    Optional<Piece> getPiece();

    default boolean isSecondRank() {
        if (getPiece().isEmpty()) {
            return false;
        }

        switch (getCoordinate().toString()) {
            case "a2":
            case "b2":
            case "c2":
            case "d2":
            case "e2":
            case "f2":
            case "g2":
            case "h2":
                return Alliance.WHITE.equals(getPiece().get().getAlliance());
            case "a7":
            case "b7":
            case "c7":
            case "d7":
            case "e7":
            case "f7":
            case "g7":
            case "h7":
                return Alliance.BLACK.equals(getPiece().get().getAlliance());
            default:
                return false;
        }
    }
}
