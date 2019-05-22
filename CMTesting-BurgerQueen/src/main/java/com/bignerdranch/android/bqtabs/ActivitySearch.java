package com.bignerdranch.android.bqtabs;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ActivitySearch extends Activity {
    private LinearLayout llContainer;
    private EditText etSearch;
    private ListView lvMenuItems;
    private List<MenuItem> mData;
    public MenuInventory mMenuInventory;
    private int drawableId;


    private ArrayList<MenuItem> mMenuItemArrayList = new ArrayList<MenuItem>();
    private MyAdapter adapter1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        initialize();



        // Add Text Change Listener to EditText
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                adapter1.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initialize() {
        etSearch = (EditText) findViewById(R.id.etSearch);
        lvMenuItems = (ListView)findViewById(R.id.lvMenuItems);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mMenuInventory = MenuInventory.get(getApplicationContext());
        mData = mMenuInventory.getMenuItems();
        mMenuItemArrayList = (ArrayList<MenuItem>)mData;


        adapter1 = new MyAdapter(ActivitySearch.this, mMenuItemArrayList);
        lvMenuItems.setAdapter(adapter1);
    }


    // Adapter Class
    public class MyAdapter extends BaseAdapter implements Filterable {

        private ArrayList<MenuItem> mOriginalValues; // Original Values
        private ArrayList<MenuItem> mDisplayedValues;// Values to be displayed
        private ArrayList<MenuItem> emptyList;
        LayoutInflater inflater;

        public MyAdapter(Context context, ArrayList<MenuItem> mMenuItemArrayList) {
            this.mOriginalValues = mMenuItemArrayList;
            this.mDisplayedValues = mMenuItemArrayList;
            this.emptyList = new ArrayList<>();
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (mData != null){
                int r = mDisplayedValues.size();;
                return r;}
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            private LinearLayout item_menu;
            private TextView tv_name;
            private TextView tv_description;
            private TextView tv_price;
            private ImageView img;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_menu, null);
                holder.item_menu = convertView.findViewById(R.id.menu_id);
                holder.tv_name = convertView.findViewById(R.id.name_item);
                holder.tv_description = convertView.findViewById(R.id.description);
                holder.tv_price = convertView.findViewById(R.id.price);
                holder.img = convertView.findViewById(R.id.img_menuitem);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_name.setText(mDisplayedValues.get(position).getName());
            holder.tv_price.setText(mDisplayedValues.get(position).getPrice());
            holder.tv_description.setText(mDisplayedValues.get(position).getDescription());
            try{
                Class res = R.drawable.class;
                Field field = res.getField(mData.get(position).getImage_path());
                drawableId = field.getInt(null);
                Log.i("ImagePath",mData.get(position).getImage_path());
            }catch (Exception e){
                Log.e("MyTag","Failure to get drawable id",e);
            }
            holder.img.setImageResource(drawableId);

            holder.item_menu.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    Toast.makeText(ActivitySearch.this, mDisplayedValues.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,FilterResults results) {

                    mDisplayedValues = (ArrayList<MenuItem>) results.values; // has the filtered values
                    notifyDataSetChanged();
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    ArrayList<MenuItem> FilteredArrList = new ArrayList<MenuItem>();

                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<MenuItem>(mDisplayedValues);
                    }


                    if (constraint == null || constraint.length() == 0) {

                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            String data = mOriginalValues.get(i).getName();
                            if (data.toLowerCase().startsWith(constraint.toString())) {
                                FilteredArrList.add(new MenuItem(mOriginalValues.get(i).getMenuID(),mOriginalValues.get(i).getPrice(),mOriginalValues.get(i).getName(),mOriginalValues.get(i).getImage_path(),mOriginalValues.get(i).getCategory(),mOriginalValues.get(i).getDescription()));
                            }
                        }
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };
            return filter;
        }
    }
}
