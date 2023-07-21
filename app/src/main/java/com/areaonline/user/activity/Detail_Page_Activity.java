package com.areaonline.user.activity;

import static com.areaonline.utils.CONSTANT.PREF_MID;
import static com.areaonline.utils.CONSTANT.USER_COMPANY_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.user.Adapter.CommentList_Adapter;
import com.areaonline.user.Adapter.Listing_Adapter;
import com.areaonline.user.Adapter.Product_Adapter;
import com.areaonline.user.Adapter.SliderAdapterExample;
import com.areaonline.R;
import com.areaonline.user.fragment.WelcomeIntro_Fragment;
import com.areaonline.user.modal.ListDetail_Response;
import com.areaonline.user.modal.Listing_data_Response;
import com.areaonline.user.modal.PostRating_Response;
import com.areaonline.user.modal.VerifyVendor_OTP;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.areaonline.utils.TouchImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.smarteist.autoimageslider.SliderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Page_Activity extends AppCompatActivity {

    TextView tv_home, tv_product, tv_contact, tv_availability, tv_rating, tv_ratingmain;
    NestedScrollView scroll;
    SliderView img_slider;
    ArrayList imageList = new ArrayList<>();
    CollapsingToolbarLayout toolbar;
    FloatingActionButton fab, fab_share, fab_chat;
    LinearLayout line_socialconnect;
    String cmpname = "";
    LinearLayout line_home, line_contactus, line_contact2, line_rating, line_rating2, line_available, line_available2, line_product;
    RelativeLayout about;
    ArrayList<HashMap<String, String>> productdata = new ArrayList<>();
    String m_id;
    String companyslug;
    ApiInterface apiInterface;
    String number;
    String emailaddress, website;
    String IMAGE;
    ImageView iv_youtubeimg;
    RelativeLayout rel_youtubeimg;
    Button cirsubmitrate;
    TextInputEditText et_SComment;
    RatingBar ratingbar, ratingbarmain;
    String lid;
    RecyclerView rec_comment;
    TextView tv_ratingcount;
    YouTubePlayerView youtube_playerview;
    int restart = 0;
    String cms = "";
    String nameofcommpnu = "";
    String whatsappnumber = "";
    String chat_compname = "";
    String chat_mid = "";
    String   product_img = "";
    String domain = "";
    SwipeRefreshLayout swipeRefreshLayout;
    public static  TextView tv_totalitemselected;
    CircleImageView iv_listingimg, iv_call, iv_whatsapp, iv_email;
    CircleImageView iv_facebook, iv_twitter, iv_linkdln, iv_instagram, iv_wordpress, iv_pint, iv_tumblr, iv_youtb;
    TextView tv_companyslug, tv_category, tv_contact2, tv_email, tv_website, tv_desc, tv_sun, tv_mon, tv_tue, tv_wed, tv_thu, tv_fri, tv_sat;
    public static TextView tv_address;
    RecyclerView rec_product;
    public static LinearLayout lin_totalselected;
    String tumblr, facebook, twitter, instagram, wordpress, pint, linkdin, youtube;
    String videourl = "";
    String src;
    String VideoID;
    LinearLayout rel_slider;
    AppBarLayout appb;
    CardView btn_viewcart;
    YouTubePlayer yvplayer;
    AbstractYouTubePlayerListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__page);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        if (restart == 0) {
            m_id = getIntent().getStringExtra("m_idcomp");
            companyslug = getIntent().getStringExtra("companyslug");
            IMAGE = getIntent().getStringExtra("listingimg");
            lid = getIntent().getStringExtra("lid");
            if (m_id != null) {
                PrefUtils.setPref(Detail_Page_Activity.this, "MMID", m_id);
                PrefUtils.setPref(Detail_Page_Activity.this, "CCSLUG", companyslug);
                PrefUtils.setPref(Detail_Page_Activity.this, "IG", IMAGE);
                PrefUtils.setPref(Detail_Page_Activity.this, "LLID", lid);
            }
        }
        initializedwidget();
    }

    @SuppressLint("SetTextI18n")
    private void initializedwidget() {
        tv_home = findViewById(R.id.tv_home);
        line_home = findViewById(R.id.lin_home);
        line_contactus = findViewById(R.id.line_contactus);
        line_contact2 = findViewById(R.id.line_contact2);
        about = findViewById(R.id.about);
        youtube_playerview = findViewById(R.id.youtube_playerview);
        line_rating2 = findViewById(R.id.line_rating2);
        line_available2 = findViewById(R.id.line_availible2);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        fab_share = findViewById(R.id.fab_share);
        fab_chat = findViewById(R.id.fab_chat);
        tv_totalitemselected = findViewById(R.id.tv_totalitemselected);
        lin_totalselected = findViewById(R.id.lin_totalselected);
        btn_viewcart = findViewById(R.id.btn_viewcart);
        line_rating = findViewById(R.id.line_rating);
        line_available = findViewById(R.id.line_availible);
        line_product = findViewById(R.id.line_product);
        tv_product = findViewById(R.id.tv_product);
        tv_contact = findViewById(R.id.tv_contact);
        tv_availability = findViewById(R.id.tv_availability);
        tv_rating = findViewById(R.id.tv_rating);
        scroll = findViewById(R.id.scroll);
        img_slider = findViewById(R.id.img_slider);
        iv_listingimg = findViewById(R.id.iv_listingimg);
        tv_companyslug = findViewById(R.id.tv_companyslug);
        tv_category = findViewById(R.id.tv_category);
        tv_address = findViewById(R.id.tv_address);
        tv_contact2 = findViewById(R.id.tv_contact2);
        tv_email = findViewById(R.id.tv_email);
        tv_website = findViewById(R.id.tv_website);
        tv_desc = findViewById(R.id.tv_desc);
        tv_sun = findViewById(R.id.tv_sun);
        tv_mon = findViewById(R.id.tv_mon);
        tv_tue = findViewById(R.id.tv_tue);
        tv_wed = findViewById(R.id.tv_wed);
        tv_thu = findViewById(R.id.tv_thu);
        tv_fri = findViewById(R.id.tv_fri);
        tv_sat = findViewById(R.id.tv_sat);

        rec_product = findViewById(R.id.rec_product);
        iv_call = findViewById(R.id.iv_call);
        iv_whatsapp = findViewById(R.id.iv_whatsapp);
        iv_email = findViewById(R.id.iv_email);
        line_socialconnect = findViewById(R.id.line_socialconnect);
        iv_facebook = findViewById(R.id.iv_facebook);
        iv_twitter = findViewById(R.id.iv_twitter);
        iv_linkdln = findViewById(R.id.iv_linkdln);
        iv_instagram = findViewById(R.id.iv_instagram);
        iv_wordpress = findViewById(R.id.iv_wordpress);
        iv_pint = findViewById(R.id.iv_pint);
        iv_tumblr = findViewById(R.id.iv_tumblr);
        iv_youtb = findViewById(R.id.iv_youtb);
        iv_youtubeimg = findViewById(R.id.iv_youtubeimg);
        rel_youtubeimg = findViewById(R.id.rel_youtubeimg);
        cirsubmitrate = findViewById(R.id.cirsubmitrate);
        et_SComment = findViewById(R.id.et_SComment);
        ratingbar = findViewById(R.id.ratingbar);
        ratingbarmain = findViewById(R.id.ratingbarmain);
        tv_ratingmain = findViewById(R.id.tv_ratingmain);
        rec_comment = findViewById(R.id.rec_comment);
        tv_ratingcount = findViewById(R.id.tv_ratingcount);
        rel_slider = findViewById(R.id.rel_slider);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        appb = findViewById(R.id.appbar);
        youtube_playerview.setVisibility(View.GONE);


        String MID = PrefUtils.getPref(Detail_Page_Activity.this, "MMID");
        String CCSLUG = PrefUtils.getPref(Detail_Page_Activity.this, "CCSLUG");
        String IMG = PrefUtils.getPref(Detail_Page_Activity.this, "IG");
        String LLID = PrefUtils.getPref(Detail_Page_Activity.this, "LLID");

        appb.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (toolbar.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(toolbar)) {
                    swipeRefreshLayout.setEnabled(false);
                } else {
                    swipeRefreshLayout.setEnabled(true);
                }
            }
        });

        btn_viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Detail_Page_Activity.this,ViewCart_Activity.class));
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        calldetailapi(CCSLUG, MID, IMG);
                    }
                }, 1000);
            }
        });
        calldetailapi(CCSLUG, MID, IMG);
        cirsubmitrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USER = PrefUtils.getPref(Detail_Page_Activity.this, CONSTANT.PREF_LOGINTYPE);
                if (ratingbar.getRating() == 0) {
                    CommandMethod.showAlert("Please Select Rating First", Detail_Page_Activity.this);
                } else if (USER.equalsIgnoreCase("shop")) {
                    callratingapi((int) ratingbar.getRating(), et_SComment.getText().toString(), MID, LLID);
                } else {
                    startActivity(new Intent(Detail_Page_Activity.this, LoginActivity.class));
                    PrefUtils.setPref(Detail_Page_Activity.this, CONSTANT.RATING, "yes");
                }
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
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + emailaddress));
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

        toolbar.setMinimumHeight(120);

        fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetImageFromUrl().execute("https://www.areaonline.in/uploads/listing/" + IMG);
            }
        });
        fab_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compname = PrefUtils.getPref(Detail_Page_Activity.this, CONSTANT.PREF_COMPANY_NAME);

                if (compname.equalsIgnoreCase("")) {
                    startActivity(new Intent(Detail_Page_Activity.this,LoginActivity.class));
                    PrefUtils.setPref(Detail_Page_Activity.this,CONSTANT.MSG,"yes");
                } else {
                    String User_ID = PrefUtils.getPref(Detail_Page_Activity.this, PREF_MID);
                    String user_companyname = PrefUtils.getPref(Detail_Page_Activity.this, USER_COMPANY_NAME);
                    if (user_companyname.equalsIgnoreCase("srt testw")) {
                        user_companyname = "testing";
                    }
                    startActivity(new Intent(Detail_Page_Activity.this, Messaging_Activity.class)
                            .putExtra("user_companyname", user_companyname)
                            .putExtra("chat_mid", chat_mid)
                            .putExtra("profileimg", IMG)
                            .putExtra("chatuser_cmpname", chat_compname));
                }




            }
        });

        iv_youtubeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_youtubeimg.setVisibility(View.GONE);
                youtube_playerview.setVisibility(View.VISIBLE);
                if (yvplayer != null) {
                    yvplayer.play();
                }
            }
        });

        iv_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent2 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(facebook));
                startActivity(viewIntent2);
            }
        });
        iv_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent2 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(twitter));
                startActivity(viewIntent2);
            }
        });
        iv_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent2 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(instagram));
                startActivity(viewIntent2);
            }
        });
        iv_linkdln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent2 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(linkdin));
                startActivity(viewIntent2);
            }
        });
        iv_wordpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent2 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(wordpress));
                startActivity(viewIntent2);
            }
        });
        iv_pint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent2 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(pint));
                startActivity(viewIntent2);
            }
        });
        iv_tumblr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent2 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(tumblr));
                startActivity(viewIntent2);
            }
        });


