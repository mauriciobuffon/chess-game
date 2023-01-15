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

public class KingTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The king has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 3", "1, 5", "2, 5", "3, 5", "4, 5", "5, 5", "6, 5", "7, 3", "8, 5", "9, 8", "10, 8", "11, 8",
            "12, 8", "13, 8", "14, 8", "15, 5", "16, 5", "17, 8", "18, 8", "19, 8", "20, 8", "21, 8", "22, 8", "23, 5",
            "24, 5", "25, 8", "26, 8", "27, 8", "28, 8", "29, 8", "30, 8", "31, 5", "32, 5", "33, 8", "34, 8", "35, 8",
            "36, 8", "37, 8", "38, 8", "39, 5", "40, 5", "41, 8", "42, 8", "43, 8", "44, 8", "45, 8", "46, 8", "47, 5",
            "48, 5", "49, 8", "50, 8", "51, 8", "52, 8", "53, 8", "54, 8", "55, 5", "56, 3", "57, 5", "58, 5", "59, 5",
            "60, 5", "61, 5", "62, 5", "63, 3" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate,
            int size) {
        King king = new King(Alliance.WHITE);
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, king));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> king.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
