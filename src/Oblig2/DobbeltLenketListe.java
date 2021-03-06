package Oblig2;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;

    }
    //hjelpemetode

    private Node<T> finnNode (int indeks){
        Node<T> node ;

        if(indeks < antall/2){
            node = hode;
            for (int i = 0; i < indeks ; i++) {
                node = node.neste;
            }

        }
        else{
            node = hale;
            for (int i = antall-1; i > indeks; i--) {
                node = node.forrige;
            }
        }
        return node;

    }



    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a,"Tabellen a er null!");

        for(T verdi : a){

            if(verdi == null){

            }
            else if(tom()){
                Node<T> node = new Node<>(verdi,null,null);
                hode=hale=node;
                antall++;
            }
            else{
                Node<T> node = new Node<>(verdi,hale,null);
                hale.neste = node;
                hale = node;
                antall++;
            }
        }


    }
    private  static  void fratilKontroll(int antall, int fra, int til){
        if(fra < 0){
            throw new IndexOutOfBoundsException("fra ("+fra+") er negativ!");
        }
        if(til> antall){
            throw new IndexOutOfBoundsException("til("+til+" er større enn antall ("+antall+")");

        }
        if(fra > til){
            throw new IndexOutOfBoundsException("fra("+fra+"er større enn til ("+til+")");
        }

    }

    public Liste<T> subliste(int fra, int til){
        if(fra > til){
            throw new IllegalArgumentException("Fra kan ikke være større enn til");
        }

        DobbeltLenketListe<T> nyListe = new DobbeltLenketListe<>();

        for (int i = fra; i < til ; i++) {
            nyListe.leggInn(hent(i));

        }
        return nyListe;
    }

    @Override
    public int antall() {
        return antall;

    }

    @Override
    public boolean tom() {
        return (hode == null && hale == null && antall == 0); // hvis
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi,"Null verdier ikke tillatt");
        if(tom()){
          Node<T> node = new Node<>(verdi,null,null);
          hode = hale = node;
          antall++ ;

          return true;
        }
        else{
            hale.neste = new Node<>(verdi,hale,null);
            hale = hale.neste;
            antall++;
            endringer ++;

            return  true;

        }


    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi,"Ikke tillatt med null-verdi");
       if(indeks < 0){
           throw new IndexOutOfBoundsException("Indeks kan ikke være mindre enn null");
       }
       if(indeks > antall){
           throw new IndexOutOfBoundsException("Indeks kan ikke være større enn antall");
       }
       if(verdi == null){
           throw  new IndexOutOfBoundsException("Vedi kan ikke være lik null");
       }
       indeksKontroll(indeks,true);

       Node<T> node;

       if(antall == 0){
           node = new Node<>(verdi,null,null);
           hode = hale = node;
           endringer++;
           antall++;
       }
       else if(tom() || indeks >= antall){
           leggInn(verdi);
       }
       else if(0==indeks){
           node = new Node<T>(verdi,null,hode);
           hode.forrige=node;
           hode=node;
           endringer++;
           antall++;
       }
       else{
           Node<T> gammelNode = finnNode(indeks);
           node = new Node<T>(verdi,gammelNode.forrige,gammelNode);

          gammelNode.forrige = node;
           node.forrige.neste = node;
           endringer++;
           antall++;


       }



    }

    @Override
    public boolean inneholder(T verdi) {
       if(indeksTil(verdi) == -1){
           return false;
       }
       return true;
    }

    @Override
    public T hent(int indeks) {
       indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;

    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi == null){
            return -1;
        }
        Node<T> p = hode;
        for (int indeks = 0; indeks < antall ; indeks++) {
            if(p.verdi.equals(verdi)){
                return indeks;
            }
            p = p.neste;


        }
        return -1;

    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi,"Ikke tillatt med null-verdi");
        indeksKontroll(indeks,false);

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyverdi;

        endringer++;

        return gammelVerdi;

    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null){
            return  false;
        }
        Node<T> p = hode;
        while (p != null) {
            if (p.verdi.equals(verdi)) {
                break;
            }
            p = p.neste;
        }
        if(p == null){
            return false;
        }
        if(p == hode) {
            hode = hode.neste;

            if (hode != null) {
                hode.forrige = null;
            } else {
                hale = null;
            }
        }
            else if(p == hale){
                hale = hale.forrige;
                hale.neste = null;
            }
            else {
                p.forrige.neste = p.neste;
                p.neste.forrige = p.forrige;
            }
            p.verdi = null;
            p.forrige = p.neste;
            p.neste = null;

            antall--;
            endringer ++;
            return true;
        }



    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);
        if(tom()){
            return null;
        }

        Node<T> node;
        T temp;

        if(antall == 1){
            temp = hode.verdi;
            hode = hale = null;
            antall--;
            return temp;
        }
        else if(indeks == 0){
            node = hode;
            hode = hode.neste;
            hode.forrige = null;
        }
        else if(indeks == antall-1){
            node = hale;
            hale = hale.forrige;
            hale.neste = null;
        }
        else {
            Node<T> c = finnNode(indeks-1);
            node = c.neste;
            c.neste = c.neste.neste;
            c.neste.forrige = c;
        }
        antall--;
        endringer++;
        return node.verdi;
    }

    @Override
    public void nullstill() {
        for(Node<T> mid = hode; mid != null; mid = mid.neste){
            mid.verdi = null;
            mid.forrige = mid.neste;
            mid.neste = null;
        }
        hode = hale = null;
        antall = 0;
        endringer++; //34ms

      /* if(!tom()){
           hode.neste = null;
           hode.forrige = null;
           hode = hale = null;
           antall = 0;
           endringer++;
       } // 40ms

       */




    }

    @Override
    public String toString() {
     StringBuilder sb = new StringBuilder();
     sb.append("[");

     if(!tom()){
         Node<T> node = hode;
         sb.append(node.verdi);
         node = node.neste;

         while( node != null){
             sb.append(",").append(" ").append(node.verdi);
             node = node.neste;
         }

     }
        sb.append("]");
        return sb.toString();

    }

    public String omvendtString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if(!tom()){
            Node<T> node = hale;
            sb.append(node.verdi);
            node = hale.forrige;

            while( node != null){
                sb.append(",").append(" ").append(node.verdi);
                node = node.forrige;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
       indeksKontroll(indeks,false);
        DobbeltLenketListeIterator ny = new DobbeltLenketListeIterator(indeks);
        return ny;
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;
            fjernOK = false;
            iteratorendringer = endringer;

        }

        private DobbeltLenketListeIterator(int indeks){
            indeksKontroll(indeks,true);
            denne = hode;
            fjernOK = false;
            iteratorendringer =endringer;
            for (int i = 0; i <  indeks; i++) {
                next();

            }

        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("");
            }
            else if (!hasNext()) {
                throw new NoSuchElementException("");
            }

            fjernOK = true;
            T temp = denne.verdi;
            denne = denne.neste;

            return temp;

        }

        @Override
        public void remove(){
            if(!fjernOK){
                throw  new IllegalStateException("Ikke gyldig");
            }
             if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("ikke gyldig liste");
            }
            fjernOK = false;
            if(antall == 1){
                hode = hale = null;
            }
            else if(denne == null){
                hale = hale.forrige;
                hale.neste = null;
            }
            else if(denne.forrige == hode){
                hode = hode.neste;
                hode.forrige = null;
            }
            else{
                denne.forrige = denne.forrige.forrige;
                denne.forrige.neste = denne;
            }
            antall--;
            endringer++;
            iteratorendringer++;

        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        int teller = liste.antall();
        T temp;

        boolean endret = false;
        for (int i = 0; i < teller-1; i++) {
            if(c.compare(liste.hent(i),liste.hent(i+1))>0){
                endret = true;
                temp = liste.hent(i);

                liste.oppdater(i,liste.hent(i+1));
                liste.oppdater(i+1,temp);


            }

        }
        if(endret){
            sorter(liste,c);
        }

    }

} // class DobbeltLenketListe
