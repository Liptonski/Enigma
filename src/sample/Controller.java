package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Controller implements EventHandler<KeyEvent> {
    private Enigma eng;
    private String template = "qwertyuiopasdfghjklzxcvbnm";
    private String help;
    @FXML
    private Label q, w, e, r, t, z, u, i, o, p, a, s, d, f, g, h, j, k, l, y, x, c, v, b, n, m;
    private ArrayList<Label> letters = new ArrayList<>();
    private ArrayList<ChoiceBox<Character>> choices = new ArrayList<>();
    @FXML
    private Text text;
    private StringBuilder crypted = new StringBuilder();

    @FXML
    ChoiceBox<String> r1,r2,r3,
    num_r1, num_r2, num_r3;
    @FXML
    ChoiceBox<Character> plug_11, plug_12, plug_21, plug_22, plug_31, plug_32, plug_41, plug_42, plug_51, plug_52, plug_61, plug_62,
            plug_71, plug_72, plug_81, plug_82, plug_91, plug_92, plug_101, plug_102;

    public Controller(){
        System.out.println("controler");
    }
    @FXML
    void initialize(){
        eng = new Enigma();
        Collections.addAll(choices, plug_11, plug_12, plug_21, plug_22, plug_31, plug_32, plug_41, plug_42, plug_51, plug_52, plug_61, plug_62,
                plug_71, plug_72, plug_81, plug_82, plug_91, plug_92, plug_101, plug_102);
        initChoices();

        Collections.addAll(letters, q, w, e, r, t, y, u, i, o, p, a, s, d, f, g, h, j, k, l, z, x, c, v, b, n, m);

        r1.getItems().add("rotor1");
        r1.getItems().add("rotor2");
        r1.getItems().add("rotor3");
        r1.getItems().add("rotor4");
        r1.getItems().add("rotor5");

        r2.getItems().add("rotor1");
        r2.getItems().add("rotor2");
        r2.getItems().add("rotor3");
        r2.getItems().add("rotor4");
        r2.getItems().add("rotor5");

        r3.getItems().add("rotor1");
        r3.getItems().add("rotor2");
        r3.getItems().add("rotor3");
        r3.getItems().add("rotor4");
        r3.getItems().add("rotor5");

        r1.setValue("rotor1");
        r2.setValue("rotor2");
        r3.setValue("rotor3");

        for(int i=0;i<26;i++){
            num_r1.getItems().add(Integer.toString(i+1));
            num_r2.getItems().add(Integer.toString(i+1));
            num_r3.getItems().add(Integer.toString(i+1));
        }

        num_r1.setValue(Integer.toString (eng.getRight().getPosition()+1));
        num_r2.setValue(Integer.toString(eng.getMid().getPosition()+1));
        num_r3.setValue(Integer.toString(eng.getLeft().getPosition()+1));

        text.setText(crypted.toString());
    }
    @Override
    public void handle(KeyEvent e){
        if(e.getEventType() == KeyEvent.KEY_PRESSED) {
            String tmp = Character.toString(eng.run(e.getCode().getChar().charAt(0)));
            if(template.contains(tmp)) {
                letters.get(template.indexOf(tmp)).setStyle("-fx-background-color: #feff00");
                crypted.append(tmp);
                text.setText(crypted.toString());
                help=tmp;
                num_r1.setValue(Integer.toString (eng.getRight().getPosition()+1));
                num_r2.setValue(Integer.toString(eng.getMid().getPosition()+1));
                num_r3.setValue(Integer.toString(eng.getLeft().getPosition()+1));
            }
        }
        else if(e.getEventType() == KeyEvent.KEY_RELEASED){
            letters.get(template.indexOf(help)).setStyle("-fx-background-color: #000000");
        }
    }

    public void directoryChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Wybierz plik do zapisu");
        File selectedFile = null;
        while(selectedFile== null){
            selectedFile = chooser.showSaveDialog(null);
        }

        File file = new File(String.valueOf(selectedFile));
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outFile.println(text.getText());
        outFile.close();
    }

    @FXML
    public void onClick_1(){
        eng.setRight(Character.getNumericValue(r1.getValue().charAt(5))-1, Integer.parseInt(num_r1.getValue())-1);
        System.out.println(r1.getValue().charAt(5));
        System.out.println(num_r1.getValue());
    }
    @FXML
    public void onClick_2(){
        eng.setMid(Character.getNumericValue(r2.getValue().charAt(5))-1, Integer.parseInt(num_r2.getValue())-1);
    }
    @FXML
    public void onClick_3(){
        eng.setLeft(Character.getNumericValue(r3.getValue().charAt(5))-1, Integer.parseInt(num_r3.getValue())-1);
    }

    public void initChoices(){
        for(ChoiceBox<Character> c: choices){
            c.getItems().addAll(' ', 'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m');
        }

        plug_11.setValue(template.charAt(eng.settings.getWires()[0].getIn()));
        plug_12.setValue(template.charAt(eng.settings.getWires()[0].getOut()));

        plug_21.setValue(template.charAt(eng.settings.getWires()[1].getIn()));
        plug_22.setValue(template.charAt(eng.settings.getWires()[1].getOut()));

        plug_31.setValue(template.charAt(eng.settings.getWires()[2].getIn()));
        plug_32.setValue(template.charAt(eng.settings.getWires()[2].getOut()));

        plug_41.setValue(template.charAt(eng.settings.getWires()[3].getIn()));
        plug_42.setValue(template.charAt(eng.settings.getWires()[3].getOut()));

        plug_51.setValue(template.charAt(eng.settings.getWires()[4].getIn()));
        plug_52.setValue(template.charAt(eng.settings.getWires()[4].getOut()));

        plug_61.setValue(template.charAt(eng.settings.getWires()[5].getIn()));
        plug_62.setValue(template.charAt(eng.settings.getWires()[5].getOut()));

        plug_71.setValue(template.charAt(eng.settings.getWires()[6].getIn()));
        plug_72.setValue(template.charAt(eng.settings.getWires()[6].getOut()));

        plug_81.setValue(template.charAt(eng.settings.getWires()[7].getIn()));
        plug_82.setValue(template.charAt(eng.settings.getWires()[7].getOut()));

        plug_91.setValue(template.charAt(eng.settings.getWires()[8].getIn()));
        plug_92.setValue(template.charAt(eng.settings.getWires()[8].getOut()));

        plug_101.setValue(template.charAt(eng.settings.getWires()[9].getIn()));
        plug_102.setValue(template.charAt(eng.settings.getWires()[9].getOut()));
    }

    // Troche bezsens z tymi funkcjami ale nie mam pomyslu jak to zrobic

    public void connection1(){
        char in = plug_11.getValue();
        char out = plug_12.getValue();

        if(in == ' ' || out == ' ' || in==out){
            plug_11.setValue(' ');
            plug_12.setValue(' ');
        }
        for(ChoiceBox<Character> c : choices){
            if(c.getValue()==in || c.getValue()==out){
                c.setValue(' ');
            }
        }
        plug_11.setValue(in);
        plug_12.setValue(out);
        eng.settings.changeConnection(0, in, out);
    }

    public void connection2(){
        char in = plug_21.getValue();
        char out = plug_22.getValue();

        if(in == ' ' || out == ' ' || in==out){
            plug_21.setValue(' ');
            plug_22.setValue(' ');
        }
        for(ChoiceBox<Character> c : choices){
            if(c.getValue()==in || c.getValue()==out){
                c.setValue(' ');
            }
        }
        plug_21.setValue(in);
        plug_22.setValue(out);
        eng.settings.changeConnection(1, in, out);
    }

    public void connection3(){
        char in = plug_31.getValue();
        char out = plug_32.getValue();

        if(in == ' ' || out == ' ' || in==out){
            plug_31.setValue(' ');
            plug_32.setValue(' ');
        }
        for(ChoiceBox<Character> c : choices){
            if(c.getValue()==in || c.getValue()==out){
                c.setValue(' ');
            }
        }
        plug_31.setValue(in);
        plug_32.setValue(out);
        eng.settings.changeConnection(2, in, out);
    }

    public void connection4() {
        char in = plug_41.getValue();
        char out = plug_42.getValue();

        if (in == ' ' || out == ' ' || in == out) {
            plug_41.setValue(' ');
            plug_42.setValue(' ');
        }
        for (ChoiceBox<Character> c : choices) {
            if (c.getValue() == in || c.getValue() == out) {
                c.setValue(' ');
            }
        }
        plug_41.setValue(in);
        plug_42.setValue(out);
        eng.settings.changeConnection(3, in, out);
    }

    public void connection5() {
        char in = plug_51.getValue();
        char out = plug_52.getValue();

        if (in == ' ' || out == ' ' || in == out) {
            plug_51.setValue(' ');
            plug_52.setValue(' ');
        }
        for (ChoiceBox<Character> c : choices) {
            if (c.getValue() == in || c.getValue() == out) {
                c.setValue(' ');
            }
        }
        plug_51.setValue(in);
        plug_52.setValue(out);
        eng.settings.changeConnection(4, in, out);
    }

    public void connection6() {
        char in = plug_61.getValue();
        char out = plug_62.getValue();

        if (in == ' ' || out == ' ' || in == out) {
            plug_61.setValue(' ');
            plug_62.setValue(' ');
        }
        for (ChoiceBox<Character> c : choices) {
            if (c.getValue() == in || c.getValue() == out) {
                c.setValue(' ');
            }
        }
        plug_61.setValue(in);
        plug_62.setValue(out);
        eng.settings.changeConnection(5, in, out);
    }

    public void connection7() {
        char in = plug_71.getValue();
        char out = plug_72.getValue();

        if (in == ' ' || out == ' ' || in == out) {
            plug_71.setValue(' ');
            plug_72.setValue(' ');
        }
        for (ChoiceBox<Character> c : choices) {
            if (c.getValue() == in || c.getValue() == out) {
                c.setValue(' ');
            }
        }
        plug_71.setValue(in);
        plug_72.setValue(out);
        eng.settings.changeConnection(6, in, out);
    }

    public void connection8() {
        char in = plug_81.getValue();
        char out = plug_82.getValue();

        if (in == ' ' || out == ' ' || in == out) {
            plug_81.setValue(' ');
            plug_82.setValue(' ');
        }
        for (ChoiceBox<Character> c : choices) {
            if (c.getValue() == in || c.getValue() == out) {
                c.setValue(' ');
            }
        }
        plug_81.setValue(in);
        plug_82.setValue(out);
        eng.settings.changeConnection(7, in, out);
    }

    public void connection9() {
        char in = plug_91.getValue();
        char out = plug_92.getValue();

        if (in == ' ' || out == ' ' || in == out) {
            plug_91.setValue(' ');
            plug_92.setValue(' ');
        }
        for (ChoiceBox<Character> c : choices) {
            if (c.getValue() == in || c.getValue() == out) {
                c.setValue(' ');
            }
        }
        plug_91.setValue(in);
        plug_92.setValue(out);
        eng.settings.changeConnection(8, in, out);
    }

    public void connection10() {
        char in = plug_101.getValue();
        char out = plug_102.getValue();

        if (in == ' ' || out == ' ' || in == out) {
            plug_101.setValue(' ');
            plug_102.setValue(' ');
        }
        for (ChoiceBox<Character> c : choices) {
            if (c.getValue() == in || c.getValue() == out) {
                c.setValue(' ');
            }
        }
        plug_101.setValue(in);
        plug_102.setValue(out);
        eng.settings.changeConnection(9, in, out);
    }
}
