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

public class QueenTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The queen has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 21", "1, 21", "2, 21", "3, 21", "4, 21", "5, 21", "6, 21", "7, 21", "16, 21", "17, 23", "18, 23",
            "19, 23", "20, 23", "21, 23", "22, 23", "23, 21", "32, 21", "33, 23", "34, 25", "35, 25", "36, 25",
            "37, 25", "38, 23", "39, 21", "48, 21", "49, 23", "50, 25", "51, 27", "52, 27", "53, 25", "54, 23",
            "55, 21", "64, 21", "65, 23", "66, 25", "67, 27", "68, 27", "69, 25", "70, 23", "71, 21", "80, 21",
            "81, 23", "82, 25", "83, 25", "84, 25", "85, 25", "86, 23", "87, 21", "96, 21", "97, 23", "98, 23",
            "99, 23", "100, 23", "101, 23", "102, 23", "103, 21", "112, 21", "113, 21", "114, 21", "115, 21", "116, 21",
            "117, 21", "118, 21", "119, 21" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate, int size) {
        Queen queen = new Queen(Color.WHITE);
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, queen));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> queen.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
