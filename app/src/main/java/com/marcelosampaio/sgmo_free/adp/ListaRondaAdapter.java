package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Ronda;

import java.util.List;

public class ListaRondaAdapter extends BaseAdapter
{

    private final List<Ronda> rondas;
    private final Activity activity;
    private DataHelper dataHelper = new DataHelper();

    public ListaRondaAdapter(Activity activity, List<Ronda> rondas)
    {
        this.activity = activity;
        this.rondas = rondas;
    }

    @Override
    public int getCount()
    {
        return rondas.size();
    }

    @Override
    public Object getItem(int i)
    {
        return rondas.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return rondas.get(i).getIdRonda();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_ronda,viewGroup,false);


        TextView ronda = v.findViewById(R.id.tvDtRonda);
        Ronda r = rondas.get(i);

        ronda.setText(dataHelper.convertLongEmStringData(r.getDataInicial()));

        return v;
    }
}
