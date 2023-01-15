package br.dev.webit.chess.board;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.SplittableRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

    @DisplayName("Invalid tile coordinate initialization")
    @ParameterizedTest(name = "[{index}] {0} is an invalid coordinate")
    @MethodSource("provideSomeInvalidCoordinates")
    public void invalidInitialization(int coordinate) {
        InvalidTileCoordinateException exception = assertThrows(InvalidTileCoordinateException.class,
                () -> TileCoordinate.of(coordinate));
        assertNotNull(exception);
    }

    @DisplayName("Invalid tile coordinate move command")
    @ParameterizedTest(name = "[{index}] The coordinate offset pair [{1}, {2}] is an invalid move command for the coordinate \"{0}\"")
    @MethodSource("provideMinimumInvalidMoveCommandsPerCoordinate")
    public void invalidMoveCommand(int coordinate, int xOffset, int yOffset) {
        TileCoordinate tile = assertDoesNotThrow(() -> TileCoordinate.of(coordinate));
        InvalidTileCoordinateException exception = assertThrows(InvalidTileCoordinateException.class,
                () -> tile.move(xOffset, yOffset));
        assertNotNull(exception);
    }

    static Stream<Arguments> provideMinimumInvalidMoveCommandsPerCoordinate() {
        return provideAllValidCoordinates().boxed().flatMap(coordinate -> {
            final int coordinateX = coordinate % 8;
            final int coordinateY = coordinate / 8;

            final int[][] offsets = {
                    { -1 - coordinateX, 0 }, // first tile out of bounds on the left (axis-x)
                    { 8 - coordinateX, 0 }, // first tile out of bounds on the right (axis-x)
                    { 0, -1 - coordinateY }, // first tile out of bounds below (axis-y)
                    { 0, 8 - coordinateY } // first tile out of bounds above (axis-y)
            };

            return Stream.of(offsets).map(p -> {
                return Arguments.of(coordinate, p[0], p[1]);
            });
        });
    }

    static IntStream provideAllValidCoordinates() {
        return IntStream.range(0, 64);
    }

    static IntStream provideSomeInvalidCoordinates() {
        return IntStream.concat(
                random.ints(Byte.MAX_VALUE, Integer.MIN_VALUE, 0),
                random.ints(Byte.MAX_VALUE, 64, Integer.MAX_VALUE));
    }

    static Stream<Arguments> provideStringRepresentationPerCoordinate() {
        return Stream.of(
                Arguments.of(0, "a1"), Arguments.of(1, "b1"), Arguments.of(2, "c1"), Arguments.of(3, "d1"),
                Arguments.of(4, "e1"), Arguments.of(5, "f1"), Arguments.of(6, "g1"), Arguments.of(7, "h1"),
                Arguments.of(8, "a2"), Arguments.of(9, "b2"), Arguments.of(10, "c2"), Arguments.of(11, "d2"),
                Arguments.of(12, "e2"), Arguments.of(13, "f2"), Arguments.of(14, "g2"), Arguments.of(15, "h2"),
                Arguments.of(16, "a3"), Arguments.of(17, "b3"), Arguments.of(18, "c3"), Arguments.of(19, "d3"),
                Arguments.of(20, "e3"), Arguments.of(21, "f3"), Arguments.of(22, "g3"), Arguments.of(23, "h3"),
                Arguments.of(24, "a4"), Arguments.of(25, "b4"), Arguments.of(26, "c4"), Arguments.of(27, "d4"),
                Arguments.of(28, "e4"), Arguments.of(29, "f4"), Arguments.of(30, "g4"), Arguments.of(31, "h4"),
                Arguments.of(32, "a5"), Arguments.of(33, "b5"), Arguments.of(34, "c5"), Arguments.of(35, "d5"),
                Arguments.of(36, "e5"), Arguments.of(37, "f5"), Arguments.of(38, "g5"), Arguments.of(39, "h5"),
                Arguments.of(40, "a6"), Arguments.of(41, "b6"), Arguments.of(42, "c6"), Arguments.of(43, "d6"),
                Arguments.of(44, "e6"), Arguments.of(45, "f6"), Arguments.of(46, "g6"), Arguments.of(47, "h6"),
                Arguments.of(48, "a7"), Arguments.of(49, "b7"), Arguments.of(50, "c7"), Arguments.of(51, "d7"),
                Arguments.of(52, "e7"), Arguments.of(53, "f7"), Arguments.of(54, "g7"), Arguments.of(55, "h7"),
                Arguments.of(56, "a8"), Arguments.of(57, "b8"), Arguments.of(58, "c8"), Arguments.of(59, "d8"),
                Arguments.of(60, "e8"), Arguments.of(61, "f8"), Arguments.of(62, "g8"), Arguments.of(63, "h8"));
    }
}
