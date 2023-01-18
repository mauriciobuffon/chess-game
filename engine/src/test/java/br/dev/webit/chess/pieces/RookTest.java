package br.dev.webit.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.MethodSource;

import br.dev.webit.chess.board.Color;
import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.BoardConfiguration;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.TileCoordinate;

public class RookTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The rook has 14 legal moves on the tile coordinate {0}")
    @MethodSource("provideAllCoordinates")
    public void numberOfLegalMovesPerTile(@ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate) {
        Rook rook = assertDoesNotThrow(() -> new Rook(Color.WHITE));
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, rook));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> rook.calculateLegalMoves(board));
        assertEquals(14, moves.size());
    }

    static IntStream provideAllCoordinates() {
        return IntStream.range(0, TileCoordinate.LENGTH).filter(n -> (n & TileCoordinate.MASK) == 0);
    }
}
