package br.dev.webit.chess.pieces;

import java.util.HashMap;
import java.util.Map;

import br.dev.webit.chess.board.Alliance;
import br.dev.webit.chess.board.BoardConfiguration;
import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.Piece;
import br.dev.webit.chess.board.TileCoordinate;

public class GameBoardConfiguration implements BoardConfiguration {

    @Override
    public Map<TileCoordinate, Piece> values() {
        Map<TileCoordinate, Piece> tiles = new HashMap<>();

        // TODO: left white rook
        tiles.put(TileCoordinate.A1, null);
        // left white knight
        tiles.put(TileCoordinate.B1, new Knight(Alliance.WHITE));
        // left white bishop
        tiles.put(TileCoordinate.C1, new Bishop(Alliance.WHITE));
        // TODO: white queen
        tiles.put(TileCoordinate.D1, null);
        // TODO: white king
        tiles.put(TileCoordinate.E1, null);
        // right white bishop
        tiles.put(TileCoordinate.F1, new Bishop(Alliance.WHITE));
        // right white knight
        tiles.put(TileCoordinate.G1, new Knight(Alliance.WHITE));
        // TODO: right white rook
        tiles.put(TileCoordinate.H1, null);

        try {
            // TODO: white pawns
            for (int i = 8; i < 16; i++) {
                tiles.put(TileCoordinate.of(i), null);
            }

            // TODO: black pawns
            for (int i = 48; i < 56; i++) {
                tiles.put(TileCoordinate.of(i), null);
            }
        } catch (InvalidTileCoordinateException ex) {
            throw new RuntimeException(ex);
        }

        // TODO: right black rook
        tiles.put(TileCoordinate.A8, null);
        // right black knight
        tiles.put(TileCoordinate.B8, new Knight(Alliance.BLACK));
        // right black bishop
        tiles.put(TileCoordinate.C8, new Bishop(Alliance.BLACK));
        // TODO: black queen
        tiles.put(TileCoordinate.D8, null);
        // TODO: black king
        tiles.put(TileCoordinate.E8, null);
        // left black bishop
        tiles.put(TileCoordinate.F8, new Bishop(Alliance.BLACK));
        // left black knight
        tiles.put(TileCoordinate.G8, new Knight(Alliance.BLACK));
        // TODO: left black rook
        tiles.put(TileCoordinate.H8, null);

        return tiles;
    }
}
