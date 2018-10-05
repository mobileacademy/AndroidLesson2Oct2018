package ro.mobileacademy.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.mobileacademy.myapplication.adapters.ArticlesAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private RadioGroup myRadioGroup;

    private Switch mySwitch;

    private CheckBox myCheckbox;

    private Spinner mySpinner;

    private RecyclerView myRecyclerView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    myRadioGroup.getCheckedRadioButtonId();

                    Toast.makeText(MainActivity.this, R.string.title_home, Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    mySwitch.isChecked();
                    myCheckbox.isChecked();

                    final Snackbar snackbar = Snackbar.make(mTextMessage, "Switch is " + mySwitch.isChecked(), Snackbar
                            .LENGTH_INDEFINITE);
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        myCheckbox = findViewById(R.id.mycheckbox);
        myRadioGroup = findViewById(R.id.myradiogroup);
        mySwitch = findViewById(R.id.myswitch);
        mySpinner = findViewById(R.id.myspinner);
        myRecyclerView = findViewById(R.id.myRecyclerView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.test));
        mySpinner.setAdapter(adapter);

        final ArticlesAdapter articlesAdapter = new ArticlesAdapter(mockDataSet());
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(articlesAdapter);

        findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articlesAdapter.addItem(1, "inserted value");
            }
        });
        findViewById(R.id.remove_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articlesAdapter.removeItem(1);
            }
        });
    }

    private List<String> mockDataSet() {
        List<String> dataSet = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) {
            dataSet.add(Integer.toString(i));
        }
        return dataSet;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
