package ro.mobileacademy.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.mobileacademy.myapplication.adapters.ReposAdapter;
import ro.mobileacademy.myapplication.model.Repo;
import ro.mobileacademy.myapplication.network.NetworkManager;

public class MainActivity extends AppCompatActivity {


    private RecyclerView myRecyclerView;

    private ProgressBar loader;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(MainActivity.this, R.string.title_home, Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        myRecyclerView = findViewById(R.id.myRecyclerView);
        loader = findViewById(R.id.loader);

        final ReposAdapter articlesAdapter = new ReposAdapter(new ArrayList<Repo>());


        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(articlesAdapter);

        NetworkManager.getInstance().getService().listRepos("mobileacademy")
                .enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        loader.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            articlesAdapter.updateDataSet(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        loader.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
