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

public class BishopTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The bishop has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 7", "1, 7", "2, 7", "3, 7", "4, 7", "5, 7", "6, 7", "7, 7", "8, 7", "9, 9", "10, 9", "11, 9",
            "12, 9", "13, 9", "14, 9", "15, 7", "16, 7", "17, 9", "18, 11", "19, 11", "20, 11", "21, 11", "22, 9",
            "23, 7", "24, 7", "25, 9", "26, 11", "27, 13", "28, 13", "29, 11", "30, 9", "31, 7", "32, 7", "33, 9",
            "34, 11", "35, 13", "36, 13", "37, 11", "38, 9", "39, 7", "40, 7", "41, 9", "42, 11", "43, 11", "44, 11",
            "45, 11", "46, 9", "47, 7", "48, 7", "49, 9", "50, 9", "51, 9", "52, 9", "53, 9", "54, 9", "55, 7", "56, 7",
            "57, 7", "58, 7", "59, 7", "60, 7", "61, 7", "62, 7", "63, 7" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate,
            int size) {
        Bishop bishop = new Bishop(Alliance.WHITE);
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, bishop));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> bishop.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
