package br.dev.webit.chess.pieces;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import br.dev.webit.chess.board.InvalidTileCoordinateException;
import br.dev.webit.chess.board.TileCoordinate;

public class TileCoordinateConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (source == null) {
            throw new ArgumentConversionException("Cannot convert null objects.");
        }

        try {
            String value = String.class.cast(source);
            source = Integer.parseInt(value);
        } catch (ClassCastException ex) {
        }

        try {
            int value = Integer.class.cast(source);
            try {
                return TileCoordinate.of(value);
            } catch (InvalidTileCoordinateException ex) {
                throw new ArgumentConversionException("Cannot convert integer value " + value, ex);
            }
        } catch (ClassCastException ex) {
        }

        throw new ArgumentConversionException("%s cannot convert objects of type [%s]".formatted(
                getClass().getSimpleName(), context.getParameter().getType().getName()));
    }
}
