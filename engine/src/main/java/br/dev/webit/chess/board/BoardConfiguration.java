package br.dev.webit.chess.board;

import java.util.Map;

public interface BoardConfiguration {

    Map<TileCoordinate, Piece> values();
}
