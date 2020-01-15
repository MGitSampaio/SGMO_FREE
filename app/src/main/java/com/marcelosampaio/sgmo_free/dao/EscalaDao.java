package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Escala;
import com.marcelosampaio.sgmo_free.model.Funcionario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class EscalaDao {
    private final Calendar calendar = Calendar.getInstance();
    private final DataHelper dataHelper = new DataHelper();
    private final SQLiteDatabase banco;

    public EscalaDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    public void verificaEscala(String dataApp) {

        Cursor cursor;
        cursor = banco.query("funcionario", new String[]{"idFuncionario", "idEscala", "folga1", "folga2"},
                null, null, null, null, null);


        while (cursor.moveToNext()) {
            //pega os valores do recorset
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(cursor.getInt(0));
            funcionario.setIdEscala(cursor.getInt(1));
            funcionario.setFolga1(cursor.getLong(2));
            funcionario.setFolga2(cursor.getLong(3));

            //calcula a diferença de dias entre as datas
            //Date dtApp = dataHelper.converteStringEmData(dataApp);
            long dtPrimeiraFolga = funcionario.getFolga1();
            long dtSegundaFolga = funcionario.getFolga2();
            long diferenca = (dtSegundaFolga - dtPrimeiraFolga + 3600000);
            long dias = (diferenca / 86400000L);

            switch ( funcionario.getIdEscala()) {
                case 1:
                    if (dias == 2)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 2);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 2:
                    if (dias==1)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 5);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }

                if (dias==5)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime( new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 3:
                    if (dias == 6)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 6);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 4:
                    if (dias == 1)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 6);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    if (dias == 6)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 5:
                    if (dias == 1)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 6);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    if (dias == 6)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 6:
                    if (dias == 1)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 6);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    if (dias == 6)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 7:
                    if (dias == 7) {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 8:
                    if (dias == 7)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;
                case 9:
                    if (dias == 7)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 10:
                    if (dias == 7)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 11:
                    if (dias == 7)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 12:
                    if (dias == 7)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 9);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    if (dias == 6)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 8);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

                case 13:
                    if (dias == 7)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 9);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    if (dias == 6)
                    {
                        funcionario.setFolga1(funcionario.getFolga2()); // pega o valor da folga2 e joga em folga1
                        dtSegundaFolga = funcionario.getFolga2();// converte a folga2 em data para poder fazer a adição
                        calendar.setTime(new Date(dtSegundaFolga));
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 8);
                        funcionario.setFolga2(calendar.getTimeInMillis());
                    }
                    atualizaEscala(funcionario);
                    break;

            }
        }
        cursor.close();
    }

    private void atualizaEscala(Funcionario funcionario){
        ContentValues values = new ContentValues();
        values.put("idFuncionario",funcionario.getIdFuncionario());
        values.put("folga1",funcionario.getFolga1());
        values.put("folga2",funcionario.getFolga2());
        banco.update("funcionario", values, "idFuncionario = ?", new String[]
                {String.valueOf(funcionario.getIdFuncionario())});
    }

    public int contarEscalas(){

        String sql = "Select * from escala";

        Cursor cursor = banco.rawQuery(sql,null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        if(count == 0){
            inserirEscala();
        }

        return count;
    }

    private void inserirEscala(){
        ContentValues values = new ContentValues();
        String escala[] = new String[14];

        escala[0]="12x36";
        escala[1]="4x2";
        escala[2]="5x1";
        escala[3]="5x2 - Fixa";
        escala[4]="5x2 - SD";
        escala[5]="5x2 - SDF";
        escala[6]="6x1 - Fixa";
        escala[7]="6x1 - S";
        escala[8]="6x1 - S+F";
        escala[9]="6x1 - D";
        escala[10]="6x1 - D+F";
        escala[11]="6x1 - Troca SD";
        escala[12]="6x1 - Troca SD + F";

        for (int i = 0; i < 13; i++)
        {
            values.put("escala",escala[i]);
            banco.insert("escala",null,values);
            System.out.println(escala[i]);
        }
        banco.close();
    }




    public List<Escala> listarEscalas()
    {
        List<Escala> lstEscala = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * from Escala Order by idEscala",null);

        while (cursor.moveToNext())
        {
            Escala escala = new Escala();
            escala.setIdEscala(cursor.getInt(0));
            escala.setEscala(cursor.getString(1));
            lstEscala.add(escala);
        }
        cursor.close();
        return lstEscala;

    }
}
