package model;

import java.util.ArrayList;

public class Enigma {
    private String template = "qwertyuiopasdfghjklzxcvbnm"; // pomocniczy string do zmiany char na int i odwronie
    private Rotor right;
    private Rotor mid;
    private Rotor left;
    private ReverseRotor rr;
    public InputBoard settings;
    //private ArrayList<Integer> rotorsTaken = new ArrayList<>();

    public Enigma(){
        right = new Rotor(0, 0);
        mid = new Rotor(1, 0);
        left = new Rotor(2, 0);
        rr = new ReverseRotor();
        settings = new InputBoard();
        //rotorsTaken.add(4);
        //rotorsTaken.add(1);
        //rotorsTaken.add(3);
    }

//    --------------------------------------------------------------------------
//    !!!                 NAJWAZNIEJSZA FUNKCJA W MODELU                     !!!
//    Jest wywolywana co kazde nacisniecie litery i odpowiada za szyfrowanie jej.
//    Przechodzi kolejno przez polaczenie kablowe, 3 rotory, rotor zawracajacy
//    z powrotem 3 rotory i znowu polaczenie kablowe
//    --------------------------------------------------------------------------
    public char run(char c){
        c = Character.toLowerCase(c);
        int charNum = template.indexOf(c);
        charNum = settings.onSignal(charNum);
        charNum = right.onSignal(charNum,false);
        charNum = mid.onSignal(charNum,false);
        charNum = left.onSignal(charNum,false);
        charNum = rr.onSignal(charNum);
        charNum = left.onSignal(charNum,true);
        charNum = mid.onSignal(charNum,true);
        charNum = right.onSignal(charNum,true);
        charNum = settings.onSignal(charNum);

        rotate();

        return template.charAt(charNum);

    }

    //funkcja obracajaca rotor po kazdym nacisnieciu litery
    // po pelnym obrocie pierwszego, obrot o 1 drugiego, analogicznie z 2 i 3
    public void rotate(){
        right.setPosition((this.right.getPosition()+1));
        if(right.getPosition()==26){
            right.setPosition(0);
            mid.setPosition(this.mid.getPosition()+1);
            if(mid.getPosition()==26){
                mid.setPosition(0);
                left.setPosition((this.mid.getPosition()+1)%26);
            }
        }
    }

    public void setRight(int number, int position) {
        this.right = new Rotor(number, position);
    }

    public void setMid(int number, int position) {
        this.mid = new Rotor(number, position);
    }

    public void setLeft(int number, int position) {
        this.left = new Rotor(number, position);
    }

    public Rotor getRight() {
        return right;
    }

    public Rotor getMid() {
        return mid;
    }

    public Rotor getLeft() {
        return left;
    }


}
