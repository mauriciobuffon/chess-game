package br.dev.webit.chess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Board {

    private final Map<TileCoordinate, Square> boardcard;
    private final Map<Piece, Square> pieces;
    private final List<Move> moves;

    public Board() {
        Map<TileCoordinate, Square> array = new HashMap<>(TileCoordinate.LENGTH * 2);

        try {
            for (int i = 0; i < TileCoordinate.LENGTH; i++) {
                if ((i & TileCoordinate.MASK) == 0) {
                    TileCoordinate coordinate = TileCoordinate.of(i);
                    Square square = new SquareImpl(coordinate, null);
                    array.put(coordinate, square);
                }
            }
        } catch (InvalidTileCoordinateException ex) {
            throw new RuntimeException(ex);
        }

        boardcard = Map.copyOf(array);
        pieces = new HashMap<>(TileCoordinate.LENGTH);
        moves = new ArrayList<>();
    }

    public Board(BoardConfiguration config) {
        this();
        Objects.requireNonNull(config);

        for (Entry<TileCoordinate, Piece> entry : config.values().entrySet()) {
            Piece piece = entry.getValue();
            if (null != piece) {
                SquareImpl square = (SquareImpl) getSquare(entry.getKey());
                square.setPiece(piece);
                pieces.put(piece, square);
            }
        }
    }

    public Square getSquare(TileCoordinate coordinate) {
        return boardcard.get(Objects.requireNonNull(coordinate));
    }

    public TileCoordinate getCoordinate(Piece piece) {
        return pieces.get(Objects.requireNonNull(piece)).getCoordinate();
    }

    public void executeMove(Move move) {
        // TODO: work in progress
        final Piece piece = move.getPiece();
        final TileCoordinate origin = getCoordinate(piece);
        final TileCoordinate destination = move.getDestination();

        // tiles.put(origin, Tile.create(origin, null));
        // tiles.put(destination, Tile.create(destination, piece.move(destination)));
        moves.add(move);
    }
}
