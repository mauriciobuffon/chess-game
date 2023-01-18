package br.dev.webit.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import br.dev.webit.chess.board.Color;
import br.dev.webit.chess.board.Board;
import br.dev.webit.chess.board.BoardConfiguration;
import br.dev.webit.chess.board.Move;
import br.dev.webit.chess.board.TileCoordinate;

public class BishopTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The bishop has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 7", "1, 7", "2, 7", "3, 7", "4, 7", "5, 7", "6, 7", "7, 7", "16, 7", "17, 9", "18, 9", "19, 9",
            "20, 9", "21, 9", "22, 9", "23, 7", "32, 7", "33, 9", "34, 11", "35, 11", "36, 11", "37, 11", "38, 9",
            "39, 7", "48, 7", "49, 9", "50, 11", "51, 13", "52, 13", "53, 11", "54, 9", "55, 7", "64, 7", "65, 9",
            "66, 11", "67, 13", "68, 13", "69, 11", "70, 9", "71, 7", "80, 7", "81, 9", "82, 11", "83, 11", "84, 11",
            "85, 11", "86, 9", "87, 7", "96, 7", "97, 9", "98, 9", "99, 9", "100, 9", "101, 9", "102, 9", "103, 7",
            "112, 7", "113, 7", "114, 7", "115, 7", "116, 7", "117, 7", "118, 7", "119, 7" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate, int size) {
        Bishop bishop = new Bishop(Color.WHITE);
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, bishop));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> bishop.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
