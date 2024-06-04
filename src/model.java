public class model {
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
    private short state;

    public void create_model(short width, short height, short n_nbequipe) {
        nbequipe = (short) n_nbequipe;
        iwidth = width;
        iheight = height;
        state = -1;
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

    public boolean inbound(short width, short height) {
        if (width < 0 || width > iwidth)
            return false;
        if (height < 0 || height > iheight)
            return false;
        return true;
    }

    public short get(short width, short height) {
        if (!(inbound(width, height)))
            return -1;
        return iboard[width][height];
    }

    public boolean set(short width, short height, short equipe) {
        if (!(inbound(width, height)))
            return false;
        if (iboard[width][height] != 0)
            return false;
        iboard[width][height] = equipe;
        state = check();
        return true;
    }

    private short check() {
        short nb = 0;
        short last = -1;
        for (short i = 0; i < iwidth && state == -1; i++) {
            for (short j = 0; j < iheight && state == -1; j++) {
                // for each case
                for (short dx = -1; dx < 2 && state == -1; dx++) {
                    for (short dy = -1; dy < 2 && state == -1; dy++) {
                        // for each direction
                        last = -1;
                        nb = 0;
                        for (short h = 0; h < 4; h++) {
                            // 4 times
                            short c_w = (short)(i+(dx*h));
                            short c_h = (short)(j+(dy*h));
                            if (!(inbound(c_w,c_h)))
                                break;
                            if (last == -1 || last == iboard[c_w][c_h]) {
                                if (last == -1) {
                                    last = iboard[c_w][c_h];
                                } else {
                                    nb += 1;
                                }
                            } else {
                                last = iboard[c_w][c_h];
                                nb = 1;
                            }
                        }
                        if (nb > 3) {
                            state = last;
                        }

                    }
                }
            }
        }
        return -1;
    }

    public boolean win() {
        return state != -1;
    }

    public short get_equipe_win() {
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
