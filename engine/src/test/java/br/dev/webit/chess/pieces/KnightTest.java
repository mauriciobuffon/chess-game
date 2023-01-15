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

public class KnightTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The knight has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 2", "1, 3", "2, 4", "3, 4", "4, 4", "5, 4", "6, 3", "7, 2", "8, 3", "9, 4", "10, 6", "11, 6",
            "12, 6", "13, 6", "14, 4", "15, 3", "16, 4", "17, 6", "18, 8", "19, 8", "20, 8", "21, 8", "22, 6", "23, 4",
            "24, 4", "25, 6", "26, 8", "27, 8", "28, 8", "29, 8", "30, 6", "31, 4", "32, 4", "33, 6", "34, 8", "35, 8",
            "36, 8", "37, 8", "38, 6", "39, 4", "40, 4", "41, 6", "42, 8", "43, 8", "44, 8", "45, 8", "46, 6", "47, 4",
            "48, 3", "49, 4", "50, 6", "51, 6", "52, 6", "53, 6", "54, 4", "55, 3", "56, 2", "57, 3", "58, 4", "59, 4",
            "60, 4", "61, 4", "62, 3", "63, 2" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate,
            int size) {
        Knight knight = assertDoesNotThrow(() -> new Knight(Alliance.WHITE));
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, knight));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> knight.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
