package gonzalo.recetas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.gson.Gson;

import java.util.ArrayList;

import gonzalo.recetas.adapter.FoodAdapter;
import gonzalo.recetas.model.Food;
import gonzalo.recetas.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityComida extends AppCompatActivity implements FoodAdapter.onComidaSelectedListener{

    private static final Gson gson = new Gson();

    private RecyclerView foodsRecyclerView;
    private FoodAdapter foodAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_comidas);

        foodsRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView_comidas);
        foodsRecyclerView.setHasFixedSize(true);
        foodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodAdapter = new FoodAdapter(this, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        foodsRecyclerView.setLayoutManager(layoutManager);
        foodsRecyclerView.setAdapter(foodAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        swipeRefreshLayout.setRefreshing(true);
        cargarDatos();
    }

    private void cargarDatos() {
        DatosFoodService service = ServiceGenerator.createService(DatosFoodService.class);

        Call<Respuesta> call;
        call = service.recetas("2c780541ba163193c556b5c1b12ea320");
         call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                swipeRefreshLayout.setRefreshing(false);
                if(response.isSuccessful()){
                    ArrayList<Food> listita = response.body().getRecipes();
                    foodAdapter.setDataset(listita);
                }
            }
            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onComidaSelectedListener(Food miinterface) {
        Intent intent = new Intent(this, CharacterAtivity.class);
        intent.putExtra("comidita", gson.toJson(miinterface));
        startActivity(intent);

    }
}
