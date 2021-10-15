package yong.aop.aop.retrofitjson;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/posts")
    Call<List<Post>> getData(@Query("userId") String id);

}
