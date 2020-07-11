package br.com.pokedex_poke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pokedex_poke.R;

import java.util.ArrayList;
import java.util.List;

import br.com.pokedex_poke.dao.PokemonDAO;
import br.com.pokedex_poke.model.Pokemon;
import br.com.pokedex_poke.util.Preferences;

public class MainActivity extends AppCompatActivity {

    private Button btnPokedex;

    private static SharedPreferences sharedPreferences;

    private TextView tvSortudos;
    private TextView tvShinys;
    private TextView tvCem;
    private TextView tvSombrosos;
    private TextView tvPurificados;
    private TextView tvZero;

    private TextView tvRegiao;

    private LinearLayout llDetalhes;

    private ImageView imvKanto;
    private ImageView imvJohto;
    private ImageView imvHoenn;
    private ImageView imvUnova;
    private ImageView imvSinnoh;
    private ImageView imvKalos;
    private ImageView imvAlola;
    private ImageView imvGalar;

    private Boolean iskanto = false;
    private Boolean isjohto = false;
    private Boolean ishoenn = false;
    private Boolean issinnoh = false;
    private Boolean isunova = false;
    private Boolean iskalos = false;
    private Boolean isalola = false;
    private Boolean isgalar = false;


    private List<Pokemon> pokemonListKanto;
    private List<Pokemon> pokemonListJohto;
    private List<Pokemon> pokemonListHoenn;
    private List<Pokemon> pokemonListSinnoh;
    private List<Pokemon> pokemonListUnova;
    private List<Pokemon> pokemonListKalos;
    private List<Pokemon> pokemonListAlola;
    private List<Pokemon> pokemonListGalar;

    private PokemonDAO pokemonDAO;
    private Pokemon pokemon;

    private List<Pokemon> listSortudosKanto;
    private List<Pokemon> listShinysKanto;
    private List<Pokemon> listCemKanto;
    private List<Pokemon> listSombrososKanto;
    private List<Pokemon> listPurificadosKanto;
    private List<Pokemon> listZeroKanto;

    private List<Pokemon> listSortudosJohto;
    private List<Pokemon> listShinysJohto;
    private List<Pokemon> listCemJohto;
    private List<Pokemon> listSombrososJohto;
    private List<Pokemon> listPurificadosJohto;
    private List<Pokemon> listZeroJohto;

    private List<Pokemon> listSortudosHoenn;
    private List<Pokemon> listShinysHoenn;
    private List<Pokemon> listCemHoenn;
    private List<Pokemon> listSombrososHoenn;
    private List<Pokemon> listPurificadosHoenn;
    private List<Pokemon> listZeroHoenn;

    private List<Pokemon> listSortudosSinnoh;
    private List<Pokemon> listShinysSinnoh;
    private List<Pokemon> listCemSinnoh;
    private List<Pokemon> listSombrososSinnoh;
    private List<Pokemon> listPurificadosSinnoh;
    private List<Pokemon> listZeroSinnoh;

    private List<Pokemon> listSortudosKalos;
    private List<Pokemon> listShinysKalos;
    private List<Pokemon> listCemKalos;
    private List<Pokemon> listSombrososKalos;
    private List<Pokemon> listPurificadosKalos;
    private List<Pokemon> listZeroKalos;

    private List<Pokemon> listSortudosUnova;
    private List<Pokemon> listShinysUnova;
    private List<Pokemon> listCemUnova;
    private List<Pokemon> listSombrososUnova;
    private List<Pokemon> listPurificadosUnova;
    private List<Pokemon> listZeroUnova;

    private List<Pokemon> listSortudosAlola;
    private List<Pokemon> listShinysAlola;
    private List<Pokemon> listCemAlola;
    private List<Pokemon> listSombrososAlola;
    private List<Pokemon> listPurificadosAlola;
    private List<Pokemon> listZeroAlola;

    private List<Pokemon> listSortudosGalar;
    private List<Pokemon> listShinysGalar;
    private List<Pokemon> listCemGalar;
    private List<Pokemon> listSombrososGalar;
    private List<Pokemon> listPurificadosGalar;
    private List<Pokemon> listZeroGalar;

