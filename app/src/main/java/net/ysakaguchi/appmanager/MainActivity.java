package net.ysakaguchi.appmanager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.ysakaguchi.appmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add("A");
            array.add("B");
            array.add("C");
        }

        adapter = new RecyclerAdapter(this, array, new RecyclerAdapter.OnRecyclerListener() {
            @Override
            public void onRecyclerClicked(View v, int position) {

            }
        });
        binding.recyclerView.setAdapter(adapter);

    }
}
