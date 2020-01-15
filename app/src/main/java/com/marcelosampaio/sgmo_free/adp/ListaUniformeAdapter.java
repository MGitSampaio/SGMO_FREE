package com.marcelosampaio.sgmo_free.adp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Uniforme;

import java.util.Date;
import java.util.List;

public class ListaUniformeAdapter extends BaseAdapter {

    private Date date = new Date(System.currentTimeMillis());
    long dataAtual = date.getTime();
    long dataFinal = dataAtual;
    private final List<Uniforme> uniformes;
    private final Activity activity;

    public ListaUniformeAdapter(Activity activity, List<Uniforme> uniformes) {
        this.activity = activity;
        this.uniformes = uniformes;
    }

    @Override
    public int getCount() {
        return uniformes.size();
    }

    @Override
    public Object getItem(int i) {
        return uniformes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return uniformes.get(i).getIdUniforme();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_uniforme, null);

        ImageView imgCalcado;
        ImageView imgCalca;
        ImageView imgCamisa;
        ImageView imgBlusa;
        ImageView imgCobertura;

        TextView re = v.findViewById(R.id.tvReUniformeItens);
        TextView nome = v.findViewById(R.id.tvNomeUniformesItens);



        imgCalcado = v.findViewById(R.id.imgCalcadoLista);
        imgCalca = v.findViewById(R.id.imgCalcaLista);
        imgCamisa = v.findViewById(R.id.imgCamisaLista);
        imgBlusa = v.findViewById(R.id.imgBlusaLista);
        imgCobertura = v.findViewById(R.id.imgCoberturaLista);

        Uniforme f = uniformes.get(i);
        re.setText(f.getRe());
        nome.setText(f.getNomeFuncionario());


        //==========================================================================================

        if (f.getDtCalcado() > 1500L && f.getDtCalcado() <= dataAtual)
            imgCalcado.setImageResource(R.drawable.ic_calcado24_v);

        if (f.getDtCalcado() >= dataAtual && f.getDtCalcado() < dataFinal + (30 * 86400000L))
            imgCalcado.setImageResource(R.drawable.ic_calcado24_av);

        //==========================================================================================
        if (f.getDtCalca() > 1500L && f.getDtCalca() <= dataAtual)
            imgCalca.setImageResource(R.drawable.ic_calca24_v);

        if (f.getDtCalca() >= dataAtual && f.getDtCalca() < dataFinal + (30 * 86400000L))
            imgCalca.setImageResource(R.drawable.ic_calca24_av);

        //==========================================================================================
        if (f.getDtCamisa() > 1500L && f.getDtCamisa() <= dataAtual)
            imgCamisa.setImageResource(R.drawable.ic_camisa24_v);

        if (f.getDtCamisa() >= dataAtual && f.getDtCamisa() < dataFinal + (30 * 86400000L))
            imgCamisa.setImageResource(R.drawable.ic_camisa24_av);

        //==========================================================================================
        if (f.getDtBlusa() > 1500L && f.getDtBlusa() <= dataAtual)
            imgBlusa.setImageResource(R.drawable.ic_blusa24_v);

        if (f.getDtBlusa() >= dataAtual && f.getDtBlusa() < dataFinal + (30 * 86400000L))
            imgBlusa.setImageResource(R.drawable.ic_blusa24_av);

        //==========================================================================================
        if (f.getDtCobertura() > 1500L && f.getDtCobertura() <= dataAtual)
            imgCobertura.setImageResource(R.drawable.ic_cobertura24_v);

        if (f.getDtCobertura() >= dataAtual && f.getDtCobertura() < dataFinal + (30 * 86400000L))
            imgCobertura.setImageResource(R.drawable.ic_cobertura24_av);

        return v;

    }


}
