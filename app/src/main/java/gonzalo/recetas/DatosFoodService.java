package gonzalo.recetas;

import gonzalo.recetas.model.Respuesta;
import gonzalo.recetas.model.RespuestaReceta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DatosFoodService {

    @GET("search")
    Call<Respuesta> recetas(@Query("key") String key);

    @GET("get")
    Call<RespuestaReceta> recetas(@Query("key") String key, @Query("rId") String rId);
}
