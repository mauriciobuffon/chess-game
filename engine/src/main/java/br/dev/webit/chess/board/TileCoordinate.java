package br.dev.webit.chess.board;

public final class TileCoordinate {

    private static final char[] COOR_X = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private static final char[] COOR_Y = { '1', '2', '3', '4', '5', '6', '7', '8' };

    private static final TileCoordinate[] COORDINATES = coordinatesInitialization();

    public static final TileCoordinate A1 = COORDINATES[0];
    public static final TileCoordinate A2 = COORDINATES[8];
    public static final TileCoordinate A3 = COORDINATES[16];
    public static final TileCoordinate A4 = COORDINATES[24];
    public static final TileCoordinate A5 = COORDINATES[32];
    public static final TileCoordinate A6 = COORDINATES[40];
    public static final TileCoordinate A7 = COORDINATES[48];
    public static final TileCoordinate A8 = COORDINATES[56];
    public static final TileCoordinate B1 = COORDINATES[1];
    public static final TileCoordinate B2 = COORDINATES[9];
    public static final TileCoordinate B3 = COORDINATES[17];
    public static final TileCoordinate B4 = COORDINATES[25];
    public static final TileCoordinate B5 = COORDINATES[33];
    public static final TileCoordinate B6 = COORDINATES[41];
    public static final TileCoordinate B7 = COORDINATES[49];
    public static final TileCoordinate B8 = COORDINATES[57];
    public static final TileCoordinate C1 = COORDINATES[2];
    public static final TileCoordinate C2 = COORDINATES[10];
    public static final TileCoordinate C3 = COORDINATES[18];
    public static final TileCoordinate C4 = COORDINATES[26];
    public static final TileCoordinate C5 = COORDINATES[34];
    public static final TileCoordinate C6 = COORDINATES[42];
    public static final TileCoordinate C7 = COORDINATES[50];
    public static final TileCoordinate C8 = COORDINATES[58];
    public static final TileCoordinate D1 = COORDINATES[3];
    public static final TileCoordinate D2 = COORDINATES[11];
    public static final TileCoordinate D3 = COORDINATES[19];
    public static final TileCoordinate D4 = COORDINATES[27];
    public static final TileCoordinate D5 = COORDINATES[35];
    public static final TileCoordinate D6 = COORDINATES[43];
    public static final TileCoordinate D7 = COORDINATES[51];
    public static final TileCoordinate D8 = COORDINATES[59];
    public static final TileCoordinate E1 = COORDINATES[4];
    public static final TileCoordinate E2 = COORDINATES[12];
    public static final TileCoordinate E3 = COORDINATES[20];
    public static final TileCoordinate E4 = COORDINATES[28];
    public static final TileCoordinate E5 = COORDINATES[36];
    public static final TileCoordinate E6 = COORDINATES[44];
    public static final TileCoordinate E7 = COORDINATES[52];
    public static final TileCoordinate E8 = COORDINATES[60];
    public static final TileCoordinate F1 = COORDINATES[5];
    public static final TileCoordinate F2 = COORDINATES[13];
    public static final TileCoordinate F3 = COORDINATES[21];
    public static final TileCoordinate F4 = COORDINATES[29];
    public static final TileCoordinate F5 = COORDINATES[37];
    public static final TileCoordinate F6 = COORDINATES[45];
    public static final TileCoordinate F7 = COORDINATES[53];
    public static final TileCoordinate F8 = COORDINATES[61];
    public static final TileCoordinate G1 = COORDINATES[6];
    public static final TileCoordinate G2 = COORDINATES[14];
    public static final TileCoordinate G3 = COORDINATES[22];
    public static final TileCoordinate G4 = COORDINATES[30];
    public static final TileCoordinate G5 = COORDINATES[38];
    public static final TileCoordinate G6 = COORDINATES[46];
    public static final TileCoordinate G7 = COORDINATES[54];
    public static final TileCoordinate G8 = COORDINATES[62];
    public static final TileCoordinate H1 = COORDINATES[7];
    public static final TileCoordinate H2 = COORDINATES[15];
    public static final TileCoordinate H3 = COORDINATES[23];
    public static final TileCoordinate H4 = COORDINATES[31];
    public static final TileCoordinate H5 = COORDINATES[39];
    public static final TileCoordinate H6 = COORDINATES[47];
    public static final TileCoordinate H7 = COORDINATES[55];
    public static final TileCoordinate H8 = COORDINATES[63];

    private final int coordinate;

    private TileCoordinate(int tileCoordinate) {
        if (tileCoordinate < 0 || tileCoordinate > 63) {
            throw new IllegalArgumentException();
        }
        this.coordinate = tileCoordinate;
    }

    private static TileCoordinate[] coordinatesInitialization() {
        TileCoordinate[] array = new TileCoordinate[64];
        for (int i = 0; i < 64; i++) {
            array[i] = new TileCoordinate(i);
        }
        return array;
    }

    public static TileCoordinate of(int intCoordinate) throws InvalidTileCoordinateException {
        if (intCoordinate < 0 || intCoordinate > 63) {
            throw new InvalidTileCoordinateException();
        }
        return COORDINATES[intCoordinate];
    }

    public TileCoordinate move(int coordinateOffset) throws InvalidTileCoordinateException {
        return TileCoordinate.of(coordinate + coordinateOffset);
    }

    public TileCoordinate move(int xOffset, int yOffset) throws InvalidTileCoordinateException {
        final int coordX = (coordinate % 8) + xOffset;
        final int coordY = (coordinate / 8) + yOffset;

        if (coordX < 0 || coordX > 7 || coordY < 0 || coordY > 7) {
            throw new InvalidTileCoordinateException();
        }

        return TileCoordinate.of(coordY * 8 + coordX);
    }

    @Override
    public String toString() {
        return "%s%s".formatted(COOR_X[coordinate % 8], COOR_Y[coordinate / 8]);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.coordinate;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TileCoordinate other = (TileCoordinate) obj;
        return this.coordinate == other.coordinate;
    }
}
