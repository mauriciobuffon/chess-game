package br.dev.webit.chess.board;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class TileCoordinateTest {

    private static final SplittableRandom random = new SplittableRandom();

    @DisplayName("Tile coordinate string representation")
    @ParameterizedTest(name = "[{index}] \"{1}\" is the representation of the coordinate \"{0}\"")
    @MethodSource("provideStringRepresentationPerCoordinate")
    public void coordinateStringRepresentation(int coordinate, String expected) {
        TileCoordinate tile = assertDoesNotThrow(() -> TileCoordinate.of(coordinate));
        assertEquals(expected, tile.toString());
    }

    @DisplayName("Tile coordinate's file and rank locations")
    @ParameterizedTest(name = "[{index}] The coordinate {0} is located at the file {1} and the rank {2}")
    @CsvSource({ "0, 1, 1", "16, 1, 2", "32, 1, 3", "48, 1, 4", "64, 1, 5", "80, 1, 6", "96, 1, 7", "112, 1, 8",
            "1, 2, 1", "17, 2, 2", "33, 2, 3", "49, 2, 4", "65, 2, 5", "81, 2, 6", "97, 2, 7", "113, 2, 8", "2, 3, 1",
            "18, 3, 2", "34, 3, 3", "50, 3, 4", "66, 3, 5", "82, 3, 6", "98, 3, 7", "114, 3, 8", "3, 4, 1", "19, 4, 2",
            "35, 4, 3", "51, 4, 4", "67, 4, 5", "83, 4, 6", "99, 4, 7", "115, 4, 8", "4, 5, 1", "20, 5, 2", "36, 5, 3",
            "52, 5, 4", "68, 5, 5", "84, 5, 6", "100, 5, 7", "116, 5, 8", "5, 6, 1", "21, 6, 2", "37, 6, 3", "53, 6, 4",
            "69, 6, 5", "85, 6, 6", "101, 6, 7", "117, 6, 8", "6, 7, 1", "22, 7, 2", "38, 7, 3", "54, 7, 4", "70, 7, 5",
            "86, 7, 6", "102, 7, 7", "118, 7, 8", "7, 8, 1", "23, 8, 2", "39, 8, 3", "55, 8, 4", "71, 8, 5", "87, 8, 6",
            "103, 8, 7", "119, 8, 8" })
    public void fileRankRepresentation(int coordinate, int file, int rank) {
        TileCoordinate tile = assertDoesNotThrow(() -> TileCoordinate.of(coordinate));
        assertEquals(file, tile.getFile());
        assertEquals(rank, tile.getRank());
    }

    @DisplayName("Invalid tile coordinate initialization")
    @ParameterizedTest(name = "[{index}] {0} is an invalid coordinate")
    @MethodSource("provideSomeInvalidCoordinates")
    public void invalidInitialization(int coordinate) {
        InvalidTileCoordinateException exception = assertThrows(InvalidTileCoordinateException.class,
                () -> TileCoordinate.of(coordinate));
        assertNotNull(exception);
    }

    @DisplayName("Invalid tile coordinate move command")
    @ParameterizedTest(name = "[{index}] The coordinate offset {1} is an invalid move command for the coordinate \"{0}\"")
    @MethodSource("provideMinimumInvalidMoveCommandsPerCoordinate")
    public void invalidMoveCommand(int coordinate, int offset) {
        TileCoordinate tile = assertDoesNotThrow(() -> TileCoordinate.of(coordinate));
        InvalidTileCoordinateException exception = assertThrows(InvalidTileCoordinateException.class,
                () -> tile.move(offset));
        assertNotNull(exception);
    }

    static IntStream provideAllCoordinates() {
        return IntStream.range(0, TileCoordinate.LENGTH);
    }

    static Stream<Arguments> provideMinimumInvalidMoveCommandsPerCoordinate() {
        return provideAllCoordinates().boxed().filter(n -> (n & TileCoordinate.MASK) == 0).flatMap(coordinate -> {
            final int coordinateX = coordinate % 16;
            final int coordinateY = coordinate / 16;

            final int[] offsets = {
                    -1 - coordinateX, // first tile out of bounds on the left (axis-x)
                    8 - coordinateX, // first tile out of bounds on the right (axis-x)
                    (-1 - coordinateY) * 16, // first tile out of bounds below (axis-y)
                    (8 - coordinateY) * 16 // first tile out of bounds above (axis-y)
            };

            return Arrays.stream(offsets).mapToObj(offset -> {
                return Arguments.of(coordinate, offset);
            });
        });
    }

    static IntStream provideSomeInvalidCoordinates() {
        return IntStream.concat(
                provideAllCoordinates().filter(n -> (n & TileCoordinate.MASK) != 0),
                IntStream.concat(
                        random.ints(Byte.MAX_VALUE, Integer.MIN_VALUE, 0),
                        random.ints(Byte.MAX_VALUE, TileCoordinate.LENGTH, Integer.MAX_VALUE)));
    }

    static Stream<Arguments> provideStringRepresentationPerCoordinate() {
        return Stream.of(
                Arguments.of(0, "a1"), Arguments.of(16, "a2"), Arguments.of(32, "a3"), Arguments.of(48, "a4"),
                Arguments.of(64, "a5"), Arguments.of(80, "a6"), Arguments.of(96, "a7"), Arguments.of(112, "a8"),
                Arguments.of(1, "b1"), Arguments.of(17, "b2"), Arguments.of(33, "b3"), Arguments.of(49, "b4"),
                Arguments.of(65, "b5"), Arguments.of(81, "b6"), Arguments.of(97, "b7"), Arguments.of(113, "b8"),
                Arguments.of(2, "c1"), Arguments.of(18, "c2"), Arguments.of(34, "c3"), Arguments.of(50, "c4"),
                Arguments.of(66, "c5"), Arguments.of(82, "c6"), Arguments.of(98, "c7"), Arguments.of(114, "c8"),
                Arguments.of(3, "d1"), Arguments.of(19, "d2"), Arguments.of(35, "d3"), Arguments.of(51, "d4"),
                Arguments.of(67, "d5"), Arguments.of(83, "d6"), Arguments.of(99, "d7"), Arguments.of(115, "d8"),
                Arguments.of(4, "e1"), Arguments.of(20, "e2"), Arguments.of(36, "e3"), Arguments.of(52, "e4"),
                Arguments.of(68, "e5"), Arguments.of(84, "e6"), Arguments.of(100, "e7"), Arguments.of(116, "e8"),
                Arguments.of(5, "f1"), Arguments.of(21, "f2"), Arguments.of(37, "f3"), Arguments.of(53, "f4"),
                Arguments.of(69, "f5"), Arguments.of(85, "f6"), Arguments.of(101, "f7"), Arguments.of(117, "f8"),
                Arguments.of(6, "g1"), Arguments.of(22, "g2"), Arguments.of(38, "g3"), Arguments.of(54, "g4"),
                Arguments.of(70, "g5"), Arguments.of(86, "g6"), Arguments.of(102, "g7"), Arguments.of(118, "g8"),
                Arguments.of(7, "h1"), Arguments.of(23, "h2"), Arguments.of(39, "h3"), Arguments.of(55, "h4"),
                Arguments.of(71, "h5"), Arguments.of(87, "h6"), Arguments.of(103, "h7"), Arguments.of(119, "h8"));
    }
}
