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
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.StringUtils;

public class ReelFragment extends Fragment {

    String URL = "NULL";
    VideoView mparticularReel;
    EditText getreellink;
    Button getreel;
    Button downloadReel;
    private MediaController mediaController;
    String reelurl = "1";
    private Uri uri2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reel_fragment, null);
        getreellink = v.findViewById(R.id.getreellink);
        getreel = v.findViewById(R.id.getreel);
        downloadReel = v.findViewById(R.id.downloadReel);
        mparticularReel = v.findViewById(R.id.particularReel);
        mediaController = new MediaController(getContext());
        mediaController.setAnchorView(mparticularReel);

        getreel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                URL = getreellink.getText().toString().trim();
                if (getreellink.equals("NULL")) {
                }
                else
                {
                    String result2 = StringUtils.substringBefore(URL, "/?");
                    URL = result2 + "/?__a=1";
                    processdata();

                }
            }
        });

        downloadReel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!reelurl.equals("1"))
                {
                    DownloadManager.Request request = new DownloadManager.Request(uri2);
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
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
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

                Third t = gson.fromJson(response, Third.class);
                reelurl = t.getItems().getVideo_versions().getUrl();
                uri2 = Uri.parse(reelurl);
                mparticularReel.setMediaController(mediaController);
                mparticularReel.setVideoURI(uri2);
                mparticularReel.requestFocus();
                mparticularReel.start();

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
