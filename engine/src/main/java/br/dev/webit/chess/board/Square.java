package br.dev.webit.chess.board;

import java.util.Optional;

public interface Square {

    TileCoordinate getCoordinate();

    Optional<Piece> getPiece();
}
