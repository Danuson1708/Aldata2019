package Oblig1;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {
    private Oblig1() {

    }
    // Danuson vasantharajan s331362

    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
        if (a.length == 0) {
            throw new NoSuchElementException("Tabellen er tom");
        }
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                int tmp = a[i - 1]; //holder av midlertidig
                a[i - 1] = a[i]; //
                a[i] = tmp;
            }
        }

        return a[a.length - 1];


    }

    public static int ombyttinger(int[] a) {
        if (a.length == 0) {
            throw new NoSuchElementException("Tabellen er tom");
        }
        int bytter = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                int tmp = a[i - 1];
                a[i - 1] = a[i];
                a[i] = tmp;
                bytter++;

            }
        }

        return bytter;
        /// oppgave 1 ferdig /////
    }

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {

        for (int l = 1; l < a.length; l++) {
            if (a[l - 1] > a[l]) {
                throw new IllegalStateException("den er ikke sortert");

            }
        }
        int antallSort = 1;

        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] < a[i]) {
                antallSort++;
            }
        }
        if (a.length == 0) {
            return 0;
        }
        return antallSort;

    }


    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {

        if (a.length == 0) {
            return 0;
        }
        if (a.length == 1) {
            return 1;
        }
        int antallSort = 0;
        int maksTall = a[0];

        for (int i = 0; i < a.length; i++) {
            if (maksTall < a[i]) {
                maksTall = a[i];

            }

        }
        for (int j = 0; j <= maksTall; j++) {
            for (int l = 0; l < a.length; l++) {
                if (a[l] == j) {
                    antallSort++;
                    break;
                }

            }

        }
        return antallSort;
        //oppgave 3 er ferdig


    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a) {
        int teller = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                int midlertidig = a[teller]; // holder av midlertidig variabel
                a[teller] = a[i]; //  ny posisjon
                a[i] = midlertidig; // ny variabel
                teller++;
            }

        }
        if (teller == 0 || teller == a.length) {
            Arrays.sort(a);
        } else {
            Arrays.sort(a, 0, teller);
            Arrays.sort(a, teller, a.length);
        }

        //oppgave 4 ferdig

    }


    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        if (!(a.length == 0)) {
            char[] b = new char[a.length];
            b[0] = a[a.length - 1];

            for (int i = 1; i < a.length; i++) {
                b[i] = a[i - 1];

            }
            for (int j = 0; j < a.length; j++) {
                a[j] = b[j];

            }
        }

    }
    // Ferdig med oppgave 5


    ///// Oppgave 6 //////////////////////////////////////
    public static int gcd(int a, int k) {
        return k == 0 ? a : gcd(k, a % k);
    }

    public static void rotasjon(char[] a, int k) {
        int n = a.length;
        if (n < 2) return;

        if ((k %= n) < 0) k += n;

        int s = gcd(n, k);

        for (int i = 0; i < s; i++) {
            char verdi = a[i];

            for (int j = i - k, m = i; j != i; j -= k) {
                if (j < 0) {
                    j += n;

                }
                a[m] = a[j];
                m = j;
            }

            a[i + k] = verdi;

        }
    }
    //Oppgave 6 ferdig


    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        char[] txt = new char[s.length() + t.length()];
        char[] aS = s.toCharArray();
        char[] aT = t.toCharArray();

        int i = 0, l = 0, j = 0;

        for (int n = 0; n < Math.max(s.length(), t.length()); n++) {
            if (n < s.length()) {
                txt[j++] = aS[i++];
            }
            if (l < t.length()) {
                txt[j++] = aT[l++];
            }
        }

        return String.valueOf(txt);
    }

    public static String flett(String... s) {

        int l = 0;

        for (String ukjent : s) {
            l += ukjent.length();
        }

        int[] ny = new int[s.length];
        String ut = "";

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < s.length; j++) {
                if (ny[j] < s[j].length()) {
                    ut += s[j].toCharArray()[ny[j]];
                    ny[j]++;
                }
            }
        }

        return ut;

    }
    ///oppgave 7b fikset

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        int[] indeks = new int[a.length];
        int[] hjelpeTabell = a.clone();
        Arrays.sort(hjelpeTabell);

        for (int i = 0; i < hjelpeTabell.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (hjelpeTabell[i] == a[j]) {
                    indeks[i] = j;
                    break;
                }

            }

        }
        return indeks;
    }

        //oppgave 8 ferdig




     ///// Oppgave 9 //////////////////////////////////////
   public static int[] tredjeMin(int[] a) {

        int lengde = a.length;
        if( lengde < 3){
            throw new NoSuchElementException("a.lentgth("+ lengde + ") < 3!");
        }
        int [] indeks = indekssortering(Arrays.copyOf(a,3)); // bruker indekssorteringsklassen

       int m = indeks[0];
       int nm = indeks[1];
       int onm = indeks[2];

       int maksverdi = a[m];
       int nestMaksverdi = a[nm];
       int tredjeMaksverdi = a[onm];

       for (int i = 3; i < lengde ; i++) {
           if(a[i] < tredjeMaksverdi)
           {
               if(a[i] < nestMaksverdi)
               {
                   if(a[i] < maksverdi)
                   {
                       onm = nm;
                       tredjeMaksverdi = nestMaksverdi;

                       nm = m;
                       nestMaksverdi = maksverdi;

                       m = i;
                       maksverdi = a[m];

                   }
                   else{
                       onm = nm;
                       tredjeMaksverdi = nestMaksverdi;

                       nm = i;
                       nestMaksverdi = a[nm];

                   }

               }

               else{
                   onm = i;
                   tredjeMaksverdi = a[onm];
               }
           }

       }

         return  new int[]{m, nm, onm};
        }

        //Oppgave 9 er ferdig


    ///// Oppgave 10 //////////////////////////////////////
     public static int bokstavNr(char bokstav) {
        if(bokstav <='Z'){
            return bokstav - 'A';
        }
        else if(bokstav == 'Å'){
            return 28;
         }
        else if( bokstav == 'Ø'){
            return 27;
         }
        else{
            return 26;
         }

    }

    public static boolean inneholdt(String a, String b) {
      if(a.length() > b.length()){
          return false;
      }
      int [] boks = new int [29];

        for (int i = 0; i < a.length() ; i++) {
            boks[bokstavNr(a.charAt(i))]++;
        }
        for (int i = 0; i <b.length() ; i++) {
            boks[bokstavNr(b.charAt(i))]--;
        }
        for (int i = 0; i <boks.length ; i++) {
            if(boks[i] > 0){
                return false;
            }

        }
        return true;
    }
    //oppgave 10 er ferdig
}
