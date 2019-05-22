package com.bignerdranch.android.bqtabs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

import java.util.List;

import static com.bignerdranch.android.bqtabs.Settings.userbase;

public class ReceiptFragment extends AppCompatActivity {
    public static BurgerQueenDBHelper BurgerQueenDB = null;
    View v;
    private RecyclerView receiptrecyclerview;
    private List<MenuItem> mData;
    public OrderList mOrderList;
    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_fragment);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = pref.getInt( "ID", 0);
        User use = userbase.getUserData(id);

        user = findViewById(R.id.username);
        user.setText(use.getUsername());

        receiptrecyclerview = findViewById(R.id.receipt_recyclerview);
        mOrderList = OrderList.get(getApplicationContext());
        mData = mOrderList.getOrderMenuItems();
        ReceiptRecyclerViewAdapter recyclerAdapter = new ReceiptRecyclerViewAdapter(getApplicationContext(), mData);
        receiptrecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        receiptrecyclerview.setAdapter(recyclerAdapter);
    }
}
