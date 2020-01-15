package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Unidade;

import java.util.List;

public class ListaUnidadeAdapter extends BaseAdapter
{

    private final Activity activity;
    private final List<Unidade> unidades;
    public ListaUnidadeAdapter(Activity activity, List<Unidade>unidades)
    {
        this.activity = activity;
        this.unidades = unidades;
    }

    @Override
    public int getCount()
    {
        return unidades.size();
    }

    @Override
    public Object getItem(int i)
    {
        return unidades.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return unidades.get(i).getIdUnidade();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_unidade,viewGroup,false);

        TextView cliente = v.findViewById(R.id.ClienteUnidadesItens);
        TextView unidade = v.findViewById(R.id.NomeUnidadeItens);

        Unidade uni = unidades.get(i);
        cliente.setText(uni.getNomeCliente());
        unidade.setText(uni.getNomeUnidade());


        return v ;
    }
}
