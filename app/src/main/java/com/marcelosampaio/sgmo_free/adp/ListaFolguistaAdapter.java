package com.marcelosampaio.sgmo_free.adp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Funcionario;

import java.util.Date;
import java.util.List;


public class ListaFolguistaAdapter extends BaseAdapter {
    private Date date = new Date(System.currentTimeMillis());

    private final List<Funcionario> funcionarios;
    private final Activity activity;

    public ListaFolguistaAdapter(Activity activity, List<Funcionario> funcionarios) {
        this.activity = activity;
        this.funcionarios = funcionarios;
    }

    @Override
    public int getCount() {
        return funcionarios.size();
    }

    @Override
    public Object getItem(int i) {
        return funcionarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return funcionarios.get(i).getIdFuncionario();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_funcionario, null);

        TextView re = v.findViewById(R.id.tvRe);
        TextView nome = v.findViewById(R.id.tvNome);

        Funcionario f = funcionarios.get(i);
        re.setText(f.getRe());
        nome.setText(f.getNomeFuncionario());


        return v;

    }


}
