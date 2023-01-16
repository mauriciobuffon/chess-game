package br.dev.webit.chess.pieces;

import java.util.HashMap;
import java.util.Map;

import br.dev.webit.chess.board.Color;
import br.dev.webit.chess.board.BoardConfiguration;
import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.Piece;
import br.dev.webit.chess.board.TileCoordinate;

public class GameBoardConfiguration implements BoardConfiguration {

    @Override
    public Map<TileCoordinate, Piece> values() {
        Map<TileCoordinate, Piece> tiles = new HashMap<>();

        // left white rook
        tiles.put(TileCoordinate.A1, new Rook(Color.WHITE));
        // left white knight
        tiles.put(TileCoordinate.B1, new Knight(Color.WHITE));
        // left white bishop
        tiles.put(TileCoordinate.C1, new Bishop(Color.WHITE));
        // white queen
        tiles.put(TileCoordinate.D1, new Queen(Color.WHITE));
        // white king
        tiles.put(TileCoordinate.E1, new King(Color.WHITE));
        // right white bishop
        tiles.put(TileCoordinate.F1, new Bishop(Color.WHITE));
        // right white knight
        tiles.put(TileCoordinate.G1, new Knight(Color.WHITE));
        // right white rook
        tiles.put(TileCoordinate.H1, new Rook(Color.WHITE));

        try {
            // white pawns
            for (int i = 8; i < 16; i++) {
                tiles.put(TileCoordinate.of(i), new Pawn(Color.WHITE));
            }

            // black pawns
            for (int i = 48; i < 56; i++) {
                tiles.put(TileCoordinate.of(i), new Pawn(Color.BLACK));
            }
        } catch (InvalidTileCoordinateException ex) {
            throw new RuntimeException(ex);
        }

        // right black rook
        tiles.put(TileCoordinate.A8, new Rook(Color.BLACK));
        // right black knight
        tiles.put(TileCoordinate.B8, new Knight(Color.BLACK));
        // right black bishop
        tiles.put(TileCoordinate.C8, new Bishop(Color.BLACK));
        // black queen
        tiles.put(TileCoordinate.D8, new Queen(Color.BLACK));
        // black king
        tiles.put(TileCoordinate.E8, new King(Color.BLACK));
        // left black bishop
        tiles.put(TileCoordinate.F8, new Bishop(Color.BLACK));
        // left black knight
        tiles.put(TileCoordinate.G8, new Knight(Color.BLACK));
        // left black rook
        tiles.put(TileCoordinate.H8, new Rook(Color.BLACK));

        return tiles;
    }
}
