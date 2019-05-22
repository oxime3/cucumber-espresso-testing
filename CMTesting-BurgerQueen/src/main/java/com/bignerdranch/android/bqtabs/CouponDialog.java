package com.bignerdranch.android.bqtabs;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class CouponDialog extends DialogFragment {
    AlertPositiveListener alertPositiveListener;
    ArrayList<CouponItem> mCoupons;

    interface AlertPositiveListener {
        public void onPositiveClick(int position);
    }

    DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            AlertDialog alert = (AlertDialog)dialog;
            int position = alert.getListView().getCheckedItemPosition();
            alertPositiveListener.onPositiveClick(position);
        }
    };

   // @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
//        int id = pref.getInt("ID", 0);
//         mCoupons = (ArrayList) CouponList.get(getContext()).getCouponItems(id);
//
//        Bundle bundle = getArguments();
//        int position = bundle.getInt("position");
//
//        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
//        b.setTitle("Choose your Coupon");
//
//
//        b.setSingleChoiceItems((ListAdapter) mCoupons, position, null);
//        b.setPositiveButton("OK",positiveListener);
//        b.setNegativeButton("Cancel", null);
//        AlertDialog d = b.create();
//
//        return d;
//    }

}
