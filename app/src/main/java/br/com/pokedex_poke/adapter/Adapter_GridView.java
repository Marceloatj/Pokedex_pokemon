package br.com.pokedex_poke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokedex_poke.R;

import java.util.ArrayList;
import java.util.List;

import br.com.pokedex_poke.model.Pokemon;

public class Adapter_GridView extends ArrayAdapter<Pokemon> {

    private Context context;
    private List<Pokemon> pokemonList;
    private int posicaoEscolhida = 10000000;

    public Adapter_GridView(@NonNull Context context, int resource, List<Pokemon> pokemonList) {
        super(context, R.layout.adapter_gridview , pokemonList);
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Adapter_GridView.ExibeTela tela;
        View v = convertView;

        try{
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.adapter_gridview, parent, false);
            tela = new Adapter_GridView.ExibeTela();

            tela.imvPokemon = (ImageView) v.findViewById(R.id.imvPokemon);

            tela.imvPurificado = (ImageView) v.findViewById(R.id.imvPurificado);
            tela.imvShiny = (ImageView) v.findViewById(R.id.imvShiny);
            tela.imvSombroso = (ImageView) v.findViewById(R.id.imvSombroso);
            tela.imvSortudo = (ImageView) v.findViewById(R.id.imvSortudo);
            tela.tvNome = (TextView) v.findViewById(R.id.tvNome);
            tela.llPokemons = (LinearLayout) v.findViewById(R.id.llpokemons);

            v.setTag(tela);
        }else {
            tela = (Adapter_GridView.ExibeTela) v.getTag();
        }

            Pokemon pokemon = pokemonList.get(position);

            String nomeDrawable = "poke_" + pokemon.getNUMERO();
            int id = context.getResources().getIdentifier("com.example.pokedex_poke:drawable/" + nomeDrawable, null, null);
            tela.imvPokemon.setImageResource(id);

            tela.tvNome.setText(pokemon.getNOME());

            if(pokemon.getSHINY().equalsIgnoreCase("S"))
                tela.imvShiny.setVisibility(View.VISIBLE);
            else
                tela.imvShiny.setVisibility(View.INVISIBLE);

            if(pokemon.getSORTUDO().equalsIgnoreCase("S"))
                tela.imvSortudo.setVisibility(View.VISIBLE);
            else
                tela.imvSortudo.setVisibility(View.INVISIBLE);

            if(pokemon.getSOMBROSO().equalsIgnoreCase("S"))
                tela.imvSombroso.setVisibility(View.VISIBLE);
            else
                tela.imvSombroso.setVisibility(View.INVISIBLE);

            if(pokemon.getPURIFICADO().equalsIgnoreCase("S"))
                tela.imvPurificado.setVisibility(View.VISIBLE);
            else
                tela.imvPurificado.setVisibility(View.INVISIBLE);

            try{
                if(position == posicaoEscolhida){
                    tela.llPokemons.setBackground( context.getResources().getDrawable(R.drawable.borda_selecionado));
                }else{
                    throw new NullPointerException();
                }
            }catch (NullPointerException e){
                tela.llPokemons.setBackground( context.getResources().getDrawable(R.drawable.borda));
            }
            return v;
        }catch (Exception e){
            e.printStackTrace();
            return v;
        }


    }

    public void updateListPokemons(List<Pokemon> newlist) {
                this.pokemonList = newlist;
        //        this.pokemonList.clear();
//        this.pokemonList.addAll(newlist);
        this.notifyDataSetChanged();
    }

    public Integer getPosicaoEscolhida() {
        return this.posicaoEscolhida;
    }

    public void setPosicaoEscolhida(int posicaoEscolhida) {
        this.posicaoEscolhida = posicaoEscolhida;
    }

    public class ExibeTela {

        ImageView imvShiny;
        ImageView imvSortudo;
        ImageView imvPurificado;
        ImageView imvSombroso;
        ImageView imvPokemon;
        LinearLayout llPokemons;
        TextView tvNome;

    }

}
