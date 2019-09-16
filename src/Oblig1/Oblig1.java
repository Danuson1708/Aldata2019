package Oblig1;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.NoSuchElementException;

public class Oblig1 {
    private Oblig1(){

    }
    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
        if(a.length == 0){
            throw new NoSuchElementException("Tabellen er tom");
        }
        for (int i = 1; i < a.length ; i++) {
            if(a[i-1] > a[i]){
                int tmp = a[i-1];
                a[i-1] = a[i];
                a[i] = tmp;
            }
        }

        return a[a.length-1];


    }

    public static int ombyttinger(int[] a) {
        if(a.length == 0){
        throw new NoSuchElementException("Tabellen er tom");
        }
        int bytter = 0;
        for(int i = 1; i < a.length; i++){
            if(a[i-1] > a[i]){
                int tmp = a[i-1];
                a[i-1] = a[i];
                a[i] = tmp;
                bytter++;

            }
        }

        return bytter;
        /// oppgave 1 ferdig /////
    }

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {

        for(int l = 1; l < a.length; l++){
            if(a[l-1] > a[l]){
                throw new IllegalStateException("den er ikke sortert");

            }
        }
        int antallSort = 1;

        for(int i = 1; i < a.length; i++){
            if(a[i-1] < a[i]){
                antallSort++;
            }
        }
       if(a.length == 0){
           return 0;
       }
       return antallSort;

    }


    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {

        if( a.length == 0){
            return  0;
        }
        if (a.length == 1){
            return 1;
        }
        int antallSort = 0;
        int maksTall=a[0];

        for (int i = 0; i <a.length ; i++) {
            if(maksTall < a[i]){
                maksTall = a[i];

            }

        }
        for (int j = 0; j <= maksTall; j++) {
            for (int l = 0; l < a.length; l++) {
                if (a[l] == j ){
                    antallSort++;
                    break;
                }

            }

        }
        return antallSort;



    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a) {
        throw new NotImplementedException();
    }

    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        throw new NotImplementedException();
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        throw new NotImplementedException();
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        throw new NotImplementedException();
    }

    /// 7b)
    public static String flett(String... s) {
        throw new NotImplementedException();
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        throw new NotImplementedException();
    }


    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        throw new NotImplementedException();
    }

    ///// Oppgave 10 //////////////////////////////////////
    public static int bokstavNr(char bokstav) {
        throw new NotImplementedException();
    }

    public static boolean inneholdt(String a, String b) {
        throw new NotImplementedException();
    }


}