//        img_slider.set.setImageList(imageList);
        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselecteditem(tv_home);
                unselecteditem(tv_product, tv_contact, tv_availability, tv_rating);
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0, line_home.getTop());
                        line_home.setBackgroundColor(getResources().getColor(R.color.blue_light2));
                    }
                });
                Handler handler = new Handler();

                Runnable run = new Runnable() {

                    public void run() {
                        line_home.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                };
                handler.postDelayed(run, 1300);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        iv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + emailaddress));
                startActivity(intent);
            }
        });
        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);

            }
        });
        iv_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!whatsappnumber.contains("+91")) {
                    whatsappnumber = "+91" + whatsappnumber;
                }
                String message = "Hello! " + cmpname + ", I found you on AreaOnline ";
//                ccccc
                String url = null;
                try {
                    url = "https://api.whatsapp.com/send?phone=" + whatsappnumber + "&text=" + URLEncoder.encode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_TEXT, message);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        tv_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselecteditem(tv_product);
                unselecteditem(tv_home, tv_contact, tv_availability, tv_rating);
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0, line_home.getBottom());
                        line_product.setBackgroundColor(getResources().getColor(R.color.blue_light2));
                    }
                });
                Handler handler = new Handler();

                Runnable run = new Runnable() {

                    public void run() {
                        line_product.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                };
                handler.postDelayed(run, 1300);
//                scroll.smoothScrollTo(0,600);
            }
        });
        tv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselecteditem(tv_contact);
                unselecteditem(tv_home, tv_product, tv_availability, tv_rating);
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0, line_contactus.getTop());
                        line_contactus.setBackgroundColor(getResources().getColor(R.color.blue_light2));
                        line_contact2.setBackgroundColor(getResources().getColor(R.color.transperant));
                    }
                });
                Handler handler = new Handler();

                Runnable run = new Runnable() {

                    public void run() {
                        line_contactus.setBackgroundColor(getResources().getColor(R.color.white));
                        line_contact2.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                };
                handler.postDelayed(run, 1300);
            }
        });
        tv_availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselecteditem(tv_availability);
                unselecteditem(tv_home, tv_product, tv_contact, tv_rating);
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0, about.getBottom());
                        line_available.setBackgroundColor(getResources().getColor(R.color.blue_light2));
                        line_available2.setBackgroundColor(getResources().getColor(R.color.transperant));
                    }
                });
                Handler handler = new Handler();

                Runnable run = new Runnable() {

                    public void run() {
                        line_available.setBackgroundColor(getResources().getColor(R.color.white));
                        line_available2.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                };
                handler.postDelayed(run, 1300);
            }
        });
        tv_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselecteditem(tv_rating);
                unselecteditem(tv_home, tv_product, tv_availability, tv_contact);
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0, line_rating.getTop());
                        line_rating.setBackgroundColor(getResources().getColor(R.color.blue_light2));
                        line_rating2.setBackgroundColor(getResources().getColor(R.color.transperant));
                    }
                });
                Handler handler = new Handler();
                Runnable run = new Runnable() {

                    public void run() {
                        line_rating.setBackgroundColor(getResources().getColor(R.color.white));
                        line_rating2.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                };
                handler.postDelayed(run, 1300);
            }
        });
    }

    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        public GetImageFromUrl() {
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            Bitmap bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @SuppressLint("WrongThread")
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            String msg;
            if (domain.equalsIgnoreCase("")) {
                msg = "Take a Look at My Shop " + nameofcommpnu + " on Areaonline " + "https://www.areaonline.in/" + cms;
            } else {
                msg ="Take a Look at My Shop " + nameofcommpnu + " on Areaonline " + domain;
            }
            Uri bmpUri = null;
            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
                file.getParentFile().mkdirs();
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.close();
                bmpUri = FileProvider.getUriForFile(Detail_Page_Activity.this, "com.areaonline.fileprovider", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bmpUri != null) {
                // Construct a ShareIntent with link to image
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(Intent.createChooser(shareIntent, "Share Image"));
            } else {
                Toast.makeText(Detail_Page_Activity.this, "Sharing failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void popup(String image) {
        Log.e("popup: ", "fff");
        Dialog dialog = new Dialog(Detail_Page_Activity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        LayoutInflater factory = LayoutInflater.from(Detail_Page_Activity.this);
        final View view = factory.inflate(R.layout.item_image_popup, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TouchImageView dialog_imageview = dialog.findViewById(R.id.dialog_imageview);
        Glide.with(Detail_Page_Activity.this).load(image).into(dialog_imageview);
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

    private void callratingapi(int rating, String s, String MID, String LLID) {
        {

            String muser_id = PrefUtils.getPref(Detail_Page_Activity.this, PREF_MID);
            CommandMethod.showProgressDialog(Detail_Page_Activity.this);
            Map<String, String> map = new HashMap<String, String>();
            map.put("user_id", muser_id);
            map.put("l_id", LLID);
            map.put("m_id", muser_id);
            map.put("rating", String.valueOf(rating));
            map.put("comment", s);
            Call<PostRating_Response> call1 = apiInterface.postrating(map);
            call1.enqueue(new Callback<PostRating_Response>() {
                @Override
                public void onResponse(Call<PostRating_Response> call, Response<PostRating_Response> response) {
                    CommandMethod.hideProgressDialog(Detail_Page_Activity.this);
                    PostRating_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        restart = 1;
                        Toast.makeText(Detail_Page_Activity.this, "Review Submitted Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Detail_Page_Activity.this, Detail_Page_Activity.class));
                    } else {
                        Toast.makeText(Detail_Page_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PostRating_Response> call, Throwable t) {
                    Toast.makeText(Detail_Page_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Detail_Page_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void calldetailapi(String CCSLUG, String MID, String IMG) {
        Log.e("calldetailapi: ", CCSLUG);
        CommandMethod.showProgressDialog(Detail_Page_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String, String> map = new HashMap<String, String>();
        map.put("m_id", MID);

        Call<ListDetail_Response> call1 = apiInterface.listdetail(CCSLUG, map);
        call1.enqueue(new Callback<ListDetail_Response>() {
            @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
            @Override
            public void onResponse(Call<ListDetail_Response> call, Response<ListDetail_Response> response) {
                CommandMethod.hideProgressDialog(Detail_Page_Activity.this);
                ListDetail_Response loginResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
                if (response.isSuccessful()
//                        &&
//                        loginResponse.getSuccess()
                ) {
                    ListDetail_Response modal = gson.fromJson(successResponse, ListDetail_Response.class);
                    if (modal.getProduct().size() > 0) {
                        productdata.clear();
                        for (int i = 0; i < modal.getProduct().size(); i++) {
                             product_img = modal.getProduct().get(i).getProductImg();
                            String name = modal.getProduct().get(i).getServicesName();
                            String Category = modal.getProduct().get(i).getCatName();
                            String desc = modal.getProduct().get(i).getServicesDesc();
                            String servicesSlug = modal.getProduct().get(i).getServicesSlug();
                            String m_id2 = modal.getProduct().get(i).getmId();
                            String ps_id = modal.getProduct().get(i).getPsId();
                            String l_id = modal.getProduct().get(i).getlId();
                            String price = modal.getProduct().get(i).getPrice();
                            HashMap map = new HashMap();
                            String number = modal.getList().getContact();
                            whatsappnumber = modal.getList().getWhatsapp();
                            String address = modal.getList().getAddress();
                            String email = modal.getList().getEmail();
                            String website = modal.getList().getWebsite();
                            nameofcommpnu = modal.getList().getName();
                            chat_compname = modal.getList().getCompName();
                            if (PrefUtils.getPref(Detail_Page_Activity.this,CONSTANT.PREF_COMPANY_NAME).equalsIgnoreCase(chat_compname)){
                                fab_chat.setVisibility(View.GONE);
                            }else{
                                fab_chat.setVisibility(View.VISIBLE);
                            }
                            chat_mid = modal.getList().getmId();
                            String city = modal.getList().getCity();
                            domain = modal.getList().getDomain();
                            if (city.contains(" ")) {
                                city = city.replace(" ", "");
                            }
                            String state = modal.getList().getState();
                            if (state.contains(" ")) {
                                state = state.replace(" ", "");
                            }
                            String country = modal.getList().getCountry();
                            if (country.contains(" ")) {
                                country = country.replace(" ", "");
                            }
                            String pincode = modal.getList().getPinCode();
//                            String cms =   modal.getList().getCompanySlug();
                            String subcattname = modal.getList().getSubCatName();
                            String org_price = modal.getProduct().get(i).getOrg_price();
                            if (modal.getList().getSubCatName().contains(" ")) {
                                subcattname = modal.getList().getSubCatName().replace(" ", "-");
                            }
                            String ccc = modal.getList().getCategory();
                            if (ccc.contains(" ")) {
                                ccc = ccc.replace(" ", "-");
                            }
                            cms = modal.getList().getCompanySlug() + "/" + ccc + "/" + subcattname + "/" + city + "/" + state + "/" + country + "/" + lid;
                            map.put("number", number);
                            map.put("address", address);
                            map.put("email", email);
                            map.put("website", website);
                            map.put("m_id", m_id2);
                            map.put("ps_id", ps_id);
                            map.put("l_id", l_id);
                            map.put("listingimg", product_img);
                            map.put("companyname", nameofcommpnu);
                            map.put("category", Category);
                            map.put("desc", desc);
                            map.put("price", price);
                            map.put("servicesSlug", servicesSlug);
                            map.put("city", city);
                            map.put("state", state);
                            map.put("country", country);
                            map.put("pincode", pincode);
                            map.put("CMS", cms);
                            map.put("count", "0");
                            map.put("org_price", org_price);
                            map.put("domain", domain);

                            productdata.add(map);
                        }
//                        GridLayoutManager gridLayoutManager = new GridLayoutManager(Detail_Page_Activity.this, 1, LinearLayoutManager.HORIZONTAL, false);
                        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(Detail_Page_Activity.this,  LinearLayoutManager.VERTICAL, false);
                        Product_Adapter product_adapter = new Product_Adapter(Detail_Page_Activity.this, productdata);

                        rec_product.setAdapter(product_adapter);
                        rec_product.setLayoutManager(gridLayoutManager);
                    }
                    Log.e("initializedwidget: ", "1" + modal.getList().getCoverImg());
                    if (modal.getList().getCoverImg().equalsIgnoreCase("no-img")) {
                        imageList.add((R.drawable.placeholder2));
                    } else {
                        imageList.add("https://www.areaonline.in/uploads/cover_img/" + modal.getList().getCoverImg());
                    }
                    SliderAdapterExample sliderAdapter = new SliderAdapterExample(Detail_Page_Activity.this, imageList);
                    img_slider.setSliderAdapter(sliderAdapter);
                    if (!modal.getList().getCoverImg().equalsIgnoreCase("")) {
                        Glide.with(Detail_Page_Activity.this).load("https://www.areaonline.in/uploads/listing/" + IMG).placeholder((R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true).error((Glide.with(Detail_Page_Activity.this).load("https://www.areaonline.in/uploads/SubCatLogo/" + IMG))).into(iv_listingimg);
                    }
                    cmpname = modal.getList().getCompName();
                    tv_companyslug.setText(modal.getList().getCompName());
                    tv_category.setText(modal.getList().getCategory());
                    tv_address.setText(modal.getList().getAddress() + " - " + modal.getList().getLocalArea() + " " + modal.getList().getPinCode() + "\n" + modal.getList().getCity()
                            + ", " + modal.getList().getState() + ", " + modal.getList().getCountry());
                    tv_contact2.setText(modal.getList().getContact());
                    tv_ratingcount.setText("(" + modal.getList().getRating().toString() + ")");
                    number = modal.getList().getContact();
                    emailaddress = modal.getList().getEmail();
                    tv_email.setText(modal.getList().getEmail());
                    tv_ratingmain.setText(String.format("%.1f", modal.getList().getAvgRating()));
                    ratingbarmain.setRating(Float.parseFloat(String.valueOf(modal.getList().getAvgRating())));
                    if (modal.getList().getWebsite().equalsIgnoreCase("")) {
                        tv_website.setText("N/A");
                        website = "";
                    } else {
                        tv_website.setText(modal.getList().getWebsite());
                        website = modal.getList().getWebsite();
                    }
                    if (modal.getList().getReview().size() > 0) {
                        LinearLayoutManager lm = new LinearLayoutManager(Detail_Page_Activity.this, LinearLayoutManager.VERTICAL, false);
                        CommentList_Adapter cm = new CommentList_Adapter(Detail_Page_Activity.this, modal.getList());
                        rec_comment.setLayoutManager(lm);
                        rec_comment.setAdapter(cm);
                    }
                    tv_desc.setText(modal.getList().getDesc());
                    tv_mon.setText(modal.getList().getMondayOpening() + " - " +
                            modal.getList().getMondayClosing());
                    tv_tue.setText(modal.getList().getTuesdayOpening() + " - " +
                            modal.getList().getTuesdayClosing());
                    tv_wed.setText(modal.getList().getWednesdayOpening() + " - " +
                            modal.getList().getWednesdayClosing());
                    tv_thu.setText(modal.getList().getThursdayOpening() + " - " +
                            modal.getList().getThursdayClosing());
                    tv_fri.setText(modal.getList().getFridayOpening() + " - " +
                            modal.getList().getFridayClosing());
                    tv_sat.setText(modal.getList().getSaturdayOpening() + " - " +
                            modal.getList().getSaturdayClosing());
                    tv_sun.setText(modal.getList().getSundayOpening() + " - " +
                            modal.getList().getSundayClosing());
                    if (!modal.getList().getFacebook().equalsIgnoreCase("")) {
                        facebook = modal.getList().getFacebook();
                        iv_facebook.setVisibility(View.VISIBLE);
                    }
                    if (!modal.getList().getTwitter().equalsIgnoreCase("")) {
                        twitter = modal.getList().getTwitter();
                        iv_twitter.setVisibility(View.VISIBLE);
                    }
                    if (!modal.getList().getLinkedin().equalsIgnoreCase("")) {
                        linkdin = modal.getList().getLinkedin();
                        iv_linkdln.setVisibility(View.VISIBLE);
                    }
                    if (!modal.getList().getInstagram().equalsIgnoreCase("")) {
                        instagram = modal.getList().getInstagram();
                        iv_instagram.setVisibility(View.VISIBLE);
                    }
                    if (!modal.getList().getWordpress().equalsIgnoreCase("")) {
                        wordpress = modal.getList().getWordpress();
                        iv_wordpress.setVisibility(View.VISIBLE);
                    }
                    if (!modal.getList().getPint().equalsIgnoreCase("")) {
                        pint = modal.getList().getPint();
                        iv_pint.setVisibility(View.VISIBLE);
                    }
                    if (!modal.getList().getTumblr().equalsIgnoreCase("")) {
                        tumblr = modal.getList().getTumblr();
                        iv_tumblr.setVisibility(View.VISIBLE);
                    }
                    if (!modal.getList().getVideoUrl().equalsIgnoreCase("")) {
                        videourl = modal.getList().getVideoUrl();
                        String webContent = videourl;
                        if (webContent.contains("iframe")) {
                            Matcher matcher = Pattern.compile("src=\"([^\"]+)\"").matcher(webContent);
                            matcher.find();
                            src = matcher.group(1);
                            webContent = src;
                            try {
                                URL myURL = new URL(src);
//                webView.loadUrl(src);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

                        Pattern compiledPattern = Pattern.compile(pattern);
                        Matcher matcher = compiledPattern.matcher(src);

                        if (matcher.find()) {
                            VideoID = matcher.group();
                            Glide.with(Detail_Page_Activity.this).load("https://img.youtube.com/vi/" + VideoID + "/hqdefault.jpg").placeholder(R.drawable.loading).into(iv_youtubeimg);
                            rel_youtubeimg.setVisibility(View.VISIBLE);
//                            Detail_Page_Activity.this.getLifecycle().addObserver(youtube_playerview);
                            youtube_playerview.enableBackgroundPlayback(false);
                            youtube_playerview.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                    yvplayer = youTubePlayer;
                                    yvplayer.loadVideo(VideoID, 0);
                                    yvplayer.pause();
//                                    super.onReady(youTubePlayer);
                                }
                            });
                        }
                    }
                    if (!modal.getList().getYoutube().equalsIgnoreCase("")) {
                        youtube = modal.getList().getYoutube();
                        iv_youtb.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListDetail_Response> call, Throwable t) {
                Toast.makeText(Detail_Page_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(Detail_Page_Activity.this);
                call.cancel();
            }
        });
    }

    private void unselecteditem(TextView tv_home, TextView tv_contact, TextView tv_availability, TextView tv_rating) {
        tv_home.setTextColor(getResources().getColor(R.color.black));
        tv_contact.setTextColor(getResources().getColor(R.color.black));
        tv_availability.setTextColor(getResources().getColor(R.color.black));
        tv_rating.setTextColor(getResources().getColor(R.color.black));
    }

    private void setselecteditem(TextView tv_product) {
        tv_product.setTextColor(getResources().getColor(R.color.red_bg));
    }

}