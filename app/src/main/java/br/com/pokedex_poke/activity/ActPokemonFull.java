package br.com.pokedex_poke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedex_poke.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.pokedex_poke.dao.PokemonDAO;
import br.com.pokedex_poke.interfaces.PegarListas;
import br.com.pokedex_poke.model.Pokemon;

public class ActPokemonFull extends AppCompatActivity {

    private Pokemon pokemon;
    private PokemonDAO pokemonDAO;

    private TextView tvNome;
    private TextView tvNumero;

    private ImageView imvShiny;
    private ImageView imvSortudo;
    private ImageView imvSombroso;
    private ImageView imvPurificado;
    private ImageView imvPokemon;

    private PegarListas pegarListas;
    private List<Pokemon> pokemonList;
    private Integer position;
    private String json;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_pokemon_full);

        // lista e posicao

        init();

        criandoList();

        pokemon = pokemonList.get(position);

        String nomeDrawable = "poke_" + pokemon.getNUMERO();
        int id = this.getResources().getIdentifier("com.example.pokedex_poke:drawable/" + nomeDrawable, null, null);
        imvPokemon.setImageResource(id);

        tvNome.setText(pokemon.getNOME());
        tvNumero.setText(pokemon.getNUMERO());

        if(pokemon.getSHINY().equalsIgnoreCase("S"))
            imvShiny.setVisibility(View.VISIBLE);
        else
            imvShiny.setVisibility(View.INVISIBLE);

        if(pokemon.getSORTUDO().equalsIgnoreCase("S"))
            imvSortudo.setVisibility(View.VISIBLE);
        else
            imvSortudo.setVisibility(View.INVISIBLE);

        if(pokemon.getSOMBROSO().equalsIgnoreCase("S"))
            imvSombroso.setVisibility(View.VISIBLE);
        else
            imvSombroso.setVisibility(View.INVISIBLE);

        if(pokemon.getPURIFICADO().equalsIgnoreCase("S"))
            imvPurificado.setVisibility(View.VISIBLE);
        else
            imvPurificado.setVisibility(View.INVISIBLE);


    }

    private void criandoList() {
        Intent intent = getIntent();
        json = intent.getStringExtra("JSON");
        position = intent.getIntExtra("POSITION",0);

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(jsonArray.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setNOME(obj.getString("NOME"));
                    pokemon.setNUMERO(obj.getString("NUMERO"));
                    pokemon.setREGIAO(obj.getString("REGIAO"));
                    pokemon.setSOMBROSO(obj.getString("SOMBROSO"));
                    pokemon.setSHINY(obj.getString("SHINY"));
                    pokemon.setSORTUDO(obj.getString("SORTUDO"));
                    pokemon.setPURIFICADO(obj.getString("PURIFICADO"));
                    pokemon.setPOKEMON_100(obj.getString("POKEMON_100"));
                    pokemon.setPOKEMON_0(obj.getString("POKEMON_0"));
                    pokemon.setFEMEA(obj.getString("FEMEA"));
                    pokemon.setMACHO(obj.getString("MACHO"));
                    pokemonList.add(pokemon);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    private void init() {

        pokemonList = new ArrayList<Pokemon>();
        pokemonDAO = new PokemonDAO(this);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvNumero = (TextView) findViewById(R.id.tvNumero);
        imvShiny = (ImageView) findViewById(R.id.imvShiny);
        imvSombroso = (ImageView) findViewById(R.id.imvSombroso);
        imvSortudo = (ImageView) findViewById(R.id.imvSortudo);
        imvPurificado = (ImageView) findViewById(R.id.imvPurificado);
        imvPokemon = (ImageView) findViewById(R.id.imvPokemon);


    }

    public PegarListas getPegarListas() {
        return pegarListas;
    }

    public void setPegarListas(PegarListas pegarListas) {
        this.pegarListas = pegarListas;
    }
}
