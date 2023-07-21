package com.areaonline.user.btm_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.areaonline.user.Adapter.Famousshop_Adapter;
import com.areaonline.user.Adapter.HotDealAdapter;
import com.areaonline.user.Adapter.ShopitemAdapter;
import com.areaonline.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Shop_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Shop_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageSlider imageSlider;
    RecyclerView rec_shop,rec_shop2,rec_shop3,rec_shop4,rec_shop5;
    ArrayList<SlideModel> imageList = new ArrayList<>();


    public Shop_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Shop_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Shop_Fragment newInstance(String param1, String param2) {
        Shop_Fragment fragment = new Shop_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        initializedwidget(view);
        return view;
    }

    private void initializedwidget(View view) {
        imageSlider = view.findViewById(R.id.imageSlider);
        rec_shop = view.findViewById(R.id.rec_shop);
        rec_shop2 = view.findViewById(R.id.rec_shop2);
        rec_shop3 = view.findViewById(R.id.rec_shop3);
        rec_shop4 = view.findViewById(R.id.rec_shop4);
        rec_shop5 = view.findViewById(R.id.rec_shop5);
        setrecyclerview();
        setslideritem();
    }

    private void setrecyclerview() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(R.drawable.hotel_img);
        arrayList.add(R.drawable.restaurant_img);
        arrayList.add(R.drawable.bar_img);
        arrayList.add(R.drawable.fitness_img);
        arrayList.add(R.drawable.shop_img);
        arrayList.add(R.drawable.hotel_img);
        arrayList.add(R.drawable.restaurant_img);
        arrayList.add(R.drawable.bar_img);
        arrayList.add(R.drawable.fitness_img);
        arrayList.add(R.drawable.shop_img);

        ArrayList namelist = new ArrayList();
        namelist.add("Hotel");
        namelist.add("Restaurant");
        namelist.add("Bar");
        namelist.add("Fitness");
        namelist.add("Shop");
        namelist.add("Hotel");
        namelist.add("Restaurant");
        namelist.add("Bar");
        namelist.add("Fitness");
        namelist.add("Shop");
//        ShopitemAdapter shopitemAdapter = new ShopitemAdapter(Shop_Fragment.this.getActivity(),arrayList,namelist);
//        rec_shop.setAdapter(shopitemAdapter);

        ArrayList hotdeallist = new ArrayList();
        hotdeallist.add(R.drawable.buyproduct_1);
        hotdeallist.add(R.drawable.buyproduct_2);
        hotdeallist.add(R.drawable.buyproduct_3);
        hotdeallist.add(R.drawable.buyproduct_4);
        hotdeallist.add(R.drawable.buyproduct_5);
        hotdeallist.add(R.drawable.buyproduct_6);



        ArrayList famousshoplist = new ArrayList();
        famousshoplist.add(R.drawable.famousshop_img);
        famousshoplist.add(R.drawable.famousshop_img2);
        famousshoplist.add(R.drawable.famousshop_img3);
        famousshoplist.add(R.drawable.famousshop_img4);

        ArrayList f_namelist = new ArrayList();
        f_namelist.add("Victoria Secretes");
        f_namelist.add("Louis Vuitton");
        f_namelist.add("Burberry");
        f_namelist.add("Pinko");

//        Famousshop_Adapter famousshopAdapter = new Famousshop_Adapter(Shop_Fragment.this.getActivity(),famousshoplist,f_namelist);
//        rec_shop3.setAdapter(famousshopAdapter);

        ArrayList popularhotellist = new ArrayList();
        popularhotellist.add(R.drawable.pop_hotel1);
        popularhotellist.add(R.drawable.pop_hotel2);
        popularhotellist.add(R.drawable.pop_hotel3);
        popularhotellist.add(R.drawable.pop_hotel4);

        ArrayList hotel_namelist = new ArrayList();
        hotel_namelist.add("Hotel Mariott");
        hotel_namelist.add(" Hotel Concorde");
        hotel_namelist.add("Hotel La Defanse");
        hotel_namelist.add("Hotel Carlton");

//        Famousshop_Adapter famousshopAdapter2 = new Famousshop_Adapter(Shop_Fragment.this.getActivity(),popularhotellist,hotel_namelist);
//        rec_shop4.setAdapter(famousshopAdapter2);


        ArrayList toprestrolist = new ArrayList();
        toprestrolist.add(R.drawable.toprest_1);
        toprestrolist.add(R.drawable.toprest_2);
        toprestrolist.add(R.drawable.toprest_3);
        toprestrolist.add(R.drawable.toprest_4);

        ArrayList restro_namelist = new ArrayList();
        restro_namelist.add("Da Alfredo");
        restro_namelist.add("Bistroter");
        restro_namelist.add("Da Luigi");
        restro_namelist.add("Marco King");

//        Famousshop_Adapter famousshopAdapter3 = new Famousshop_Adapter(Shop_Fragment.this.getActivity(),toprestrolist,restro_namelist);
//        rec_shop5.setAdapter(famousshopAdapter3);

    }



    @SuppressLint("UseCompatLoadingForDrawables")
    private void setslideritem() {
        imageList.add(new SlideModel(R.drawable.hotel_img, ScaleTypes.FIT)) ;
        imageList.add(new SlideModel(R.drawable.restaurant_img, ScaleTypes.FIT)) ;
        imageList.add(new SlideModel(R.drawable.bar_img, ScaleTypes.FIT)) ;
        imageList.add(new SlideModel(R.drawable.event_img, ScaleTypes.FIT)) ;
        imageList.add(new SlideModel(R.drawable.fitness_img, ScaleTypes.FIT)) ;


        imageSlider.setImageList(imageList);

    }
}