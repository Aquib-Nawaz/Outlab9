package com.example.out9;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.out9.adapter.MainArticleAdapter;
import com.example.out9.model.Article;
import com.example.out9.model.ResponseModel;
import com.example.out9.rests.APIInterface;
import com.example.out9.rests.ApiClient;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "5b28d8e9636f4116a119663d77e11536";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView mainRecycler = findViewById(R.id.activity_main_rv);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final MainArticleAdapter[] mainArticleAdapter = {new MainArticleAdapter(new List<Article>() {
            @Override public int size() { return 0;}
            @Override public boolean isEmpty() { return false; }
            @Override public boolean contains(@Nullable Object o) { return false; }
            @NonNull @Override public Iterator<Article> iterator() { return null; }
            @NonNull @Override public Object[] toArray() { return new Object[0]; }
            @NonNull @Override public <T> T[] toArray(@NonNull T[] a) { return null; }
            @Override public boolean add(Article article) { return false;}
            @Override public boolean remove(@Nullable Object o) { return false; }
            @Override public boolean containsAll(@NonNull Collection<?> c) { return false; }
            @Override public boolean addAll(@NonNull Collection<? extends Article> c) { return false; }
            @Override public boolean addAll(int index, @NonNull Collection<? extends Article> c) { return false; }
            @Override public boolean removeAll(@NonNull Collection<?> c) { return false; }
            @Override public boolean retainAll(@NonNull Collection<?> c) { return false; }
            @Override public void clear() { }
            @Override public Article get(int index) { return null; }
            @Override public Article set(int index, Article element) { return null; }
            @Override public void add(int index, Article element) { }
            @Override public Article remove(int index) { return null; }
            @Override public int indexOf(@Nullable Object o) { return 0; }
            @Override public int lastIndexOf(@Nullable Object o) { return 0; }
            @NonNull @Override public ListIterator<Article> listIterator() { return null; }
            @NonNull @Override public ListIterator<Article> listIterator(int index) { return null; }
            @NonNull @Override public List<Article> subList(int fromIndex, int toIndex) { return null; }
        })};
        mainRecycler.setLayoutManager(linearLayoutManager);
        mainRecycler.setAdapter(mainArticleAdapter[0]);
        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews("en",API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                Log.d("JSON", String.valueOf(response));
                if (response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    Log.d("SIZE", String.valueOf(articleList.size()));
                    if (articleList.size() > 0) {
                        mainArticleAdapter[0] = new MainArticleAdapter(articleList);
                        mainRecycler.setAdapter(mainArticleAdapter[0]);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("out", t.toString());
            }
        });
    }
}
