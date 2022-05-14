package com.example.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

public class DpFragment extends Fragment {

    String URL = "NULL";
    ImageView mparticulardp;
    EditText getdplink;
    Button getdp;
    Button downloaddp;
    String dpurl = "1";
    private Uri uri3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dp_fragment,null);
        getdplink = v.findViewById(R.id.getdplink);
        getdp = v.findViewById(R.id.getdp);
        downloaddp = v.findViewById(R.id.downloaddp);
        mparticulardp = v.findViewById(R.id.particulardp);

        getdp.setOnClickListener(v1 -> {

            URL = getdplink.getText().toString().trim();
            if (getdplink.equals("NULL")) {
            }
            else
            {
                String result2 = StringUtils.substringBefore(URL, "/?");
                URL = result2 + "/?__a=1";
                processdata();
//                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
            }
        });

        downloaddp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dpurl.equals("1"))
                {
                    DownloadManager.Request request = new DownloadManager.Request(uri3);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    request.setTitle("Download");
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    request.allowScanningByMediaScanner();
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ""+System.currentTimeMillis()+ ",mp4");
                    DownloadManager manager = (DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    Toast.makeText(getContext(), "Downloaded", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
    private void processdata() {

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Third t = gson.fromJson(response,Third.class);
                dpurl = t.getGraphql().getUser().getProfile_pic_url_hd();
                uri3 = Uri.parse(dpurl);

                Glide.with(getContext()).load(uri3).into(mparticulardp);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

}