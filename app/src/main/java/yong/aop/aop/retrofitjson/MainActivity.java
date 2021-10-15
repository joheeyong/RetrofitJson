package yong.aop.aop.retrofitjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter adapter;
    public int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView1.setAdapter(adapter);

//        getData();

        reto();

    }

    private void reto() {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ("https://jsonplaceholder.typicode.com")
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();
        RetrofitAPI retrofitAPI = retrofit.create (RetrofitAPI.class);
        retrofitAPI.getData ("1").enqueue (new Callback<List<Post>> () {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful ()){
                    List<Post> data = response.body ();
                    for(i=0; i!=data.size (); i++){

//                    Log.d("TEST","성공성공");
//                    Log.d("TEST", String.valueOf (data.get (0).getId ()));
                    Post post = new Post();
                    post.setTitle(String.valueOf (data.get (i).getTitle ()));
                    post.setBody(data.get (i).getBody ());

                    // adapter에 방금 만든 Data 객체를 추가해 넣는다.
                    adapter.addItem(post);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace ();
                Log.d("TEST","실패");
            }
        });
    }

//    private void getData() {
//        List<String> titleList = Arrays.asList("너구리","안성탕면","삼양라면","신라면","튀김우동","짜파게티");
//        List<String> contentList = Arrays.asList("구수한 우동맛입니다.","션합니다.","제일 무난합니다.","마니 매워요~","피씨방에서는 쵝오","짜장라면입니다.");
//
//        for (int i=0;i<6;i++) {
//            Post post = new Post();
//            post.setTitle(titleList.get(i));
//            post.setBody(contentList.get(i));
//            // adapter에 방금 만든 Data 객체를 추가해 넣는다.
//            adapter.addItem(post);
//        }
//
//        // adapter 내용의 값이 변경되었음을 알려준다. 이 함수를 쓰지않으면 data가 노출안된다.
//        // 다만, recyclerView1.setAdapter() 함수가 data를 추가시켜준 뒤에 호출되었다면 정상적으로  data 노출된다.
//        adapter.notifyDataSetChanged();
//    }

//    private void getData() {
//        List<String> titleList = Arrays.asList("너구리","안성탕면","삼양라면","신라면","튀김우동","짜파게티");
//        List<Integer> IdList = Arrays.asList(1,2,3,4,5,6);
//
//        for (int i=0;i<6;i++) {
//            Post post = new Post();
//            post.setTitle(titleList.get(i));
//            post.setId (IdList.get(i));
//
//            // adapter에 방금 만든 Data 객체를 추가해 넣는다.
//            adapter.addItem(post);
//        }
//
//        // adapter 내용의 값이 변경되었음을 알려준다. 이 함수를 쓰지않으면 data가 노출안된다.
//        // 다만, recyclerView1.setAdapter() 함수가 data를 추가시켜준 뒤에 호출되었다면 정상적으로  data 노출된다.
//        adapter.notifyDataSetChanged();
//    }
}