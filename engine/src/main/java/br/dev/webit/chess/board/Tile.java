package br.dev.webit.chess.board;

import java.util.Optional;

public interface Tile {

    TileCoordinate getCoordinate();

    Optional<Piece> getPiece();
}
