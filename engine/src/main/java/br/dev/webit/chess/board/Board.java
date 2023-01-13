package br.dev.webit.chess.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Board {

    private final Map<TileCoordinate, Tile> tiles;
    private final Map<Piece, Tile> pieces;

    public Board() {
        Map<TileCoordinate, Tile> array = new HashMap<>(128);

        try {
            for (int i = 0; i < 64; i++) {
                TileCoordinate coordinate = TileCoordinate.of(i);
                Tile tile = new TileImpl(coordinate, null);
                array.put(coordinate, tile);
            }
        } catch (InvalidTileCoordinateException ex) {
            throw new RuntimeException(ex);
        }

        tiles = Map.copyOf(array);
        pieces = new HashMap<>(64);
    }

    public Board(BoardConfiguration config) {
        this();
        Objects.requireNonNull(config);

        for (Entry<TileCoordinate, Piece> entry : config.values().entrySet()) {
            Piece piece = entry.getValue();
            if (null != piece) {
                TileImpl tile = (TileImpl) getTile(entry.getKey());
                tile.setPiece(piece);
                pieces.put(piece, tile);
            }
        }
    }

    public Tile getTile(TileCoordinate coordinate) {
        return tiles.get(Objects.requireNonNull(coordinate));
    }

    public Tile getTile(Piece piece) {
        return pieces.get(Objects.requireNonNull(piece));
    }

    public void executeMove(Move move) {
        // TODO: work in progress
        Piece piece = move.getPiece();
        TileCoordinate origin = move.getOrigin();
        TileCoordinate destination = move.getDestination();

        // tiles.put(origin, Tile.create(origin, null));
        // tiles.put(destination, Tile.create(destination, piece.move(destination)));
    }
}
