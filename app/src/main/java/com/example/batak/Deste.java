package com.example.batak;

import java.util.ArrayList;
import java.util.Collections;

public class Deste {
    String[] semboller = {"kupa","sinek","maca","karo"};
    ArrayList<Kart> deste = new ArrayList<Kart>();
    Kart yeniKart;

    public Deste() {
        for(int i=0; i<semboller.length; i++) {
            for(int j=2; j<=14; j++) {
                yeniKart = new Kart(semboller[i], j);
                deste.add(yeniKart);
            }
        }
        Collections.shuffle(deste);
    }
}
