package com.example.batak;


public class Kart {
    String sembol;
    int sayı;

    public Kart(String _sembol, int _sayı) {
        sembol = _sembol;
        sayı = _sayı;
    }

    public String toString() {
        switch (sayı) {
            case 2:
                return sembol + "_" + sayı;
            case 3:
                return sembol + "_" + sayı;
            case 4:
                return sembol + "_" + sayı;
            case 5:
                return sembol + "_" + sayı;
            case 6:
                return sembol + "_" + sayı;
            case 7:
                return sembol + "_" + sayı;
            case 8:
                return sembol + "_" + sayı;
            case 9:
                return sembol + "_" + sayı;
            case 10:
                return sembol + "_" + sayı;
            case 11:
                return sembol + "_vale";
            case 12:
                return sembol + "_kiz";
            case 13:
                return sembol + "_papaz";
            case 14:
                return sembol + "_as";
        }
        return null;
    }
}