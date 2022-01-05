package com.example.batak;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    ImageView card5;
    ImageView card6;
    ImageView card7;
    ImageView card8;
    ImageView card9;
    ImageView card10;
    ImageView card11;
    ImageView card12;
    ImageView card13;
    ArrayList<ImageView> cards;

    TextView kozText1;
    TextView kozText2;
    TextView kozText3;
    TextView kozText4;

    ImageView middleCard1;
    ImageView middleCard2;
    ImageView middleCard3;
    ImageView middleCard4;
    ArrayList<ImageView> middleCards;


    Oyuncu p1;
    Oyuncu p2;
    Oyuncu p3;
    Oyuncu p4;
    ArrayList<Oyuncu> oyuncular;

    ArrayList<Kart> oynananKartlar;

    Oyuncu turKimde;

    Deste deste;

    String koz;

    Oyuncu elKazanan;
    Oyuncu oyunKazanan;

    int turSayısı;

    ArrayList<Kart> oynanabilirKartlar;
    ArrayList<Kart> oynananKozKartlar;

    String oynananSembol;

    boolean kozOynanabilirmi;

    TextView sonucText;
    Button restartButton;
    TextView secilenKozText;

    boolean oynananSembolVarmı;
    Kart oynanan;

    TextView p1PuanText, p2PuanText, p3PuanText, p4PuanText;
    ArrayList<TextView> puanTextler = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        card10 = findViewById(R.id.card10);
        card11 = findViewById(R.id.card11);
        card12 = findViewById(R.id.card12);
        card13 = findViewById(R.id.card13);

        cards = new ArrayList<ImageView>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
        cards.add(card10);
        cards.add(card11);
        cards.add(card12);
        cards.add(card13);

        kozText1 = findViewById(R.id.macaText);
        kozText2 = findViewById(R.id.kupaText);
        kozText3 = findViewById(R.id.sinekText);
        kozText4 = findViewById(R.id.karoText);

        middleCard1 = findViewById(R.id.middleCard1);
        middleCard2 = findViewById(R.id.middleCard2);
        middleCard3 = findViewById(R.id.middleCard3);
        middleCard4 = findViewById(R.id.middleCard4);

        p1PuanText = findViewById(R.id.p1PuanText);
        p2PuanText = findViewById(R.id.p2PuanText);
        p3PuanText = findViewById(R.id.p3PuanText);
        p4PuanText = findViewById(R.id.p4PuanText);

        puanTextler.add(p1PuanText);
        puanTextler.add(p2PuanText);
        puanTextler.add(p3PuanText);
        puanTextler.add(p4PuanText);

        sonucText = findViewById(R.id.sonucText);
        sonucText.setVisibility(View.INVISIBLE);
        restartButton = findViewById(R.id.restartButton);
        restartButton.setVisibility(View.INVISIBLE);
        secilenKozText = findViewById(R.id.kozText);
        secilenKozText.setVisibility(View.INVISIBLE);

        middleCards = new ArrayList<ImageView>();
        middleCards.add(middleCard1);
        middleCards.add(middleCard2);
        middleCards.add(middleCard3);
        middleCards.add(middleCard4);

        oyuncular = new ArrayList<Oyuncu>();

        oynananKartlar = new ArrayList<Kart>();

        oynananKozKartlar = new ArrayList<Kart>();

        turSayısı = 0;
        oynanabilirKartlar = new ArrayList<Kart>();

        kozOynanabilirmi = false;

        oyunuBaşlat();
    }

    private void kartGoster(Kart kart, ImageView kartResim){
        AssetManager assets = getAssets();
        try{
            InputStream resim = assets.open(kart.toString() + ".png");
            Drawable flag = Drawable.createFromStream(resim, kart.toString());
            kartResim.setImageDrawable(flag);
        }
        catch (IOException e) {
        }
    }

    public void oyunuBaşlat() {
        deste = new Deste();

        oyuncularıHazırla();

        turKimde = oyuncular.get(0);
    }

    public void oyuncularıHazırla() {
        p1 = new Oyuncu("Oyuncu1");
        p1.elYarat(deste);

        p2 = new Oyuncu("Oyuncu2");
        p2.elYarat(deste);

        p3 = new Oyuncu("Oyuncu3");
        p3.elYarat(deste);

        p4 = new Oyuncu("Oyuncu4");
        p4.elYarat(deste);

        oyuncular.add(p1);
        oyuncular.add(p2);
        oyuncular.add(p3);
        oyuncular.add(p4);

        for(int i=0; i<13; i++){
            kartGoster(p1.el.get(i), cards.get(i));
        }
    }

    public void kozSeç(View v) {
        TextView kozText = findViewById(v.getId());
        koz = kozText.getText().toString();

        kozText1.setVisibility(View.INVISIBLE);
        kozText2.setVisibility(View.INVISIBLE);
        kozText3.setVisibility(View.INVISIBLE);
        kozText4.setVisibility(View.INVISIBLE);

        secilenKozText.setText("Koz: " + koz);
        secilenKozText.setVisibility(View.VISIBLE);

        for(int i=0; i<puanTextler.size(); i++) {
            puanTextler.get(i).setVisibility(View.VISIBLE);
        }
    }

    public void kartTıkla(View v) {
        if(turKimde.equals(oyuncular.get(0))) {
            String tag = (String)v.getTag();
            int secilenKart = Integer.parseInt(tag);
            boolean oynayabilirmi = true;

            oynananSembol = oyuncular.get(0).kartGetir(secilenKart-1).sembol;
            if(oynananSembol.equals(koz)) {
                if(kozOynanabilirmi == false) {
                    oynayabilirmi = false;
                    Toast.makeText(getApplicationContext(),"Koz Çıkmadan Koz Oynayamazsın Yeni Kart Seç",Toast.LENGTH_SHORT).show();
                }
            }

            if(oynayabilirmi == true) {
                kartGoster(oyuncular.get(0).kartGetir(secilenKart-1),middleCards.get(0));
                oynananKartlar.add(oyuncular.get(0).el.get(secilenKart-1));
                oynananSembol = oyuncular.get(0).el.get(secilenKart-1).sembol;
                v.setVisibility(View.INVISIBLE);
                middleCards.get(0).setVisibility(View.VISIBLE);
                botOyna();
            }
        }
    }

    public void botOyna() {
        for(int id=1; id<4; id++) {
            turKimde = oyuncular.get(id);
            oynanan = turKimde.el.get(0);

            oynananSembolVarmı = false;

            for(int i=0; i<turKimde.el.size(); i++) {
                if(turKimde.el.get(i).sembol.equals(oynananSembol)) {
                    oynanabilirKartlar.add(turKimde.el.get(i));
                    oynananSembolVarmı = true;
                }
            }

            if(oynananSembolVarmı == false) {
                for(int i=0; i<turKimde.el.size(); i++) {
                    if(turKimde.el.get(i).sembol.equals(koz)) {
                        oynanabilirKartlar.add(turKimde.el.get(i));
                        kozOynanabilirmi = true;
                    }
                }
            }

            if(!oynanabilirKartlar.isEmpty()) {
                int enBüyük = oynanabilirKartlar.get(0).sayı;
                oynanan = oynanabilirKartlar.get(0);
                for(int j=0; j<oynanabilirKartlar.size(); j++) {
                    if(oynanabilirKartlar.get(j).sayı > enBüyük) {
                        enBüyük = oynanabilirKartlar.get(j).sayı;
                        oynanan = oynanabilirKartlar.get(j);
                    }
                }
            }

            oynananKartlar.add(oynanan);
            turKimde.el.remove(oynanan);
            kartGoster(oynanan, middleCards.get(id));
            middleCards.get(id).setVisibility(View.VISIBLE);
            oynanabilirKartlar.clear();
        }
        kazanc(oynananKartlar);

        for(int i=0; i<puanTextler.size(); i++) {
            puanTextler.get(i).setText("Puan: " + oyuncular.get(i).puan);
        }

        turSayısı++;
        oynananKartlar.clear();

        turKimde = oyuncular.get(0);

        if(turSayısı == 13) {
            int enBüyük = oyuncular.get(0).puan;
            oyunKazanan = oyuncular.get(0);

            if(oyuncular.get(1).puan > enBüyük) {
                enBüyük = oyuncular.get(1).puan;
                oyunKazanan = oyuncular.get(1);
            }
            if(oyuncular.get(2).puan > enBüyük) {
                enBüyük = oyuncular.get(2).puan;
                oyunKazanan = oyuncular.get(2);
            }
            if(oyuncular.get(3).puan > enBüyük) {
                enBüyük = oyuncular.get(3).puan;
                oyunKazanan = oyuncular.get(3);
            }

            sonucText.setText("Kazanan: " + oyunKazanan.ad + "     puan: " + enBüyük);
            sonucText.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
        }
    }

    public void kazanc(ArrayList<Kart> oynananKartlar) {
        boolean kozAtıldımı = false;

        for(int i=0; i<oynananKartlar.size(); i++){
            if(oynananKartlar.get(i).sembol.equals(koz)) {
                oynananKartlar.get(i).sayı *= 8;
                oynananKozKartlar.add(oynananKartlar.get(i));
                kozAtıldımı = true;
            }
            else {
                oynananKozKartlar.add(oynananKartlar.get(i));
            }
        }

        if(kozAtıldımı == false) {
            for(int i=0; i<oynananKartlar.size(); i++) {
                if(!oynananKartlar.get(i).sembol.equals(oynananSembol)) {
                    oynananKartlar.get(i).sayı = 0;
                }
            }

            int enBüyük = oynananKartlar.get(0).sayı;
            elKazanan = oyuncular.get(0);

            if(oynananKartlar.get(1).sayı > enBüyük) {
                enBüyük = oynananKartlar.get(1).sayı;
                elKazanan = oyuncular.get(1);
            }
            if(oynananKartlar.get(2).sayı > enBüyük) {
                enBüyük = oynananKartlar.get(2).sayı;
                elKazanan = oyuncular.get(2);
            }
            if(oynananKartlar.get(3).sayı > enBüyük) {
                elKazanan = oyuncular.get(3);
            }
        } else {
            int enBüyük2 = oynananKozKartlar.get(0).sayı;
            elKazanan = oyuncular.get(0);

            if(oynananKozKartlar.get(1).sayı > enBüyük2) {
                enBüyük2 = oynananKozKartlar.get(1).sayı;
                elKazanan = oyuncular.get(1);
            }
            if(oynananKozKartlar.get(2).sayı > enBüyük2) {
                enBüyük2 = oynananKozKartlar.get(2).sayı;
                elKazanan = oyuncular.get(2);
            }
            if(oynananKozKartlar.get(3).sayı > enBüyük2) {
                elKazanan = oyuncular.get(3);
            }
        }
        oynananKartlar.clear();
        oynananKozKartlar.clear();

        elKazanan.puan++;
    }

    public void oyunuYenidenBaşlat(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}