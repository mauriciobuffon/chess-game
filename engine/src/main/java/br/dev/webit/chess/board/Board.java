package br.dev.webit.chess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Board {

    private final Map<TileCoordinate, Square> tiles;
    private final Map<Piece, Square> pieces;
    private final List<Move> moves;

    public Board() {
        Map<TileCoordinate, Square> array = new HashMap<>(128);

        try {
            for (int i = 0; i < 64; i++) {
                TileCoordinate coordinate = TileCoordinate.of(i);
                Square tile = new SquareImpl(coordinate, null);
                array.put(coordinate, tile);
            }
        } catch (InvalidTileCoordinateException ex) {
            throw new RuntimeException(ex);
        }

        tiles = Map.copyOf(array);
        pieces = new HashMap<>(64);
        moves = new ArrayList<>();
    }

    public Board(BoardConfiguration config) {
        this();
        Objects.requireNonNull(config);

        for (Entry<TileCoordinate, Piece> entry : config.values().entrySet()) {
            Piece piece = entry.getValue();
            if (null != piece) {
                SquareImpl tile = (SquareImpl) getTile(entry.getKey());
                tile.setPiece(piece);
                pieces.put(piece, tile);
            }
        }
    }

    public Square getTile(TileCoordinate coordinate) {
        return tiles.get(Objects.requireNonNull(coordinate));
    }

    public Square getTile(Piece piece) {
        return pieces.get(Objects.requireNonNull(piece));
    }

    public void executeMove(Move move) {
        // TODO: work in progress
        final Piece piece = move.getPiece();
        final TileCoordinate origin = getTile(piece).getCoordinate();
        final TileCoordinate destination = move.getDestination();

        // tiles.put(origin, Tile.create(origin, null));
        // tiles.put(destination, Tile.create(destination, piece.move(destination)));

        moves.add(move);
    }
}
