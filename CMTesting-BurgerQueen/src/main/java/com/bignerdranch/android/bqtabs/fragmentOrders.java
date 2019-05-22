package com.bignerdranch.android.bqtabs;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

import java.util.List;

public class fragmentOrders extends Fragment {
    public static BurgerQueenDBHelper BurgerQueenDB = null;
    public OrderList mOrderList;
    View v;
    private RecyclerView orderrecyclerview;
    private RecyclerView couponrecyclerview;
    private List<MenuItem> mData;
    private List<CouponItem>mCoupons;
    CardView cardView;
    int price = 0;
    public CouponList mCouponList;
    TextView cost;
    int position = 0;
    Button coupons, pay;
    private Dialog myDialog;
    private Button btn;



    public fragmentOrders() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.order_fragment,container,false);
        display(v);

        btn = v.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReceiptFragment.class);
                startActivity(intent);
            }
        });


        return v;
    }

    private void display(View v) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int id = pref.getInt("ID", 0);
        //discount = pref.getInt("discount",0);
        orderrecyclerview = v.findViewById(R.id.order_recyclerview);
        //couponrecyclerview = v.findViewById(R.id.couponorder_recyclerview);
        mOrderList = OrderList.get(getActivity());
        mCouponList = CouponList.get(getActivity());
        mData = mOrderList.getOrderMenuItems();
        mCoupons = mCouponList.getCouponItems(id);
        RecyclerViewFavouriteAdapter recyclerAdapter = new RecyclerViewFavouriteAdapter(getActivity(), mData);
        orderrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderrecyclerview.setAdapter(recyclerAdapter);
        TextView textView = v.findViewById(R.id.textview);
        int total = mOrderList.getTotalOrderItems();
        if (mData != null) {
            for (MenuItem m : mData) {
                price += Integer.parseInt(m.getPrice());
                Log.i("Price", String.valueOf(price));
            }
            cost = v.findViewById(R.id.textview);
            //String strTotal = String.valueOf(price);
            //cost.setText(strTotal);
        }
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.coupon_item);
        coupons = (Button) v.findViewById(R.id.button2) ;

        coupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rbtn1 = myDialog.findViewById(R.id.new_coupon);
                RadioButton rbtn2 = myDialog.findViewById(R.id.christmas_coupon);
                TextView r1price = myDialog.findViewById(R.id.rbt1price);
                TextView r2price = myDialog.findViewById(R.id.rbtn2price);
                rbtn1.setText("New User");
                rbtn2.setText("Christmas");
                myDialog.show();
                rbtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String g = String.valueOf(rbtn1.getText());
                        Log.i("Check1",g);
                        for (CouponItem couponItem: mCoupons){
                            if (couponItem.getCouponName().equalsIgnoreCase(g)){
                                int discount = couponItem.getdiscount();
                                Log.i("Checkdis",String.valueOf(discount));
                                r1price.setText(String.valueOf(couponItem.getdiscount()));
                                int p = price;
                                p = price - discount;
                                cost.setText(String.valueOf(p));

                            }

                        }
                        myDialog.dismiss();
                    }
                });
                rbtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String g = String.valueOf(rbtn2.getText());
                        Log.i("Check",g);
                        for (CouponItem couponItem: mCoupons){
                            if (couponItem.getCouponName().equalsIgnoreCase(g)){
                                int disc = couponItem.getdiscount();
                                r2price.setText(String.valueOf(couponItem.getdiscount()));
                                int p = price;
                                p = price - disc;
                                cost.setText(String.valueOf(p));
                            }

                        }
                        myDialog.dismiss();
                    }
                });
                //FragmentManager manager = getFragmentManager();
                //CouponDialog alert = new CouponDialog();
                //Bundle b  = new Bundle();
                //b.putInt("position", position);
                //alert.setArguments(b);
                //alert.show(manager, "alert_dialog_radio");
            }
        });


        //price -= discount;
        textView.setText(String.valueOf(price));


        //cardView = v.findViewById(R.id.card_view);
        //int total = mOrderList.getTotalOrderItems();
        //Log.i("size", String.valueOf(mCoupons.size()));
        /*for (final CouponItem c : mCoupons) {
            String name = c.getCouponName();
            Log.i("Coupon", name);
            final CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setId(mCoupons.indexOf(c));
            checkBox.setText(name);
            Log.i("Indexx", String.valueOf(mCoupons.indexOf(c)));
            cardView.addView(checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //Fix string resource error
                    if (isChecked) {

                        price -= c.getdiscount();
                        Log.i("DiscountA", String.valueOf(price));
                        cost.setText(String.valueOf(price));
                        checkBox.setEnabled(false);

                    }


                }
            });*/
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BurgerQueenDB = new BurgerQueenDBHelper(getActivity());

    }


}

