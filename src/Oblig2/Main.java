package Oblig2;

import java.sql.SQLOutput;

public class Main {
    public static void main (String [] args){

    DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(new String[]
            {"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});
    liste.fjernHvis(navn -> navn.charAt(0) == 'B');

        System.out.println(liste +" "+liste.omvendtString());



    }
}
