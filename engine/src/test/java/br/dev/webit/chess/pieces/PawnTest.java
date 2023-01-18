package br.dev.webit.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.BoardConfiguration;
import br.dev.webit.chess.board.Color;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.TileCoordinate;

public class PawnTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The {1} pawn has {2} legal moves on the tile coordinate {0}")
    @CsvSource({ "16, BLACK, 1", "16, WHITE, 2", "17, BLACK, 1", "17, WHITE, 2", "18, BLACK, 1", "18, WHITE, 2",
            "19, BLACK, 1", "19, WHITE, 2", "20, BLACK, 1", "20, WHITE, 2", "21, BLACK, 1", "21, WHITE, 2",
            "22, BLACK, 1", "22, WHITE, 2", "23, BLACK, 1", "23, WHITE, 2", "32, BLACK, 1", "32, WHITE, 1",
            "33, BLACK, 1", "33, WHITE, 1", "34, BLACK, 1", "34, WHITE, 1", "35, BLACK, 1", "35, WHITE, 1",
            "36, BLACK, 1", "36, WHITE, 1", "37, BLACK, 1", "37, WHITE, 1", "38, BLACK, 1", "38, WHITE, 1",
            "39, BLACK, 1", "39, WHITE, 1", "48, BLACK, 1", "48, WHITE, 1", "49, BLACK, 1", "49, WHITE, 1",
            "50, BLACK, 1", "50, WHITE, 1", "51, BLACK, 1", "51, WHITE, 1", "52, BLACK, 1", "52, WHITE, 1",
            "53, BLACK, 1", "53, WHITE, 1", "54, BLACK, 1", "54, WHITE, 1", "55, BLACK, 1", "55, WHITE, 1",
            "64, BLACK, 1", "64, WHITE, 1", "65, BLACK, 1", "65, WHITE, 1", "66, BLACK, 1", "66, WHITE, 1",
            "67, BLACK, 1", "67, WHITE, 1", "68, BLACK, 1", "68, WHITE, 1", "69, BLACK, 1", "69, WHITE, 1",
            "70, BLACK, 1", "70, WHITE, 1", "71, BLACK, 1", "71, WHITE, 1", "80, BLACK, 1", "80, WHITE, 1",
            "81, BLACK, 1", "81, WHITE, 1", "82, BLACK, 1", "82, WHITE, 1", "83, BLACK, 1", "83, WHITE, 1",
            "84, BLACK, 1", "84, WHITE, 1", "85, BLACK, 1", "85, WHITE, 1", "86, BLACK, 1", "86, WHITE, 1",
            "87, BLACK, 1", "87, WHITE, 1", "96, BLACK, 2", "96, WHITE, 1", "97, BLACK, 2", "97, WHITE, 1",
            "98, BLACK, 2", "98, WHITE, 1", "99, BLACK, 2", "99, WHITE, 1", "100, BLACK, 2", "100, WHITE, 1",
            "101, BLACK, 2", "101, WHITE, 1", "102, BLACK, 2", "102, WHITE, 1", "103, BLACK, 2", "103, WHITE, 1" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate, Color color, int size) {
        Pawn pawn = assertDoesNotThrow(() -> new Pawn(color));
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, pawn));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> pawn.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
