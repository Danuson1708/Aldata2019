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
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    @Override
    public T fjern(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public void nullstill() {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            throw new NotImplementedException();
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext(){
            throw new NotImplementedException();
        }

        @Override
        public T next(){
            throw new NotImplementedException();
        }

        @Override
        public void remove(){
            throw new NotImplementedException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe
