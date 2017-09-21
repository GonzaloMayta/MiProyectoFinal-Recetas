package gonzalo.recetas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.gson.Gson;

import gonzalo.recetas.model.Food;
import gonzalo.recetas.model.RespuestaReceta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterAtivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private TextView tView2;
    private ImageView iView;
    private TextView tView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_comida);

        tView2 =(TextView)findViewById(R.id.textView2);
        iView =(ImageView)findViewById(R.id.imageView2);
        tView3 =(TextView)findViewById(R.id.textView3);

    }
    @Override
    protected void onStart() {
        super.onStart();
        cargarDatos();

    }
    private void cargarDatos() {
        if (getIntent().getExtras() != null) {
            String foods = getIntent().getStringExtra("comidita");

            Food comida = gson.fromJson(foods, Food.class);

            tView2.setText(comida.getTitle()+" "+comida.getRecipeId());
            Glide.with(this).load(comida.getImageUrl()).into(iView);



            DatosFoodService service = ServiceGenerator.createService(DatosFoodService.class);
            Call<RespuestaReceta> call = service.recetas("7b790470dffb036404df84755ffa8405", comida.getRecipeId());
            call.enqueue(new Callback<RespuestaReceta>() {

                @Override
                public void onResponse(Call<RespuestaReceta> call, Response<RespuestaReceta> response) {
                    //Toast.makeText(CharacterAtivity.this, ""+ new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
                    Log.d("este es el --nuevo", new Gson().toJson(response.body()));

                    if(response.isSuccessful()){
                        tView3.setText(response.body().getRecipe().getIngredients().toString());
                    }else{

                    }

                }

                @Override
                public void onFailure(Call<RespuestaReceta> call, Throwable t) {
                    //aqui viene si la peticion fue ...

                }
            });

        }
    }

}


