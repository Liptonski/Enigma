package model;

// polaczenie 2 liter, in, out to tylko moje nazwy, nie ma znaczenia kolejnosc sygnalu
public class Connection {
    private int in;
    private int out;

    public Connection(int in, int out){
        this.in=in;
        this.out=out;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getIn() {
        return in;
    }

    public int getOut() {
        return out;
    }
}
