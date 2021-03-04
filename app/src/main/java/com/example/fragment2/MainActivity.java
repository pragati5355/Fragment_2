package com.example.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mEdtText;
    Button mBtnAddFragment,mBtnRemoveFragment;
    ArrayList<SimpleFragment> mListCounterFragment;
    SimpleFragment mCounterFragment1, mCounterFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListCounterFragment = new ArrayList<>();
        mEdtText = findViewById(R.id.edtCounter);
        mBtnAddFragment = findViewById(R.id.btnAdd);
        mBtnRemoveFragment = findViewById(R.id.btnRemove);

        mCounterFragment1 = (SimpleFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentCounter1);
        mCounterFragment2 = (SimpleFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentCounter2);

        mBtnAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleFragment counterFragment = new SimpleFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("count", Integer.parseInt(mEdtText.getText().toString()));
                counterFragment.setArguments(bundle);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.mainContainer, counterFragment);
                fragmentTransaction.commit();

                mListCounterFragment.add(counterFragment);
            }
        });

        mBtnRemoveFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(mListCounterFragment.get(mListCounterFragment.size() -1))
                        .commit();

                mListCounterFragment.remove(mListCounterFragment.size() -1);
            }
        });
    }
}