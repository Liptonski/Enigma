package model;

public class Rotor {
    private int[][] setup; // ustawienia szyfru rotora
    private int number; // numer rotora
    private int position; // obecna pozycja

    public Rotor(int roNum, int roPos){
        number=roNum;
        position=roPos;
        switch (number){
            case 0:
                setup = new int[][] {{0, 15 }, {1, 4 }, {2, 25 }, {3, 20 }, {4, 14 }, {5, 7 }, {6, 23 }, {7, 18 },
                        {8, 2 }, {9, 21 }, {10, 5 }, {11, 12 }, {12, 19 }, {13, 1 }, {14, 6 }, {15, 11 }, {16, 17 },
                        {17, 8 }, {18, 13 }, {19, 16 }, {20, 9 }, {21, 22 }, {22, 0 }, {23, 24 }, {24, 3 }, {25, 10 }};
                break;
            case 1:
                setup = new int[][] {{0, 25 }, {1, 14 }, {2, 20 }, {3, 4 }, {4, 18 }, {5, 24 }, {6, 3 }, {7, 10 },
                        {8, 5 }, {9, 22 }, {10, 15 }, {11, 2 }, {12, 8 }, {13, 16 }, {14, 23 }, {15, 7 }, {16, 12 },
                        {17, 21 }, {18, 1 }, {19, 11 }, {20, 6 }, {21, 13 }, {22, 9 }, {23, 17 }, {24, 0 }, {25, 19 }};
                break;
            case 2:
                setup = new int[][] {{0, 4 }, {1, 7 }, {2, 17 }, {3, 21 }, {4, 23 }, {5, 6 }, {6, 0 }, {7, 14 },
                        {8, 1 }, {9, 16 }, {10, 20 }, {11, 18 }, {12, 8 }, {13, 12 }, {14, 25 }, {15, 5 }, {16, 11 },
                        {17, 24 }, {18, 13 }, {19, 22 }, {20, 10 }, {21, 19 }, {22, 15 }, {23, 3 }, {24, 9 }, {25, 2 }};
                break;
            case 3:
                setup = new int[][] {{0, 8 }, {1, 12 }, {2, 4 }, {3, 19 }, {4, 2 }, {5, 6 }, {6, 5 }, {7, 17 },
                        {8, 0 }, {9, 24 }, {10, 18 }, {11, 16 }, {12, 1 }, {13, 25 }, {14, 23 }, {15, 22 }, {16, 11 },
                        {17, 7 }, {18, 10 }, {19, 3 }, {20, 21 }, {21, 20 }, {22, 15 }, {23, 14 }, {24, 9 }, {25, 13 }};
                break;
            case 4:
                setup = new int[][] {{0, 16 }, {1, 22 }, {2, 4 }, {3, 17 }, {4, 19 }, {5, 25 }, {6, 20 }, {7, 8 },
                        {8, 14 }, {9, 0 }, {10, 18 }, {11, 3 }, {12, 5 }, {13, 6 }, {14, 7 }, {15, 9 }, {16, 10 },
                        {17, 15 }, {18, 24 }, {19, 23 }, {20, 2 }, {21, 21 }, {22, 1 }, {23, 13 }, {24, 12 }, {25, 11 }};
                break;
        }
    }
    // funkcja szyfrowania litery, reverse w przypadku drogi powrotnej sygnalu
    int onSignal(int num, boolean reverse){
        if(reverse){
            for (int i[]:setup) {
                if(i[1]==num){
                    num = i[0]-position;
                    if(num<0) num+=26;
                    num = num%26;
                    break;
                }
            }
            return num;
        }
        else{
            num = (num+position)%26;
            return setup[num][1];
        }
    }

    public void setSetup(int[][] setup) {
        this.setup = setup;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int[][] getSetup() {
        return setup;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }
}