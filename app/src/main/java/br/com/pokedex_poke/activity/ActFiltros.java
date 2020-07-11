package br.com.pokedex_poke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.pokedex_poke.R;

import java.util.ArrayList;
import java.util.List;

import br.com.pokedex_poke.model.Pokemon;
import br.com.pokedex_poke.util.Preferences;

public class ActFiltros extends AppCompatActivity {


    private ImageButton imbFiltrar;

    private CheckBox cbSortudo;
    private CheckBox cbShiny;
    private CheckBox cbZero;
    private CheckBox cbCem;
    private CheckBox cbSombroso;
    private CheckBox cbPurificado;

    private String sortudo;

    private RadioButton rbTenho;
    private RadioButton rbNaoTenho;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_filtros);


        init();

        eventoClick();


    }

    private void eventoClick() {

        imbFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sortudo = "SORTUDO";
                String shiny = "SHINY";
                String purificado = "PURIFICADO";
                String sombroso = "SOMBROSO";
                String cem = "POKEMON_100";
                String zero = "POKEMON_0";

                List<String> listselecionados = new ArrayList<String>();

                if (cbSortudo.isChecked())
                    listselecionados.add(sortudo);

                if (cbPurificado.isChecked())
                    listselecionados.add(purificado);

                if (cbShiny.isChecked())
                    listselecionados.add(shiny);

                if (cbSombroso.isChecked())
                    listselecionados.add(sombroso);

                if (cbCem.isChecked())
                    listselecionados.add(cem);

                if (cbZero.isChecked())
                    listselecionados.add(zero);

                String select = "";
                String filtroTEM = " = 'S'";
                String filtroNAO = " = 'N'";
                if(listselecionados.size() > 0){
                    for(int i = 0; i < listselecionados.size(); i++){
                        if(i == 0){
                            select = "Select * from POKEMON where ";
                        }
                        if(i > 0){
                            select += " and ";
                        }
                        if(rbTenho.isChecked())
                            select += listselecionados.get(i) + filtroTEM;
                        else
                            select += listselecionados.get(i) + filtroNAO;
                    }
                    select += " and ";
                }else {

                    select = "Select * from POKEMON where ";

                }

                Preferences.getInstance(ActFiltros.this).setFiltro(select);
                Preferences.getInstance(ActFiltros.this).setFiltrado(true);
                finish();
            }
        });
    }

    private void init() {

        rbNaoTenho = (RadioButton) findViewById(R.id.rbNaoTenho);
        rbTenho = (RadioButton) findViewById(R.id.rbTenho);

        imbFiltrar = (ImageButton) findViewById(R.id.imbFiltrar);

        cbCem = (CheckBox) findViewById(R.id.cbCem);
        cbPurificado = (CheckBox) findViewById(R.id.cbPurificado);
        cbShiny = (CheckBox) findViewById(R.id.cbShiny);
        cbSombroso = (CheckBox) findViewById(R.id.cbSombroso);
        cbSortudo = (CheckBox) findViewById(R.id.cbSortudo);
        cbZero = (CheckBox) findViewById(R.id.cbZero);



    }




}
