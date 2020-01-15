package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Posto;

import java.util.List;

class ListaPostoAdapter extends BaseAdapter
{

    private final Activity activity;
    private final List<Posto>postos;


    public ListaPostoAdapter(Activity activity, List<Posto>postos)
    {
        this.activity = activity;
        this.postos = postos;

    }

    @Override
    public int getCount()
    {
        return postos.size();
    }

    @Override
    public Object getItem(int i)
    {
        return postos.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return postos.get(i).getIdPosto();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = activity.getLayoutInflater().inflate(R.layout.itens_posto,viewGroup,false);


        TextView nomeCliente = v.findViewById(R.id.tvNomeClientePostoItens);
        TextView nomePosto = v.findViewById(R.id.tvNomePostoItens);
        TextView nomeUnidade = v.findViewById(R.id.tvNomeUnidadePostoItens);
        TextView idPosto = v.findViewById(R.id.tvIdPostoItens);
        TextView idUnidade = v.findViewById(R.id.tvIdUnidadePostoItens);
        TextView idCliente = v.findViewById(R.id.tvIdClientePostoItens);


        Posto p = postos.get(i);

        nomeUnidade.setText(p.getNomeUnidade());
        nomePosto.setText(p.getNomePosto());
        nomeCliente.setText(p.getNomeCliente());

        idPosto.setText(String.valueOf(p.getIdPosto()));
        idUnidade.setText(String.valueOf(p.getIdUnidade()));
        idCliente.setText(String.valueOf(p.getIdCliente()));


        return v;
    }
}