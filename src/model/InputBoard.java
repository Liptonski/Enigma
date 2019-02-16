package model;

import java.util.ArrayList;

public class InputBoard {
    private Connection [] wires = new Connection[10]; // tablica polaczen kabli
    //private ArrayList<Integer> occupied = new ArrayList<>();
    private String template = "qwertyuiopasdfghjklzxcvbnm"; // pomocniczy string słuzacy do zamiany intow na chary i odwrotnie

    // na poczatku ustawiam polaczenia po kolei, 1-2, 3-4 itd
    public InputBoard(){
        int j = 0;
        for (int i = 0; i< wires.length; i++) {
            wires[i] = new Connection(j,j+1);
            //occupied.add(j);
            j += 2;
        }
    }

    // funkcja zwracajaca odpowiednik z pary dostarczonej litery
    int onSignal(int num) {
        for ( Connection i : wires) {
            if (i.getIn() == num) {
                return i.getOut();
            } else if (i.getOut() == num) {
                return i.getIn();
            }
        }

        return num;
    }

    // zmiana polaczen kabli
    public void changeConnection(int index, char a, char b){
        int numa = template.indexOf(a);
        int numb = template.indexOf(b);
        wires[index].setIn(numa);
        wires[index].setOut(numb);
    }

    //public ArrayList<Integer> getOccupied() {
     //   return occupied;
    //}

    public Connection[] getWires() {
        return wires;
    }
}
