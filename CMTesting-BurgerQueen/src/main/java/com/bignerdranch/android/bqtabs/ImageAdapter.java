package com.bignerdranch.android.bqtabs;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

public class ImageAdapter extends PagerAdapter{
   private Context mContext;
   private  int[] mImageIds = new int[] {R.drawable.smnelse, R.drawable.special2};
   private List<MenuItem> specialList;
   public MenuInventory mMenuInventory;
   private int drawableId;
    private OrderList mOrderList;

           ImageAdapter(Context context){
       mContext = context;
           }
    @Override
    public int getCount() {
        return mImageIds.length;
    }

    public List<MenuItem> getSpecialList(){
               mMenuInventory = MenuInventory.get(mContext);
        specialList = mMenuInventory.getCategoryMenuItems("Special");
        return specialList;
    }
    ;

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        try{
            Class res = R.drawable.class;
            Field field = res.getField(getSpecialList().get(position).getImage_path());
            drawableId = field.getInt(null);
            Log.i("ImagePath",getSpecialList().get(position).getImage_path());
        }catch (Exception e){
            Log.e("MyTag","Failure to get drawable id",e);
        }
        imageView.setImageResource(drawableId);
        container.addView(imageView, 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iID = getSpecialList().get(position).getMenuID();
                if (iID != null){
                    int tger = Integer.valueOf(iID);
                    mOrderList = mOrderList.get(mContext);
                    mOrderList.addOrderItem(tger);
                    Toast.makeText(mContext,"Special added to the Orderlist",Toast.LENGTH_SHORT).show();
                }


            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

}
