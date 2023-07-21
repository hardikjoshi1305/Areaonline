package com.areaonline.user.activity;

import static com.areaonline.user.activity.Detail_Page_Activity.lin_totalselected;
import static com.areaonline.user.activity.Detail_Page_Activity.tv_totalitemselected;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.user.Adapter.Product_Adapter;
import com.areaonline.user.fragment.Search_Fragment;
import com.areaonline.user.modal.AddCart_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.areaonline.utils.TouchImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductView_Activity extends AppCompatActivity {
    ImageView iv_back, iv_product;
    TextView tv_name, tv_type, tv_desc;
    TextView tv_address, tv_contact2, tv_email, tv_website, tv_buynow;
    RelativeLayout rel_buy, iv_share;
    String number;
    String companyname;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        initializedwidget();
    }

    @SuppressLint("SetTextI18n")
    private void initializedwidget() {
        iv_back = findViewById(R.id.iv_back);
        iv_product = findViewById(R.id.iv_product);
        tv_name = findViewById(R.id.tv_name);
        tv_type = findViewById(R.id.tv_type);
        tv_desc = findViewById(R.id.tv_desc);
        tv_address = findViewById(R.id.tv_address);
        tv_contact2 = findViewById(R.id.tv_contact2);
        tv_email = findViewById(R.id.tv_email);
        tv_website = findViewById(R.id.tv_website);
        rel_buy = findViewById(R.id.rel_buy);
        tv_buynow = findViewById(R.id.tv_buynow);
        iv_share = findViewById(R.id.iv_share);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        String listingimg = getIntent().getStringExtra("listingimg");
        companyname = getIntent().getStringExtra("companyname");
        String category = getIntent().getStringExtra("category");
        number = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");
        String desc = getIntent().getStringExtra("desc");
        String website = getIntent().getStringExtra("website");
        String address = getIntent().getStringExtra("address");
        String CMS = getIntent().getStringExtra("CMS");
        String domain = getIntent().getStringExtra("domain");
        String l_id = getIntent().getStringExtra("l_id");
        String ps_id = getIntent().getStringExtra("ps_id");
        String m_idv = PrefUtils.getPref(ProductView_Activity.this, CONSTANT.PREF_MID);//getIntent().getStringExtra("m_idv");
        Log.e("setdata:view ", CMS);
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        onResume();
                    }
                }, 1000);


            }
        });
        String servicesSlug = getIntent().getStringExtra("servicesSlug");
        String price = getIntent().getStringExtra("price");
        Glide.with(ProductView_Activity.this).load("https://www.areaonline.in/uploads/services/" + listingimg).placeholder(getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(iv_product);
        if (!price.equalsIgnoreCase("")) {
            rel_buy.setVisibility(View.VISIBLE);
            tv_buynow.setText("Buy Now " + "(" + getString(R.string.rs) + price + ")");
        }
        tv_name.setText(companyname);
        tv_type.setText(category);
        tv_desc.setText(desc);
        tv_address.setText(address);
        tv_contact2.setText(number);
        tv_email.setText(email);
        if (website.equalsIgnoreCase("")) {
            tv_website.setText("N/A");
        } else {
            tv_website.setText(website);
        }
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                if (domain.equalsIgnoreCase("")) {
                    msg = "Take a Look at this " + companyname + " on Areaonline " + "https://www.areaonline.in/" + CMS;

                } else {
                    msg = "Take a Look at this " + companyname + " on Areaonline " + domain;
                }
                Uri bmpUri = getLocalBitmapUri(iv_product);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                    Toast.makeText(ProductView_Activity.this, "Sharing failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rel_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderitemfromcart("1", l_id,m_idv,ps_id);

//                if (!number.contains("+91")) {
//                    number = "+91" + number;
//                }
//                PackageManager packageManager = getPackageManager();
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                String message = "Hello! I want to Buy " + companyname + " : " + price;
//
//                try {
//                    String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + URLEncoder.encode(message, "UTF-8");
//                    i.setPackage("com.whatsapp");
//                    i.setData(Uri.parse(url));
////                        if (i.resolveActivity(packageManager) != null) {
//                    startActivity(i);
////                        }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        iv_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup("https://www.areaonline.in/uploads/services/" + listingimg);
            }
        });
        tv_contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + email));
                startActivity(intent);
            }
        });
        tv_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!website.equalsIgnoreCase("")) {
                    Intent viewIntent2 =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(website));
                    startActivity(viewIntent2);
                }
            }
        });
    }
    ApiInterface apiInterface;
    private void orderitemfromcart(String qty, String l_id, String m_id, String ps_id) {
        CommandMethod.showProgressDialog(ProductView_Activity.this);
        String quantity = qty ;
        Log.e("orderitemfromcart: ",quantity );
        HashMap map = new HashMap();
        map.put("qty", quantity);
        map.put("shop", l_id);
        map.put("m_id", m_id);
        map.put("product", ps_id);
        Call<AddCart_Response> call1 = apiInterface.checkcart( map);
        call1.enqueue(new Callback<AddCart_Response>() {
            @Override
            public void onResponse(Call<AddCart_Response> call, Response<AddCart_Response> response) {
                CommandMethod.hideProgressDialog(ProductView_Activity.this);
                AddCart_Response banners_response = response.body();
                Gson gson = new Gson();
                String menusResponse2 = gson.toJson(response.body());
                Log.e("orderitem_response", menusResponse2);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && banners_response.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + banners_response.getMessage());
                    String responseCode = banners_response.getMessage();
                    if (responseCode != null && responseCode.equals("404")) {
                        Toast.makeText(ProductView_Activity.this, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(ProductView_Activity.this,ViewCart_Activity.class));
                    }
                } else {
                    Toast.makeText(ProductView_Activity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCart_Response> call, Throwable t) {
                Toast.makeText(ProductView_Activity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(ProductView_Activity.this);
                call.cancel();
            }
        });
    }
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = FileProvider.getUriForFile(ProductView_Activity.this, "com.areaonline.fileprovider", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    private void popup(String image) {
        Log.e("popup: ", "fff");
        Dialog dialog = new Dialog(ProductView_Activity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        LayoutInflater factory = LayoutInflater.from(ProductView_Activity.this);
        final View view = factory.inflate(R.layout.item_image_popup, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        dialog.setContentView(view);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TouchImageView dialog_imageview = dialog.findViewById(R.id.dialog_imageview);
        Glide.with(ProductView_Activity.this).load(image).into(dialog_imageview);
        ImageButton btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        dialog.show();
    }

}