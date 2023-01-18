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

public class KingTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The king has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 3", "1, 5", "2, 5", "3, 5", "4, 5", "5, 5", "6, 5", "7, 3", "16, 5", "17, 8", "18, 8", "19, 8",
            "20, 8", "21, 8", "22, 8", "23, 5", "32, 5", "33, 8", "34, 8", "35, 8", "36, 8", "37, 8", "38, 8", "39, 5",
            "48, 5", "49, 8", "50, 8", "51, 8", "52, 8", "53, 8", "54, 8", "55, 5", "64, 5", "65, 8", "66, 8", "67, 8",
            "68, 8", "69, 8", "70, 8", "71, 5", "80, 5", "81, 8", "82, 8", "83, 8", "84, 8", "85, 8", "86, 8", "87, 5",
            "96, 5", "97, 8", "98, 8", "99, 8", "100, 8", "101, 8", "102, 8", "103, 5", "112, 3", "113, 5", "114, 5",
            "115, 5", "116, 5", "117, 5", "118, 5", "119, 3" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate, int size) {
        King king = new King(Color.WHITE);
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, king));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> king.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
