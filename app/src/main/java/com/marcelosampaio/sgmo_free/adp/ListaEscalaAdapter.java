package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Escala;

import java.util.List;

public class ListaEscalaAdapter extends BaseAdapter
{

    private final List<Escala> escalas;
    private final Activity activity;

    public ListaEscalaAdapter(Activity activity, List<Escala> escalas)
    {
        this.activity = activity;
        this.escalas = escalas;
    }


    public int getCount()
    {
        return escalas.size();
    }


    public Object getItem(int i)
    {
        return escalas.get(i);
    }


    public long getItemId(int i)
    {
        return escalas.get(i).getIdEscala();
    }


    public View getView(int i, View view, ViewGroup viewGroup)
    {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_escala,viewGroup,false);


        TextView escala = v.findViewById(R.id.txtEscalaItens);

        Escala e = escalas.get(i);

        escala.setText(e.getEscala());

        return v;
    }
}
