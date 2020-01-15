package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Cargo;

import java.util.List;

public class ListaCargoAdapter extends BaseAdapter
{

    private final List<Cargo> cargos;
    private final Activity activity;

    public ListaCargoAdapter(Activity activity, List<Cargo> cargos)
    {
        this.activity = activity;
        this.cargos = cargos;
    }

    @Override
    public int getCount()
    {
        return cargos.size();
    }

    @Override
    public Object getItem(int i)
    {
        return cargos.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return cargos.get(i).getIdCargo();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_cargo,viewGroup,false);


        TextView cargo = v.findViewById(R.id.txtCargoItens);
        Cargo c = cargos.get(i);

        cargo.setText(c.getCargo());

        return v;
    }
}
