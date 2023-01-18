package br.dev.webit.chess.board;

public final class TileCoordinate {

    private static final char[] COOR_X = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private static final char[] COOR_Y = { '1', '2', '3', '4', '5', '6', '7', '8' };

    private static final TileCoordinate[] COORDINATES = coordinatesInitialization();

    private static TileCoordinate[] coordinatesInitialization() {
        TileCoordinate[] array = new TileCoordinate[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            if ((i & MASK) == 0) {
                array[i] = new TileCoordinate(i);
            } else {
                array[i] = null;
            }
        }
        return array;
    }

    public static final TileCoordinate A1 = COORDINATES[0];
    public static final TileCoordinate A2 = COORDINATES[16];
    public static final TileCoordinate A3 = COORDINATES[32];
    public static final TileCoordinate A4 = COORDINATES[48];
    public static final TileCoordinate A5 = COORDINATES[64];
    public static final TileCoordinate A6 = COORDINATES[80];
    public static final TileCoordinate A7 = COORDINATES[96];
    public static final TileCoordinate A8 = COORDINATES[112];
    public static final TileCoordinate B1 = COORDINATES[1];
    public static final TileCoordinate B2 = COORDINATES[17];
    public static final TileCoordinate B3 = COORDINATES[33];
    public static final TileCoordinate B4 = COORDINATES[49];
    public static final TileCoordinate B5 = COORDINATES[65];
    public static final TileCoordinate B6 = COORDINATES[81];
    public static final TileCoordinate B7 = COORDINATES[97];
    public static final TileCoordinate B8 = COORDINATES[113];
    public static final TileCoordinate C1 = COORDINATES[2];
    public static final TileCoordinate C2 = COORDINATES[18];
    public static final TileCoordinate C3 = COORDINATES[34];
    public static final TileCoordinate C4 = COORDINATES[50];
    public static final TileCoordinate C5 = COORDINATES[66];
    public static final TileCoordinate C6 = COORDINATES[82];
    public static final TileCoordinate C7 = COORDINATES[98];
    public static final TileCoordinate C8 = COORDINATES[114];
    public static final TileCoordinate D1 = COORDINATES[3];
    public static final TileCoordinate D2 = COORDINATES[19];
    public static final TileCoordinate D3 = COORDINATES[35];
    public static final TileCoordinate D4 = COORDINATES[51];
    public static final TileCoordinate D5 = COORDINATES[67];
    public static final TileCoordinate D6 = COORDINATES[83];
    public static final TileCoordinate D7 = COORDINATES[99];
    public static final TileCoordinate D8 = COORDINATES[115];
    public static final TileCoordinate E1 = COORDINATES[4];
    public static final TileCoordinate E2 = COORDINATES[20];
    public static final TileCoordinate E3 = COORDINATES[36];
    public static final TileCoordinate E4 = COORDINATES[52];
    public static final TileCoordinate E5 = COORDINATES[68];
    public static final TileCoordinate E6 = COORDINATES[84];
    public static final TileCoordinate E7 = COORDINATES[100];
    public static final TileCoordinate E8 = COORDINATES[116];
    public static final TileCoordinate F1 = COORDINATES[5];
    public static final TileCoordinate F2 = COORDINATES[21];
    public static final TileCoordinate F3 = COORDINATES[37];
    public static final TileCoordinate F4 = COORDINATES[53];
    public static final TileCoordinate F5 = COORDINATES[69];
    public static final TileCoordinate F6 = COORDINATES[85];
    public static final TileCoordinate F7 = COORDINATES[101];
    public static final TileCoordinate F8 = COORDINATES[117];
    public static final TileCoordinate G1 = COORDINATES[6];
    public static final TileCoordinate G2 = COORDINATES[22];
    public static final TileCoordinate G3 = COORDINATES[38];
    public static final TileCoordinate G4 = COORDINATES[54];
    public static final TileCoordinate G5 = COORDINATES[70];
    public static final TileCoordinate G6 = COORDINATES[86];
    public static final TileCoordinate G7 = COORDINATES[102];
    public static final TileCoordinate G8 = COORDINATES[118];
    public static final TileCoordinate H1 = COORDINATES[7];
    public static final TileCoordinate H2 = COORDINATES[23];
    public static final TileCoordinate H3 = COORDINATES[39];
    public static final TileCoordinate H4 = COORDINATES[55];
    public static final TileCoordinate H5 = COORDINATES[71];
    public static final TileCoordinate H6 = COORDINATES[87];
    public static final TileCoordinate H7 = COORDINATES[103];
    public static final TileCoordinate H8 = COORDINATES[119];

    public static final int MASK = 0b1111_1111_1111_1111_1111_1111_1000_1000;
    public static final int LENGTH = 128;

    private final int coordinate;

    private TileCoordinate(int tileCoordinate) {
        if ((tileCoordinate & MASK) != 0) {
            throw new IllegalArgumentException();
        }
        this.coordinate = tileCoordinate;
    }

    public static TileCoordinate of(int coordinate) throws InvalidTileCoordinateException {
        if ((coordinate & MASK) != 0) {
            throw new InvalidTileCoordinateException();
        }
        return COORDINATES[coordinate];
    }

    public TileCoordinate move(int offset) throws InvalidTileCoordinateException {
        return TileCoordinate.of(coordinate + offset);
    }

    public int getRank() {
        switch (coordinate & 0b0111_0000) {
            case 0x00:
                return 1;
            case 0x10:
                return 2;
            case 0x20:
                return 3;
            case 0x30:
                return 4;
            case 0x40:
                return 5;
            case 0x50:
                return 6;
            case 0x60:
                return 7;
            case 0x70:
                return 8;
            default:
                throw new IllegalStateException();
        }
    }

    public int getFile() {
        switch (coordinate & 0b0000_0111) {
            case 0x00:
                return 1;
            case 0x01:
                return 2;
            case 0x02:
                return 3;
            case 0x03:
                return 4;
            case 0x04:
                return 5;
            case 0x05:
                return 6;
            case 0x06:
                return 7;
            case 0x07:
                return 8;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        return "%s%s".formatted(COOR_X[coordinate % 16], COOR_Y[coordinate / 16]);
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
