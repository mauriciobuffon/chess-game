package br.dev.webit.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import br.dev.webit.chess.board.Alliance;
import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.BoardConfiguration;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.TileCoordinate;

public class PawnTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The {1} pawn has {2} legal moves on the tile coordinate {0}")
    @CsvSource({ "8, BLACK, 1", "8, WHITE, 2", "9, BLACK, 1", "9, WHITE, 2", "10, BLACK, 1", "10, WHITE, 2",
            "11, BLACK, 1", "11, WHITE, 2", "12, BLACK, 1", "12, WHITE, 2", "13, BLACK, 1", "13, WHITE, 2",
            "14, BLACK, 1", "14, WHITE, 2", "15, BLACK, 1", "15, WHITE, 2", "16, BLACK, 1", "16, WHITE, 1",
            "17, BLACK, 1", "17, WHITE, 1", "18, BLACK, 1", "18, WHITE, 1", "19, BLACK, 1", "19, WHITE, 1",
            "20, BLACK, 1", "20, WHITE, 1", "21, BLACK, 1", "21, WHITE, 1", "22, BLACK, 1", "22, WHITE, 1",
            "23, BLACK, 1", "23, WHITE, 1", "24, BLACK, 1", "24, WHITE, 1", "25, BLACK, 1", "25, WHITE, 1",
            "26, BLACK, 1", "26, WHITE, 1", "27, BLACK, 1", "27, WHITE, 1", "28, BLACK, 1", "28, WHITE, 1",
            "29, BLACK, 1", "29, WHITE, 1", "30, BLACK, 1", "30, WHITE, 1", "31, BLACK, 1", "31, WHITE, 1",
            "32, BLACK, 1", "32, WHITE, 1", "33, BLACK, 1", "33, WHITE, 1", "34, BLACK, 1", "34, WHITE, 1",
            "35, BLACK, 1", "35, WHITE, 1", "36, BLACK, 1", "36, WHITE, 1", "37, BLACK, 1", "37, WHITE, 1",
            "38, BLACK, 1", "38, WHITE, 1", "39, BLACK, 1", "39, WHITE, 1", "40, BLACK, 1", "40, WHITE, 1",
            "41, BLACK, 1", "41, WHITE, 1", "42, BLACK, 1", "42, WHITE, 1", "43, BLACK, 1", "43, WHITE, 1",
            "44, BLACK, 1", "44, WHITE, 1", "45, BLACK, 1", "45, WHITE, 1", "46, BLACK, 1", "46, WHITE, 1",
            "47, BLACK, 1", "47, WHITE, 1", "48, BLACK, 2", "48, WHITE, 1", "49, BLACK, 2", "49, WHITE, 1",
            "50, BLACK, 2", "50, WHITE, 1", "51, BLACK, 2", "51, WHITE, 1", "52, BLACK, 2", "52, WHITE, 1",
            "53, BLACK, 2", "53, WHITE, 1", "54, BLACK, 2", "54, WHITE, 1", "55, BLACK, 2", "55, WHITE, 1" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate,
            Alliance alliance, int size) {
        Pawn pawn = assertDoesNotThrow(() -> new Pawn(alliance));
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, pawn));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> pawn.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
