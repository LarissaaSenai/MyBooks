package br.com.senaijandira.mybooks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Arrays;

import br.com.senaijandira.mybooks.model.Livro;

public class cadastro_Activity extends AppCompatActivity {
    Bitmap LivroCapa;
    ImageView imgLivroCapa;
    EditText txtTitulo;
    EditText txtDescricao;
    private AlertDialog alerta;
    EditText meuEditText;


    private final int COD_REQ_GALERIA = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_);
        imgLivroCapa = findViewById(R.id.imgLivroCapa);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricao = findViewById(R.id.txtDescricao);





    }

    public void abrirGaleria(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent,"Selecione uma imagem"), COD_REQ_GALERIA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_REQ_GALERIA
                && resultCode == Activity.RESULT_OK){

            try{
                InputStream input = getContentResolver().openInputStream(data.getData());

                //converter para bitmap
                LivroCapa = BitmapFactory.decodeStream(input);

               imgLivroCapa.setImageBitmap(LivroCapa);




            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public void salvarLivro(View view) {
        byte[] capa = Utils.toByteArray(LivroCapa);

        String titulo = txtTitulo.getText().toString();

        String descricao = txtDescricao.getText().toString();

        Livro livro = new Livro (0, capa, titulo, descricao);

        // inserir na variável estática da MainActivity
        int tamanhoArray = MainActivity.livros.length;

        MainActivity.livros= Arrays.copyOf(MainActivity.livros, tamanhoArray+1);

        MainActivity.livros[tamanhoArray] = livro;

        if (txtDescricao.getText().toString().trim().equals("")|| txtTitulo.getText().toString().trim().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Fracasso");
            //define a mensagem
            builder.setMessage("Seu Lixo");
            //define um botão como positivo
            builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            //define um botão como negativo.
            builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Sucesso");
            //define a mensagem
            builder.setMessage("parabens cara!");
            //define um botão como positivo
            builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            //define um botão como negativo.
            builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();
        }








    }
}
