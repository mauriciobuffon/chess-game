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

public class QueenTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The queen has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 21", "1, 21", "2, 21", "3, 21", "4, 21", "5, 21", "6, 21", "7, 21", "8, 21", "9, 23", "10, 23",
            "11, 23", "12, 23", "13, 23", "14, 23", "15, 21", "16, 21", "17, 23", "18, 25", "19, 25", "20, 25",
            "21, 25", "22, 23", "23, 21", "24, 21", "25, 23", "26, 25", "27, 27", "28, 27", "29, 25", "30, 23",
            "31, 21", "32, 21", "33, 23", "34, 25", "35, 27", "36, 27", "37, 25", "38, 23", "39, 21", "40, 21",
            "41, 23", "42, 25", "43, 25", "44, 25", "45, 25", "46, 23", "47, 21", "48, 21", "49, 23", "50, 23",
            "51, 23", "52, 23", "53, 23", "54, 23", "55, 21", "56, 21", "57, 21", "58, 21", "59, 21", "60, 21",
            "61, 21", "62, 21", "63, 21" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate,
            int size) {
        Queen queen = new Queen(Alliance.WHITE);
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, queen));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> queen.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
