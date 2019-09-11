package uke2;

import java.util.Arrays;
import java.util.Random;

public class test {
    //oppgave 1.2.1
    public static int minst(int [] a,int fra, int til){
        if(fra<0 || til>a.length || fra>=til)
            throw new IllegalArgumentException("ugyldig intervall");

    int m = fra;     //indeks til minste verdi i a[fra:til>
    int minsteverdi  = a[fra];  //minste verdi i a[fra : til>

    for(int i = fra + 1; i< til; i++)
        if(a[i] < minsteverdi){
            m = i;              //indeks til minsteveri oppdaters
            minsteverdi = a[m];  //minste verdi oppdaters

        }
    return m;  //posisjonen til minste verdi

    }
    public static int minst(int [] a) // bruker hele tabellen
    {
        return minst(a,0,a.length); // kaller på metode fra toppen
    }

    // Oppgave 1.2.5
     /*   en for (a : x) løkke kjører gjennom en hel fo løkke.
     eks. String [] klasse = {data,matte,norsk,naturfag}
          for ( String ny : klasse)
          System.out.println( ny + " ");
          skriver da ut "data,matte,norsk,naturfag"
       */

     // oppgave 1.2.2 a

    public static class Tabell{
        private Tabell(){} // hindrer instansering

        public static void bytt( int [] a, int i, int j){
           int temp = a[i]; a[i] = a[j]; a[j] = temp;
        }

        public static int[] randPerm( int n) // effektiv metode
        {
            Random r = new Random(); // en randomgerator
            int [] a = new int[n]; // tabell med n-plasser

            Arrays.setAll(a, i -> i + 1); // legger  inn talll 2,3,...,n

            for (int k = n - 1; k > 0; k--) //løkke som går n-1 ganger
            {
                int i = r.nextInt(k+1); //tilfeldig tall fra til k
                bytt(a,k,i);  // bytter om  plasser
            }
            return a; // permutasjon retuneres

        }
        public static void randPerm(int [] a) //stokker om
        {
           Random r = new Random();
           for (int k = a.length-1; k > 0; k--){
               int i = r.nextInt(k+1); // tilfeldig tall [0,k]
               bytt(a,k,i);
           }
        }
         public static int max(int []a, int fra, int til)
         {
            if(fra < 0 || til > a.length || fra >= til){
                throw new IllegalArgumentException("Ukyldig intervall");
         }
            int m = fra; //indeks til største verdi
             int maxverdi = a[fra]; // største verdi i a[fra:til>

             for ( int i = fra + 1; i < til; i++){
                if(a[i]> maxverdi){
                    m = i;              // indeks til største verdi update
                    maxverdi = a[m];    // største verdi update
                }
             }
             return m; // posisjonen til største verdi
         }
         public static int max (int [] a) // kaller på tabbel
         {
            return  max (a,0,a.length); // kaller på metode
         }

         public static int min( int [] a, int fra, int til)
         {
             if (fra < 0 || til > a.length || fra >= til){
                 throw new IllegalArgumentException("ugyldig intervall");
             }
             int m = fra;
             int minverdi = a[m];
             for(int i = fra + 1; i < til; i++){
                 if(a[i] < minverdi){
                     m = i;
                     minverdi = a[m];
                 }
             }
             return  m;
         }
         public static  int min(int [] a){
            return min(a,0,a.length);
         }

    } // class Tabell

    // Oppgave 1.2.2 c

    public static void bytt(char [] c, int i, int j){
        char para = c[i]; c[i] =c[j]; c[j] = para;
    }

    //oppgave 1.2.2 d

    public static void skriv (int[] a, int i, int j ){

    }




}
