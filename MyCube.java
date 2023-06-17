class MyCube {
    char c1;
    char c2;
    char c3;
    char c4;
    char c5;
    char c6;
    char c7;
    char c8;

    public MyCube(String text) {
        c1 = text.charAt(0);
        c2 = text.charAt(1);
        c3 = text.charAt(2);
        c4 = text.charAt(3);
        c5 = text.charAt(4);
        c6 = text.charAt(5);
        c7 = text.charAt(6);
        c8 = text.charAt(7);
    }

    public void rotateLeft() {
        char tmp = c1;
        char tmp2 = c4;
        c1 = c2;
        c2 = c6;
        c6 = c5;
        c5 = tmp;
        c4 = c3;
        c3 = c7;
        c7 = c8;
        c8 = tmp2;
    }

    public String getText() {
        char[] arr = new char[]{c1,c2,c3,c4,c5,c6,c7,c8};
        return String.valueOf(arr);
    }

    public void rotateRight() {
        char tmp = c1;
        char tmp2 = c4;
        c1 = c5;
        c5 = c6;
        c6 = c2;
        c2 = tmp;
        c4 = c8;
        c8 = c7;
        c7 = c3;
        c3 = tmp2;
    }

    public void rotateUp() {
        char tmp1 = c1;
        c1 = c3;
        c3 = c8;
        c8 = c5;
        c5 = tmp1;
        char tmp2 = c2;
        c2 = c4;
        c4 = c7;
        c7 = c6;
        c6 = tmp2;
    }

    public void rotateDown() {
        char tmp1 = c1;
        c1 = c5;
        c5 = c8;
        c8 = c3;
        c3 = tmp1;
        char tmp2 = c2;
        c2 = c6;
        c6 = c7;
        c7 = c4;
        c4 = tmp2;
    }
}








