package Inlämningsuppgift3;

import java.util.*;

public class ModellPussel {


    private String[] brickor = new String[16];  //aray brickor och tom ruta


    private int tomtIndex; // index där den tomma rutan är


    public ModellPussel() {omStart(); // Sätts i startläge (omstart)
    }

    // metod för återställa pusslet
    public void omStart() {
        for (int i = 0; i < 15; i++) brickor[i] = String.valueOf(i + 1);
        brickor[15] = "";
        tomtIndex = 15; // sista rutan ska va tom
    }

    // blanda brickorna
    public void blanda()
    {List<String> lista = new ArrayList<>(Arrays.asList(brickor)); // får en lista
        Collections.shuffle(lista);// shuffla listan
        brickor = lista.toArray(new String[0]);// tillbaka till array
        tomtIndex = Arrays.asList(brickor).indexOf("");// uppdatera tomma rutan position
    }


    public boolean flytta(int index) {
        if (kanFlyttas(index)) { // kollar om tom ruta är bredvid
            brickor[tomtIndex] = brickor[index]; // byter plats med tom ruta
            brickor[index] = ""; // gamla blir tom
            tomtIndex = index; // håller koll på den tomma rutan

            return true; // lyckas
        }
        return false; // lyckas inte
    }


    private boolean kanFlyttas(int index) { // metod för att kolla om brickan ligger bredvid tom ruta

        int rad = index / 4, kol = index % 4;
        int tomRad = tomtIndex / 4, tomKol = tomtIndex % 4;
        return (Math.abs(rad - tomRad) + Math.abs(kol - tomKol)) == 1;
    }


    public boolean ärLöst() { // metod för om pussel är löst

        for (int i = 0; i < 15; i++) { //loop
            if (!brickor[i].equals(String.valueOf(i + 1))) return false; // inte iordning = false
        }

        return brickor[15].equals(""); // sista måste vara tom
    }


    public String[] getBrickor() { // ger tillgång till nuvarande brickordning, returnerar en array..
        return brickor;
    }
}

