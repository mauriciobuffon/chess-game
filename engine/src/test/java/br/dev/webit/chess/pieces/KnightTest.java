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

public class KnightTest {

    @DisplayName("Number of legal moves per tile")
    @ParameterizedTest(name = "[{index}] The knight has {1} legal moves on the tile coordinate {0}")
    @CsvSource({ "0, 2", "1, 3", "2, 4", "3, 4", "4, 4", "5, 4", "6, 3", "7, 2", "16, 3", "17, 4", "18, 6", "19, 6",
            "20, 6", "21, 6", "22, 4", "23, 3", "32, 4", "33, 6", "34, 8", "35, 8", "36, 8", "37, 8", "38, 6", "39, 4",
            "48, 4", "49, 6", "50, 8", "51, 8", "52, 8", "53, 8", "54, 6", "55, 4", "64, 4", "65, 6", "66, 8", "67, 8",
            "68, 8", "69, 8", "70, 6", "71, 4", "80, 4", "81, 6", "82, 8", "83, 8", "84, 8", "85, 8", "86, 6", "87, 4",
            "96, 3", "97, 4", "98, 6", "99, 6", "100, 6", "101, 6", "102, 4", "103, 3", "112, 2", "113, 3", "114, 4",
            "115, 4", "116, 4", "117, 4", "118, 3", "119, 2" })
    public void numberOfLegalMovesPerTile(
            @ConvertWith(TileCoordinateConverter.class) TileCoordinate tileCoordinate, int size) {
        Knight knight = assertDoesNotThrow(() -> new Knight(Color.WHITE));
        BoardConfiguration config = assertDoesNotThrow(() -> (BoardConfiguration) () -> Map.of(tileCoordinate, knight));
        Board board = assertDoesNotThrow(() -> new Board(config));
        Collection<Move> moves = assertDoesNotThrow(() -> knight.calculateLegalMoves(board));
        assertEquals(size, moves.size());
    }
}
