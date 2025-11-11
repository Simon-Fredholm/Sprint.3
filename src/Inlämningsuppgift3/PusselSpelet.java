package Inlämningsuppgift3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class PusselSpelet extends JFrame implements ActionListener {

    private final JButton[] knappar = new JButton[16]; //aray 16 knappar

    private final JButton startKnapp = new JButton("Starta spelet"); // Starta / spela igen

    private final ModellPussel spelLogik = new ModellPussel(); //hantera spelet / flyttar blandar, kontroll etc

    private boolean speletIgång = false; // igång/ej

    //konstruktor som bygger ett fönster med knappar etc
    public PusselSpelet() {

        setTitle("Pusselspelet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);// avsluta
        setLayout(new BorderLayout());// layout

        //skapa spelet 4x4 i ett rutnät
        JPanel rutNät = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            knappar[i] = new JButton(); //skapa knapparna
            knappar[i].setFont(new Font("TIMES", Font.BOLD, 25));//Utseende
            knappar[i].addActionListener(this::actionPerformed);// klicka på på knappar, implementerar action...
            rutNät.add(knappar[i]);// lägg till knappar i panel
        }

        add(rutNät, BorderLayout.CENTER); // i mitten


        startKnapp.addActionListener(e -> startaSpelet());// klicka så körs startaspelet metoden (LAMBDAAA

        add(startKnapp, BorderLayout.SOUTH);// ligger längst ner

        uppdateraKnappar();// icke shufflat

        setSize(600, 600); //storlek
        setVisible(true);// synligt
    }

    //starta / köra om
    private void startaSpelet() {
        spelLogik.blanda(); // blanda brickor
        speletIgång = true; // igång
        startKnapp.setText("Spela igen"); // ändra text startknapp
        uppdateraKnappar(); // blanda siffror
    }

    private void uppdateraKnappar() {String[] brickor = spelLogik.getBrickor(); // Metod för att visa rätt siffror från andra klass
        for (int i = 0; i < 16; i++) { //loopar
            knappar[i].setText(brickor[i]); // rätt siffra/tom ruta
        }
    }

    // hanterar knapptryck
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton klickad = (JButton) e.getSource(); // Vilken knapp som klickas
        int index = Arrays.asList(knappar).indexOf(klickad);// Gör om ti lista för att hitta klickad knapp
        if (spelLogik.flytta(index)) { // anropar metod flytta och ser om man kan flytta brickan

            uppdateraKnappar(); // Om möjligt så uppdateras

            // kontroll om man vunnit
            if (speletIgång && spelLogik.ärLöst()) {
                JOptionPane.showMessageDialog(this, "Du vann!");
                speletIgång = false; //stoppar
                startKnapp.setText("Starta spelet"); //ändrar knapptexten, man kan spela igen..
            }
        }
    }

    public static void main(String[] args) {new PusselSpelet();}

}
