package com.example.batak;


import java.util.ArrayList;

public class Oyuncu {
    ArrayList<Kart> el = new ArrayList<Kart>();
    String ad;
    int puan;

    public Oyuncu(String _ad) {
        ad = _ad;
    }

    public void elYarat(Deste deste) {
        for(int i=12; i>=0; i--) {
            el.add(deste.deste.get(i));
            deste.deste.remove(i);
        }
    }

    public Kart kartGetir(int pozisyon) {
        return el.get(pozisyon);
    }
}