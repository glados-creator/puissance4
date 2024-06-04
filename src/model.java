public class model {
    public enum ending {
        running,
        lose,
        win;
    }

    private static final short MIN_WIDTH = 5;
    private static final short MAX_WIDTH = 16;
    private static final short MIN_HEIGHT = 5;
    private static final short MAX_HEIGHT = 16;
    private static final short MIN_EQUIPE = 2;
    private static final short MAX_EQUIPE = 16;

    private short iwidth;
    private short iheight;
    private short nbequipe;
    private short[][] iboard;
    private ending state;

    public void create_model(short width, short height, short n_nbequipe) {
        nbequipe = (short) n_nbequipe;
        iwidth = width;
        iheight = height;
        state = ending.running;
        iboard = new short[width][height];
        for (short i = 0; i < iwidth; i++) {
            for (short j = 0; j < iheight; j++) {
                iboard[i][j] = 0;
            }
        }
    }

    public model() {
        create_model((short) 7, (short) 6, (short) 2);
    }

    public boolean change_size(short width, short height) {
        if (width < MIN_WIDTH || width > MAX_WIDTH)
            return false;
        if (height < MIN_HEIGHT || height > MAX_HEIGHT)
            return false;
        short[][] board = new short[width][height];
        // transpose
        for (short i = 0; i < Math.min(iwidth, width); i++) {
            for (short j = 0; j < Math.min(iheight, height); j++) {
                board[i][j] = iboard[i][j];
            }
        }
        iboard = board;
        return true;
    }

    public boolean change_nbequipe(short n_nbequipe) {
        if (n_nbequipe < MIN_EQUIPE || n_nbequipe > MAX_EQUIPE)
            return false;
        nbequipe = n_nbequipe;
        for (short i = 0; i < iwidth; i++) {
            for (short j = 0; j < iheight; j++) {
                if (iboard[i][j] > nbequipe)
                    iboard[i][j] = 0;
            }
        }
        return true;
    }

    public short get(short width, short height) {
        if (width < 0 || width > iwidth)
            return -1;
        if (height < 0 || height > iheight)
            return -1;
        return iboard[width][height];
    }

    public boolean set(short width, short height, short equipe) {
        if (width < 0 || width > iwidth)
            return false;
        if (height < 0 || height > iheight)
            return false;
        if (iboard[width][height] != 0)
            return false;
        iboard[width][height] = equipe;
        check();
        return true;
    }

    private void check() {
        ; // TODO : code
    }

    public boolean win() {
        return ending.win == state;
    }

    public boolean lose() {
        return ending.lose == state;
    }

    public ending getstate() {
        return state;
    }

    public short getwidth() {
        return iwidth;
    }

    public short getheight() {
        return iheight;
    }

    public short getnbequipe() {
        return nbequipe;
    }

}
