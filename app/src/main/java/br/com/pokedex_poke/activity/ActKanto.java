package br.com.pokedex_poke.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pokedex_poke.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.pokedex_poke.adapter.Adapter_GridView;
import br.com.pokedex_poke.dao.PokemonDAO;
import br.com.pokedex_poke.interfaces.PegarListas;
import br.com.pokedex_poke.model.Pokemon;
import br.com.pokedex_poke.util.Preferences;

public class ActKanto extends AppCompatActivity implements PegarListas{

    private GridView gvKanto;
    private Adapter_GridView adapter_gridView;
    private List<Pokemon> pokemonList;
    private List<Pokemon> listaAUX;
    private PokemonDAO pokemonDAO;
    private LinearLayout llopcoes;

    private CheckBox cbPurificado;
    private CheckBox cbZero;
    private CheckBox cbCem;
    private CheckBox cbSortudo;
    private CheckBox cbShiny;
    private CheckBox cbSombroso;

    private LinearLayout llMacho;
    private LinearLayout llFemea;
    private ImageButton imbFemea;
    private ImageButton imbMacho;
    private ImageButton imbFiltro;
    private ImageButton imbCancelar;

    private EditText etBusca;
    private TextView tvQuantosPokemons;

    private String total_regiao;

    private SearchView sv;
    private PegarListas pegarListas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_kanto);

        init();

        total_regiao = "";

        criarLista();

        eventClick();

    }



    private void criarLista() {
        switch (Preferences.getInstance(ActKanto.this).getRegiao()){
            case "KANTO":
                pokemonList = getPokemonList("KANTO");
                break;
            case "JOHTO":
                pokemonList = getPokemonList("JOHTO");
                break;
            case "HOENN":
                pokemonList = getPokemonList("HOENN");
                break;
            case "SINNOH":
                pokemonList = getPokemonList("SINNOH");
                break;
            case "UNOVA":
                pokemonList = getPokemonList("UNOVA");
                break;
            case "KALOS":
                pokemonList = getPokemonList("KALOS");
                break;
            case "ALOLA":
                pokemonList = getPokemonList("ALOLA");
                break;
            case "GALAR":
                pokemonList = getPokemonList("GALAR");
                break;
        }
        total_regiao = " / " + pokemonList.size();
    }

    private List<Pokemon> getPokemonList(String regiao) {
        return pokemonDAO.findAllRegiao(regiao);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(!Preferences.getInstance(this).getfiltro().equalsIgnoreCase("")){
            etBusca.setText("");
            imbCancelar.setVisibility(View.VISIBLE);
            pokemonList = pokemonDAO.findByFiltro(Preferences.getInstance(ActKanto.this).getfiltro(), Preferences.getInstance(ActKanto.this).getRegiao());
        }

        eventClick();

        adapter();


    }

    private void adapter() {
        adapter_gridView = new Adapter_GridView(ActKanto.this, android.R.layout.simple_list_item_1, pokemonList);
        gvKanto.setAdapter(adapter_gridView);
        adapter_gridView.notifyDataSetChanged();
        tvQuantosPokemons.setText( pokemonList.size() + total_regiao);
    }

    private void eventClick() {

        gvKanto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                retirarTeclado();

                llopcoes.setVisibility(View.VISIBLE);
                llFemea.setVisibility(View.VISIBLE);
                llMacho.setVisibility(View.VISIBLE);

                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();

                if(pos != position){
                    ((Adapter_GridView)gvKanto.getAdapter()).setPosicaoEscolhida(position);

                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(position).getNUMERO());

                    if(pokemon != null){
                        if(pokemon.getPURIFICADO().equalsIgnoreCase("N"))
                            cbPurificado.setChecked(false);
                        else
                            cbPurificado.setChecked(true);

                        if(pokemon.getPOKEMON_0().equalsIgnoreCase("N"))
                            cbZero.setChecked(false);
                        else
                            cbZero.setChecked(true);

                        if(pokemon.getPOKEMON_100().equalsIgnoreCase("N"))
                            cbCem.setChecked(false);
                        else
                            cbCem.setChecked(true);

                        if(pokemon.getSHINY().equalsIgnoreCase("N"))
                            cbShiny.setChecked(false);
                        else
                            cbShiny.setChecked(true);

                        if(pokemon.getSOMBROSO().equalsIgnoreCase("N"))
                            cbSombroso.setChecked(false);
                        else
                            cbSombroso.setChecked(true);

                        if(pokemon.getSORTUDO().equalsIgnoreCase("N"))
                            cbSortudo.setChecked(false);
                        else
                            cbSortudo.setChecked(true);

                        if(pokemon.getMACHO().equalsIgnoreCase("N")){
                            imbMacho.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_macho_black));
                        }else {
                            imbMacho.setImageDrawable(getResources().getDrawable(R.drawable.icon_macho));
                        }

                        if(pokemon.getFEMEA().equalsIgnoreCase("N")){
                            imbFemea.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_femea_black));
                        }else {
                            imbFemea.setImageDrawable(getResources().getDrawable(R.drawable.icon_female));
                        }

                    }

                    Intent intent = new Intent(ActKanto.this, ActPokemonFull.class);
                    String json = new Gson().toJson(pokemonList);
                    intent.putExtra("JSON", json);
                    intent.putExtra("POSITION", position);
                    startActivity(intent);


                    ((Adapter_GridView)gvKanto.getAdapter()).notifyDataSetChanged();
                }else {
                    desmarcarGridview();
                }

            }
        });

        llMacho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000){
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());

                    if(imbMacho.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_action_macho_black).getConstantState())){
                        imbMacho.setImageDrawable(getResources().getDrawable(R.drawable.icon_macho));
                        pokemon.setMACHO("S");
                    }else {
                        imbMacho.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_macho_black));
                        pokemon.setMACHO("N");
                    }
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
                adapter_gridView.notifyDataSetChanged();

            }
        });

        llFemea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000) {
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());

                    if (imbFemea.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_action_femea_black).getConstantState())) {
                        imbFemea.setImageDrawable(getResources().getDrawable(R.drawable.icon_female));
                        pokemon.setFEMEA("S");
                    } else {
                        imbFemea.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_femea_black));
                        pokemon.setFEMEA("N");
                    }
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
            }
        });

        cbPurificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000) {
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());
                    if (cbPurificado.isChecked())
                        pokemon.setPURIFICADO("S");
                    else
                        pokemon.setPURIFICADO("N");
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
                adapter_gridView.notifyDataSetChanged();
            }
        });

        cbZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000) {
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());
                    if (cbZero.isChecked())
                        pokemon.setPOKEMON_0("S");
                    else
                        pokemon.setPOKEMON_0("N");
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
            }
        });

        cbCem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000) {
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());
                    if (cbCem.isChecked())
                        pokemon.setPOKEMON_100("S");
                    else
                        pokemon.setPOKEMON_100("N");
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
            }
        });

        cbSortudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000) {
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());
                    if (cbSortudo.isChecked())
                        pokemon.setSORTUDO("S");
                    else
                        pokemon.setSORTUDO("N");
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
                adapter_gridView.notifyDataSetChanged();
            }
        });

        cbShiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000) {
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());
                    if (cbShiny.isChecked())
                        pokemon.setSHINY("S");
                    else
                        pokemon.setSHINY("N");
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
                adapter_gridView.notifyDataSetChanged();
            }
        });

        cbSombroso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
                if(pos != 10000000) {
                    Pokemon pokemon = pokemonDAO.loadByNumero(pokemonList.get(pos).getNUMERO());
                    if (cbSombroso.isChecked()){
                        pokemon.setSOMBROSO("S");
                    }
                    else {
                        pokemon.setSOMBROSO("N");
                    }
                    pokemonDAO.update(pokemon);
                    atualizaListaPokemons();
                }
                adapter_gridView.notifyDataSetChanged();

            }
        });

        etBusca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                desmarcarGridview();
                try{
                    filter(charSequence.toString());
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        imbFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retirarTeclado();
                desmarcarGridview();
                startActivity(new Intent(ActKanto.this, ActFiltros.class));

            }
        });

        imbCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retirarTeclado();
                criarLista();
                adapter();
                imbCancelar.setVisibility(View.GONE);
                Preferences.getInstance(ActKanto.this).setFiltro("");
                Preferences.getInstance(ActKanto.this).setFiltrado(false);
                tvQuantosPokemons.setText( pokemonList.size() + total_regiao);

            }
        });


    }




    private void atualizaListaPokemons() {

        if(!Preferences.getInstance(ActKanto.this).getFiltrado()){
            if(!etBusca.getText().toString().equalsIgnoreCase("")){
                List<Pokemon> list = new ArrayList<Pokemon>();
                List<Pokemon> listOK = new ArrayList<Pokemon>();
                list =  getPokemonList(Preferences.getInstance(ActKanto.this).getRegiao());


                for (int i = 0; i < list.size(); i++){
                    for(int j = 0; j < pokemonList.size(); j++){

                        if(pokemonList.get(j).getNUMERO().equalsIgnoreCase(list.get(i).getNUMERO()))
                            listOK.add(list.get(i));

                    }
                }
                pokemonList.clear();
                pokemonList.addAll(listOK);
            }else {
                pokemonList = getPokemonList(Preferences.getInstance(ActKanto.this).getRegiao());
            }
        }
            ((Adapter_GridView)gvKanto.getAdapter()).updateListPokemons(pokemonList);
            setListaPokemon(pokemonList);


    }

    public void setListaPokemon(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @Override
    public List<Pokemon> getListPokemon(){
        return this.pokemonList;
    }

    @Override
    public Integer getPosition(){
        return ((Adapter_GridView)gvKanto.getAdapter()).getPosicaoEscolhida();
    }

    private void retirarTeclado() {
        InputMethodManager imm = (InputMethodManager) ActKanto.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void init() {

        imbCancelar = (ImageButton) findViewById(R.id.imbCancelar);
        imbCancelar.setVisibility(View.GONE);
        imbFiltro = (ImageButton) findViewById(R.id.imbFiltro);
        tvQuantosPokemons = (TextView) findViewById(R.id.tvQuantosPokemons);
        etBusca = (EditText) findViewById(R.id.etBusca);

        imbFemea = (ImageButton) findViewById(R.id.imbFemea);
        imbMacho = (ImageButton) findViewById(R.id.imbMacho);
        llMacho = (LinearLayout) findViewById(R.id.llMacho);
        llFemea = (LinearLayout) findViewById(R.id.llFemea);
        gvKanto = (GridView) findViewById(R.id.gvKanto);
        llopcoes = (LinearLayout) findViewById(R.id.llopcoes);
            llopcoes.setVisibility(View.GONE);
            llFemea.setVisibility(View.GONE);
            llMacho.setVisibility(View.GONE);

        cbCem = (CheckBox) findViewById(R.id.cbCem);
        cbPurificado = (CheckBox) findViewById(R.id.cbPurificado);
        cbShiny = (CheckBox) findViewById(R.id.cbShiny);
        cbSombroso = (CheckBox) findViewById(R.id.cbSombroso);
        cbSortudo = (CheckBox) findViewById(R.id.cbSortudo);
        cbZero = (CheckBox) findViewById(R.id.cbZero);

        pokemonDAO = new PokemonDAO(this);
        listaAUX = new ArrayList<Pokemon>();

    }


    private void desmarcarGridview() {
        ((Adapter_GridView) gvKanto.getAdapter()).setPosicaoEscolhida(10000000);
        llopcoes.setVisibility(View.GONE);
        llFemea.setVisibility(View.GONE);
        llMacho.setVisibility(View.GONE);
    }

    private void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        if(listaAUX.isEmpty())
            listaAUX.addAll(pokemonList);

        pokemonList.clear();
        if (charText.isEmpty()) {
            pokemonList.addAll(listaAUX);
            listaAUX.clear();
        } else {
            for ( Pokemon wp : listaAUX ) {

                if ( wp.getNOME().toLowerCase(Locale.getDefault()).contains(charText)
                  || wp.getNUMERO().toLowerCase(Locale.getDefault()).contains(charText)){
                    pokemonList.add(wp);
                }
            }
        }
        tvQuantosPokemons.setText( pokemonList.size() + total_regiao);
        if(pokemonList.size() == 0){
            ((Adapter_GridView)gvKanto.getAdapter()).updateListPokemons(pokemonList);
        }
        adapter();
        adapter_gridView.notifyDataSetChanged ();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent( ActKanto.this,MainActivity.class));
        finish();

    }
}