    private String kanto = " / 151";
    private String johto = " / 100";
    private String hoenn = " / 135";
    private String sinnoh = " / 108";
    private String unova = " / 155";
    private String kalos = " / 72";
    private String alola = " / 88";
    private String galar = " / 81";


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        createList();

    }

    @Override
    protected void onResume() {
        super.onResume();

        eventClick();



    }

    @SuppressLint("SetTextI18n")
    private void itensKanto() {
        listSortudosKanto = pokemonDAO.findSortudos("KANTO");
        listCemKanto = pokemonDAO.findCem("KANTO");
        listPurificadosKanto = pokemonDAO.findPurificados("KANTO");
        listSombrososKanto = pokemonDAO.findSombroso("KANTO");
        listShinysKanto = pokemonDAO.findShinys("KANTO");
        listZeroKanto = pokemonDAO.findZero("KANTO");

        tvCem.setText(listCemKanto.size() + kanto);
        tvPurificados.setText(listPurificadosKanto.size() + kanto);
        tvShinys.setText(listShinysKanto.size() + kanto);
        tvSombrosos.setText(listSombrososKanto.size() + kanto);
        tvSortudos.setText(listSortudosKanto.size() + kanto);
        tvZero.setText(listZeroKanto.size() + kanto);
    }

    @SuppressLint("SetTextI18n")
    private void itensJohto() {
        listSortudosJohto = pokemonDAO.findSortudos("JOHTO");
        listCemJohto = pokemonDAO.findCem("JOHTO");
        listPurificadosJohto = pokemonDAO.findPurificados("JOHTO");
        listSombrososJohto = pokemonDAO.findSombroso("JOHTO");
        listShinysJohto = pokemonDAO.findShinys("JOHTO");
        listZeroJohto = pokemonDAO.findZero("JOHTO");

        tvCem.setText(listCemJohto.size() + johto);
        tvPurificados.setText(listPurificadosJohto.size() + johto);
        tvShinys.setText(listShinysJohto.size() + johto);
        tvSombrosos.setText(listSombrososJohto.size() + johto);
        tvSortudos.setText(listSortudosJohto.size() + johto);
        tvZero.setText(listZeroJohto.size() + johto);
    }

    @SuppressLint("SetTextI18n")
    private void itensHoenn() {
        listSortudosHoenn = pokemonDAO.findSortudos("HOENN");
        listCemHoenn = pokemonDAO.findCem("HOENN");
        listPurificadosHoenn = pokemonDAO.findPurificados("HOENN");
        listSombrososHoenn = pokemonDAO.findSombroso("HOENN");
        listShinysHoenn = pokemonDAO.findShinys("HOENN");
        listZeroHoenn = pokemonDAO.findZero("HOENN");

        tvCem.setText(listCemHoenn.size() + hoenn);
        tvPurificados.setText(listPurificadosHoenn.size() + hoenn);
        tvShinys.setText(listShinysHoenn.size() + hoenn);
        tvSombrosos.setText(listSombrososHoenn.size() + hoenn);
        tvSortudos.setText(listSortudosHoenn.size() + hoenn);
        tvZero.setText(listZeroHoenn.size() + hoenn);
    }

    @SuppressLint("SetTextI18n")
    private void itensSinnoh() {
        listSortudosSinnoh = pokemonDAO.findSortudos("SINNOH");
        listCemSinnoh = pokemonDAO.findCem("SINNOH");
        listPurificadosSinnoh = pokemonDAO.findPurificados("SINNOH");
        listSombrososSinnoh = pokemonDAO.findSombroso("SINNOH");
        listShinysSinnoh = pokemonDAO.findShinys("SINNOH");
        listZeroSinnoh = pokemonDAO.findZero("SINNOH");

        tvCem.setText(listCemSinnoh.size() + sinnoh);
        tvPurificados.setText(listPurificadosSinnoh.size() + sinnoh);
        tvShinys.setText(listShinysSinnoh.size() + sinnoh);
        tvSombrosos.setText(listSombrososSinnoh.size() + sinnoh);
        tvSortudos.setText(listSortudosSinnoh.size() + sinnoh);
        tvZero.setText(listZeroSinnoh.size() + sinnoh);
    }

    @SuppressLint("SetTextI18n")
    private void itensUnova() {
        listSortudosUnova = pokemonDAO.findSortudos("UNOVA");
        listCemUnova = pokemonDAO.findCem("UNOVA");
        listPurificadosUnova = pokemonDAO.findPurificados("UNOVA");
        listSombrososUnova = pokemonDAO.findSombroso("UNOVA");
        listShinysUnova = pokemonDAO.findShinys("UNOVA");
        listZeroUnova = pokemonDAO.findZero("UNOVA");

        tvCem.setText(listCemUnova.size() + unova);
        tvPurificados.setText(listPurificadosUnova.size() + unova);
        tvShinys.setText(listShinysUnova.size() + unova);
        tvSombrosos.setText(listSombrososUnova.size() + unova);
        tvSortudos.setText(listSortudosUnova.size() + unova);
        tvZero.setText(listZeroUnova.size() + unova);
    }

    @SuppressLint("SetTextI18n")
    private void itensKalos() {
        listSortudosKalos = pokemonDAO.findSortudos("KALOS");
        listCemKalos = pokemonDAO.findCem("KALOS");
        listPurificadosKalos = pokemonDAO.findPurificados("KALOS");
        listSombrososKalos = pokemonDAO.findSombroso("KALOS");
        listShinysKalos = pokemonDAO.findShinys("KALOS");
        listZeroKalos = pokemonDAO.findZero("KALOS");

        tvCem.setText(listCemKalos.size() + kalos);
        tvPurificados.setText(listPurificadosKalos.size() + kalos);
        tvShinys.setText(listShinysKalos.size() + kalos);
        tvSombrosos.setText(listSombrososKalos.size() + kalos);
        tvSortudos.setText(listSortudosKalos.size() + kalos);
        tvZero.setText(listZeroKalos.size() + kalos);
    }

    @SuppressLint("SetTextI18n")
    private void itensAlola() {
        listSortudosAlola = pokemonDAO.findSortudos("ALOLA");
        listCemAlola = pokemonDAO.findCem("ALOLA");
        listPurificadosAlola = pokemonDAO.findPurificados("ALOLA");
        listSombrososAlola = pokemonDAO.findSombroso("ALOLA");
        listShinysAlola = pokemonDAO.findShinys("ALOLA");
        listZeroAlola = pokemonDAO.findZero("ALOLA");

        tvCem.setText(listCemAlola.size() + alola);
        tvPurificados.setText(listPurificadosAlola.size() + alola);
        tvShinys.setText(listShinysAlola.size() + alola);
        tvSombrosos.setText(listSombrososAlola.size() + alola);
        tvSortudos.setText(listSortudosAlola.size() + alola);
        tvZero.setText(listZeroAlola.size() + alola);
    }

    @SuppressLint("SetTextI18n")
    private void itensGalar() {
        listSortudosGalar = pokemonDAO.findSortudos("GALAR");
        listCemGalar = pokemonDAO.findCem("GALAR");
        listPurificadosGalar = pokemonDAO.findPurificados("GALAR");
        listSombrososGalar = pokemonDAO.findSombroso("GALAR");
        listShinysGalar = pokemonDAO.findShinys("GALAR");
        listZeroGalar = pokemonDAO.findZero("GALAR");

        tvCem.setText(listCemGalar.size() + galar);
        tvPurificados.setText(listPurificadosGalar.size() + galar);
        tvShinys.setText(listShinysGalar.size() + galar);
        tvSombrosos.setText(listSombrososGalar.size() + galar);
        tvSortudos.setText(listSortudosGalar.size() + galar);
        tvZero.setText(listZeroGalar.size() + galar);
    }

    private void todosFalsos(){
        iskanto = false;
        isjohto = false;
        ishoenn = false;
        issinnoh = false;
        isunova = false;
        iskalos = false;
        isalola = false;
        isgalar = false;
    }

    private void eventClick() {
        btnPokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Preferences.getInstance(MainActivity.this).setFiltro("");
                Preferences.getInstance(MainActivity.this).setFiltrado(false);

                if(iskanto)
                    Preferences.getInstance(MainActivity.this).setRegiao("KANTO");
                else if(isjohto)
                    Preferences.getInstance(MainActivity.this).setRegiao("JOHTO");
                else if(ishoenn)
                    Preferences.getInstance(MainActivity.this).setRegiao("HOENN");
                else if(issinnoh)
                    Preferences.getInstance(MainActivity.this).setRegiao("SINNOH");
                else if(isunova)
                    Preferences.getInstance(MainActivity.this).setRegiao("UNOVA");
                else if(iskalos)
                    Preferences.getInstance(MainActivity.this).setRegiao("KALOS");
                else if(isalola)
                    Preferences.getInstance(MainActivity.this).setRegiao("ALOLA");
                else if(isgalar)
                    Preferences.getInstance(MainActivity.this).setRegiao("GALAR");

                startActivity(new Intent(MainActivity.this, ActKanto.class));
                finish();
            }
        });

        imvKanto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && iskanto){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    iskanto = true;
                    itensKanto();
                    tvRegiao.setText("Kanto");
                }
            }
        });

        imvJohto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && isjohto){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    isjohto = true;
                    itensJohto();
                    tvRegiao.setText("Johto");
                }
            }
        });

        imvHoenn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && ishoenn){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    ishoenn = true;
                    itensHoenn();
                    tvRegiao.setText("Hoenn");
                }
            }
        });

        imvSinnoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && issinnoh){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    issinnoh = true;
                    itensSinnoh();
                    tvRegiao.setText("Sinnoh");
                }
            }
        });

        imvUnova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && isunova){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    isunova = true;
                    itensUnova();
                    tvRegiao.setText("Unova");
                }
            }
        });

        imvKalos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && iskalos){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    iskalos = true;
                    itensKalos();
                    tvRegiao.setText("Kalos");
                }
            }
        });

        imvAlola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && isalola){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    isalola = true;
                    itensAlola();
                    tvRegiao.setText("Alola");
                }
            }
        });

        imvGalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(llDetalhes.getVisibility() == View.VISIBLE && isgalar){
                    llDetalhes.setVisibility(View.GONE);
                    btnPokedex.setVisibility(View.GONE);
                }else {
                    llDetalhes.setVisibility(View.VISIBLE);
                    btnPokedex.setVisibility(View.VISIBLE);
                    todosFalsos();
                    isgalar = true;
                    itensGalar();
                    tvRegiao.setText("Galar");
                }
            }
        });
    }

    private void init() {

        pokemonDAO = new PokemonDAO(this);

        btnPokedex = (Button) findViewById(R.id.btnPokedex);
        btnPokedex.setVisibility(View.GONE);
        llDetalhes = (LinearLayout) findViewById(R.id.llDetalhes);
        llDetalhes.setVisibility(View.GONE);

        tvCem = (TextView) findViewById(R.id.tvCem);
        tvSortudos = (TextView) findViewById(R.id.tvSortudos);
        tvShinys = (TextView) findViewById(R.id.tvShinys);
        tvSombrosos = (TextView) findViewById(R.id.tvSombrosos);
        tvPurificados = (TextView) findViewById(R.id.tvPurificados);
        tvZero = (TextView) findViewById(R.id.tvZero);

        imvKanto = (ImageView) findViewById(R.id.imvKanto);
        imvHoenn = (ImageView) findViewById(R.id.imvHoenn);
        imvJohto = (ImageView) findViewById(R.id.imvJohto);
        imvSinnoh = (ImageView) findViewById(R.id.imvSinnoh);
        imvUnova = (ImageView) findViewById(R.id.imvUnova);
        imvKalos = (ImageView) findViewById(R.id.imvKalos);
        imvAlola = (ImageView) findViewById(R.id.imvAlola);
        imvGalar = (ImageView) findViewById(R.id.imvGalar);

        tvRegiao = (TextView) findViewById(R.id.tvRegiao);

    }

    private void createList() {

        pokemonListKanto = pokemonDAO.findAllRegiao("KANTO");
        pokemonListJohto = pokemonDAO.findAllRegiao("JOHTO");
        pokemonListHoenn = pokemonDAO.findAllRegiao("HOENN");
        pokemonListSinnoh = pokemonDAO.findAllRegiao("SINNOH");
        pokemonListUnova = pokemonDAO.findAllRegiao("UNOVA");
        pokemonListKalos = pokemonDAO.findAllRegiao("KALOS");
        pokemonListAlola = pokemonDAO.findAllRegiao("ALOLA");
        pokemonListGalar = pokemonDAO.findAllRegiao("GALAR");

        if(pokemonListKanto.isEmpty()){

            String[] pokemonsKanto = { "Bulbasaur", "Ivysaur" , "Venusaur" , "Charmander" , "Charmeleon" ,  "Charizard" , "Squirtle" , "Wartortle" ,
                    "Blastoise" , "Caterpie" ,  "Metapod" ,  "Butterfree" ,  "Weedle" ,  "Kakuna" ,  "Beedrill" ,  "Pidgey" , "Pidgeotto" , "Pidgeot" ,
                    "Rattata" , "Raticate" , "Spearow" , "Fearow" , "Ekans" , "Arbok" , "Pikachu" , "Raichu" , "Sandshrew" , "Sandslash" , "Nidoran?" ,
                    "Nidorina" , "Nidoqueen" , "Nidoran" , "Nidorino" , "Nidoking" , "Clefairy" , "Clefable" , "Vulpix" , "Ninetales" , "Jigglypuff" ,
                    "Wigglytuff" , "Zubat" , "Golbat" , "Oddish" , "Gloom" , "Vileplume" , "Paras" ,  "Parasect" , "Venonat" ,  "Venomoth" , "Diglett" ,
                    "Dugtrio" , "Meowth" ,  "Persian" ,  "Psyduck" , "Golduck" , "Mankey" , "Primeape" ,  "Growlithe" , "Arcanine" , "Poliwag" , "Poliwhirl" ,
                    "Poliwrath" , "Abra" , "Kadabra" , "Alakazam" ,  "Machop" ,  "Machoke" ,  "Machamp" ,  "Bellsprout" , "Weepinbell" ,  "Victreebel" ,
                    "Tentacool" , "Tentacruel" ,  "Geodude" ,  "Graveler" ,  "Golem" ,  "Ponyta" ,  "Rapidash" ,  "Slowpoke" ,  "Slowbro" , "Magnemite" ,
                    "Magneton" , "Farfetch'd" ,  "Doduo" ,  "Dodrio" ,  "Seel" , "Dewgong" , "Grimer" , "Muk" , "Shellder" , "Cloyster" , "Gastly" ,
                    "Haunter" , "Gengar" , "Onix" , "Drowzee" , "Hypno" , "Krabby" , "Kingler" ,  "Voltorb" , "Electrode" ,  "Exeggcute" , "Exeggutor" ,
                    "Cubone" , "Marowak" , "Hitmonlee" ,  "Hitmonchan" , "Lickitung" , "Koffing" , "Weezing" , "Rhyhorn" , "Rhydon" , "Chansey" ,
                    "Tangela" , "Kangaskhan" , "Horsea" , "Seadra" , "Goldeen" ,  "Seaking" , "Staryu" , "Starmie" , "Mr. Mime" , "Scyther" , "Jynx" ,
                    "Electabuzz" , "Magmar" , "Pinsir" , "Tauros" , "Magikarp" , "Gyarados" , "Lapras" , "Ditto" , "Eevee" , "Vaporeon" , "Jolteon" ,
                    "Flareon" , "Porygon" , "Omanyte" , "Omastar" , "Kabuto" , "Kabutops" , "Aerodactyl" , "Snorlax" , "Articuno" , "Zapdos" ,
                    "Moltres" , "Dratini" , "Dragonair" , "Dragonite" , "Mewtwo" , "Mew" };

            for(int i = 0; i < pokemonsKanto.length ; i++){

                Pokemon pokemon = new Pokemon();
                if(i < 9)
                    pokemon.setNUMERO("00" + (i+1));
                if(i >= 9 && i < 99)
                    pokemon.setNUMERO("0" + (i+1));
                if(i >= 99)
                    pokemon.setNUMERO(String.valueOf((i+1)));

                pokemon.setNOME(pokemonsKanto[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("KANTO");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }
        if(pokemonListJohto.isEmpty()){

            String[] pokemonsJohto = {"Chikorita","Bayleef","Meganium","Cyndaquil","Quilava","Typhlosion","Totodile","Croconaw","Feraligatr","Sentret","Furret"
                                    ,"Hoothoot","Noctowl","Ledyba","Ledian","Spinarak","Ariados","Crobat","Chinchou","Lanturn","Pichu","Cleffa","Igglybuff","Togepi"
                                    ,"Togetic","Natu","Xatu","Mareep","Flaaffy","Ampharos","Bellossom","Marill","Azumarill","Sudowoodo","Politoed","Hoppip","Skiploom"
                                    ,"Jumpluff","Aipom","Sunkern","Sunflora","Yanma","Wooper","Quagsire","Espeon","Umbreon","Murkrow","Slowking","Misdreavus","Unown"
                                    ,"Wobbuffet","Girafarig","Pineco","Forretress","Dunsparce","Gligar","Steelix","Snubbull","Granbull","Qwilfish","Scizor","Shuckle"
                                    ,"Heracross","Sneasel","Teddiursa","Ursaring","Slugma","Magcargo","Swinub","Piloswine","Corsola","Remoraid","Octillery","Delibird"
                                    ,"Mantine","Skarmory","Houndour","Houndoom","Kingdra","Phanpy","Donphan","Porygon2","Stantler","Smeargle","Tyrogue","Hitmontop"
                                    ,"Smoochum","Elekid","Magby","Miltank","Blissey","Raikou","Entei","Suicune","Larvitar","Pupitar","Tyranitar","Lugia","Ho-Oh","Celebi" };

            for(int i = 0; i < pokemonsJohto.length ; i++){

                Pokemon pokemon = new Pokemon();

                    pokemon.setNUMERO(String.valueOf((151 +(i+1))));

                pokemon.setNOME(pokemonsJohto[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("JOHTO");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }
        if(pokemonListHoenn.isEmpty()){

            String[] pokemonsHoenn = {"Treecko","Grovyle","Sceptile","Torchic","Combusken","Blaziken","Mudkip","Marshtomp","Swampert","Poochyena","Mightyena","Zigzagoon","Linoone",
                    "Wurmple","Silcoon","Beautifly","Cascoon","Dustox","Lotad","Lombre","Ludicolo","Seedot","Nuzleaf","Shiftry","Taillow","Swellow","Wingull","Pelipper","Ralts","Kirlia",
                    "Gardevoir","Surskit","Masquerain","Shroomish","Breloom","Slakoth","Vigoroth","Slaking","Nincada","Ninjask","Shedinja","Whismur","Loudred","Exploud","Makuhita",
                    "Hariyama","Azurill","Nosepass","Skitty","Delcatty","Sableye","Mawile","Aron","Lairon","Aggron","Meditite","Medicham","Electrike","Manectric","Plusle","Minun",
                    "Volbeat","Illumise","Roselia","Gulpin","Swalot","Carvanha","Sharpedo","Wailmer","Wailord","Numel","Camerupt","Torkoal","Spoink","Grumpig","Spinda","Trapinch",
                    "Vibrava","Flygon","Cacnea","Cacturne","Swablu","Altaria","Zangoose","Seviper","Lunatone","Solrock","Barboach","Whiscash","Corphish","Crawdaunt","Baltoy","Claydol",
                    "Lileep","Cradily","Anorith","Armaldo","Feebas","Milotic","Castform","Kecleon","Shuppet","Banette","Duskull","Dusclops","Tropius","Chimecho","Absol","Wynaut","Snorunt",
                    "Glalie","Spheal","Sealeo","Walrein","Clamperl","Huntail","Gorebyss","Relicanth","Luvdisc","Bagon","Shelgon","Salamence","Beldum","Metang","Metagross","Regirock",
                    "Regice","Registeel","Latias","Latios","Kyogre","Groudon","Rayquaza","Jirachi","Deoxys" };

            for(int i = 0; i < pokemonsHoenn.length ; i++){

                Pokemon pokemon = new Pokemon();

                pokemon.setNUMERO(String.valueOf((251 +(i+1))));

                pokemon.setNOME(pokemonsHoenn[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("HOENN");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }

        if(pokemonListSinnoh.isEmpty()){

            String[] pokemonsSinnoh = {"Turtwig","Grotle","Torterra","Chimchar","Monferno","Infernape","Piplup","Prinplup","Empoleon","Starly","Staravia","Staraptor",
                    "Bidoof","Bibarel","Kricketot","Kricketune","Shinx","Luxio","Luxray","Budew","Roserade","Cranidos","Rampardos","Shieldon","Bastiodon","Burmy","Wormadam",
                    "Mothim","Combee","Vespiquen","Pachirisu","Buizel","Floatzel","Cherubi","Cherrim","Shellos","Gastrodon","Ambipom","Drifloon","Drifblim","Buneary","Lopunny",
                    "Mismagius","Honchkrow","Glameow","Purugly","Chingling","Stunky","Skuntank","Bronzor","Bronzong","Bonsly","Mime Jr.","Happiny","Chatot","Spiritomb","Gible",
                    "Gabite","Garchomp","Munchlax","Riolu","Lucario","Hippopotas","Hippowdon","Skorupi","Drapion","Croagunk","Toxicroak","Carnivine","Finneon","Lumineon","Mantyke",
                    "Snover","Abomasnow","Weavile","Magnezone","Lickilicky","Rhyperior","Tangrowth","Electivire","Magmortar","Togekiss","Yanmega","Leafeon","Glaceon","Gliscor",
                    "Mamoswine","Porygon-Z","Gallade","Probopass","Dusknoir","Froslass","Rotom","Uxie","Mesprit","Azelf","Dialga","Palkia","Heatran","Regigigas","Giratina","Cresselia",
                    "Phione","Manaphy","Darkrai","Shaymin","Arceus","Victini" };

            for(int i = 0; i < pokemonsSinnoh.length ; i++){

                Pokemon pokemon = new Pokemon();

                pokemon.setNUMERO(String.valueOf((386 +(i+1))));

                pokemon.setNOME(pokemonsSinnoh[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("SINNOH");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }

        if(pokemonListUnova.isEmpty()){

            String[] pokemonsUnova = {"Snivy","Servine","Serperior","Tepig","Pignite","Emboar","Oshawott","Dewott","Samurott","Patrat","Watchog","Lillipup","Herdier",
                    "Stoutland","Purrloin","Liepard","Pansage","Simisage","Pansear","Simisear","Panpour","Simipour","Munna","Musharna","Pidove","Tranquill","Unfezant",
                    "Blitzle","Zebstrika","Roggenrola","Boldore","Gigalith","Woobat","Swoobat","Drilbur","Excadrill","Audino","Timburr","Gurdurr","Conkeldurr","Tympole",
                    "Palpitoad","Seismitoad","Throh","Sawk","Sewaddle","Swadloon","Leavanny","Venipede","Whirlipede","Scolipede","Cottonee","Whimsicott","Petilil","Lilligant",
                    "Basculin","Sandile","Krokorok","Krookodile","Darumaka","Darmanitan","Maractus","Dwebble","Crustle","Scraggy","Scrafty","Sigilyph","Yamask","Cofagrigus",
                    "Tirtouga","Carracosta","Archen","Archeops","Trubbish","Garbodor","Zorua","Zoroark","Minccino","Cinccino","Gothita","Gothorita","Gothitelle","Solosis",
                    "Duosion","Reuniclus","Ducklett","Swanna","Vanillite","Vanillish","Vanilluxe","Deerling","Sawsbuck","Emolga","Karrablast","Escavalier","Foongus","Amoonguss",
                    "Frillish","Jellicent","Alomomola","Joltik","Galvantula","Ferroseed","Ferrothorn","Klink","Klang","Klinklang","Tynamo","Eelektrik","Eelektross","Elgyem",
                    "Beheeyem","Litwick","Lampent","Chandelure","Axew","Fraxure","Haxorus","Cubchoo","Beartic","Cryogonal","Shelmet","Accelgor","Stunfisk","Mienfoo","Mienshao",
                    "Druddigon","Golett","Golurk","Pawniard","Bisharp","Bouffalant","Rufflet","Braviary","Vullaby","Mandibuzz","Heatmor","Durant","Deino","Zweilous","Hydreigon",
                    "Larvesta","Volcarona","Cobalion","Terrakion","Virizion","Tornadus","Thundurus","Reshiram","Zekrom","Landorus","Kyurem","Keldeo","Meloetta","Genesect" };

            for(int i = 0; i < pokemonsUnova.length ; i++){

                Pokemon pokemon = new Pokemon();

                pokemon.setNUMERO(String.valueOf((494 +(i+1))));

                pokemon.setNOME(pokemonsUnova[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("UNOVA");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }

        if(pokemonListKalos.isEmpty()){

            String[] pokemonsKalos = {"Chespin","Quilladin","Chesnaught","Fennekin","Braixen","Delphox","Froakie","Frogadier","Greninja","Bunnelby","Diggersby","Fletchling",
                    "Fletchinder","Talonflame","Scatterbug","Spewpa","Vivillon","Litleo","Pyroar","Flabébé","Floette","Florges","Skiddo","Gogoat","Pancham","Pangoro","Furfrou",
                    "Espurr","Meowstic","Honedge","Doublade","Aegislash","Spritzee","Aromatisse","Swirlix","Slurpuff","Inkay","Malamar","Binacle","Barbaracle","Skrelp","Dragalge",
                    "Clauncher","Clawitzer","Helioptile","Heliolisk","Tyrunt","Tyrantrum","Amaura","Aurorus","Sylveon","Hawlucha","Dedenne","Carbink","Goomy","Sliggoo","Goodra",
                    "Klefki","Phantump","Trevenant","Pumpkaboo","Gourgeist","Bergmite","Avalugg","Noibat","Noivern","Xerneas","Yveltal","Zygarde","Diancie","Hoopa","Volcanion" };

            for(int i = 0; i < pokemonsKalos.length ; i++){

                Pokemon pokemon = new Pokemon();

                pokemon.setNUMERO(String.valueOf((649 +(i+1))));

                pokemon.setNOME(pokemonsKalos[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("KALOS");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }

        if(pokemonListAlola.isEmpty()){

            String[] pokemonsAlola = { "Rowlet","Dartrix","Decidueye","Litten","Torracat","Incineroar","Popplio","Brionne","Primarina","Pikipek","Trumbeak","Toucannon","Yungoos","Gumshoos",
                    "Grubbin","Charjabug","Vikavolt","Crabrawler","Crabominable","Oricorio","Cutiefly","Ribombee","Rockruff","Lycanroc","Wishiwashi","Mareanie","Toxapex","Mudbray","Mudsdale",
                    "Dewpider","Araquanid","Fomantis","Lurantis","Morelull","Shiinotic","Salandit","Salazzle","Stufful","Bewear","Bounsweet","Steenee","Tsareena","Comfey","Oranguru",
                    "Passimian","Wimpod","Golisopod","Sandygast","Palossand","Pyukumuku","Type: Null","Silvally","Minior","Komala","Turtonator","Togedemaru","Mimikyu","Bruxish","Drampa",
                    "Dhelmise","Jangmo-o","Hakamo-o","Kommo-o","Tapu Koko","Tapu Lele","Tapu Bulu","Tapu Fini","Cosmog","Cosmoem","Solgaleo","Lunala","Nihilego","Buzzwole","Pheromosa",
                    "Xurkitree","Celesteela","Kartana","Guzzlord","Necrozma","Magearna","Marshadow","Poipole","Naganadel","Stakataka","Blacephalon","Zeraora","Meltan","Melmetal"};

            for(int i = 0; i < pokemonsAlola.length ; i++){

                Pokemon pokemon = new Pokemon();

                pokemon.setNUMERO(String.valueOf((721 +(i+1))));

                pokemon.setNOME(pokemonsAlola[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("ALOLA");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }

        if(pokemonListGalar.isEmpty()){

            String[] pokemonsGalar = {"Grookey","Thwackey","Rillaboom","Scorbunny","Raboot","Cinderace","Sobble","Drizzile","Inteleon","Skwovet","Greedent","Rookidee","Corvisquire",
                    "Corviknight","Blipbug","Dottler","Orbeetle","Nickit","Thievul","Gossifleur","Eldegoss","Wooloo","Dubwool","Chewtle","Drednaw","Yamper","Boltund","Rolycoly",
                    "Carkol","Coalossal","Applin","Flapple","Appletun","Silicobra","Sandaconda","Cramorant","Arrokuda","Barraskewda","Toxel","Toxtricity","Sizzlipede","Centiskorch",
                    "Clobbopus","Grapploct","Sinistea","Polteageist","Hatenna","Hattrem","Hatterene","Impidimp","Morgrem","Grimmsnarl","Obstagoon","Perrserker","Cursola","Sirfetch'd",
                    "Mr. Rime","Runerigus","Milcery","Alcremie","Falinks","Pincurchin","Snom","Frosmoth","Stonjourner","Eiscue","Indeedee","Morpeko","Cufant","Copperajah","Dracozolt",
                    "Arctozolt","Dracovish","Arctovish","Duraludon","Dreepy","Drakloak","Dragapult","Zacian","Zamazenta","Eternatus"};

            for(int i = 0; i < pokemonsGalar.length ; i++){

                Pokemon pokemon = new Pokemon();

                pokemon.setNUMERO(String.valueOf((809 +(i+1))));

                pokemon.setNOME(pokemonsGalar[i]);
                pokemon.setPURIFICADO("N");
                pokemon.setSHINY("N");
                pokemon.setSOMBROSO("N");
                pokemon.setSORTUDO("N");
                pokemon.setMACHO("N");
                pokemon.setFEMEA("N");
                pokemon.setPOKEMON_100("N");
                pokemon.setPOKEMON_0("N");
                pokemon.setREGIAO("GALAR");

                pokemonListKanto.add(pokemon);
                pokemonDAO.insert(pokemon);
            }
        }
    }

}
