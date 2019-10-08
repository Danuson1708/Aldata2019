package Oblig2;

import java.sql.SQLOutput;

public class Main {
    public static void main (String [] args){

        String [] navn ={"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);

        liste.forEach(s -> System.out.println(s+" "));
        System.out.println();
        for(String s : liste){
            System.out.println(s+" ");
        }




    }
}
