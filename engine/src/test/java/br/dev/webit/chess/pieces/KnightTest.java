package br.dev.webit.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.dev.webit.chess.board.Alliance;
import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.BoardConfiguration;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.TileCoordinate;

public class KnightTest {

    @ParameterizedTest
    @ValueSource(ints = { 0, 7, 56, 63 })
    public void cornerMovements(int coordinate) {
        TileCoordinate tileCoordinate = assertDoesNotThrow(() -> TileCoordinate.of(coordinate));
        Knight knight = assertDoesNotThrow(() -> new Knight(Alliance.WHITE));
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, knight));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> knight.calculateLegalMoves(board));
        assertTrue(moves.size() == 2);
    }
}
