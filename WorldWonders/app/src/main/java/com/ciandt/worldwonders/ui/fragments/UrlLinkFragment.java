package com.ciandt.worldwonders.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;

public class UrlLinkFragment extends AppCompatDialogFragment {

    static public DialogFragment show(Wonder wonder, FragmentManager manager)  {
        UrlLinkFragment fragment = new UrlLinkFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable("wonder", wonder);
        fragment.setArguments(bundle);

        fragment.show(manager, "source_dialog");

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_url_link, null);

        Bundle args = getArguments();
        Wonder wonder = (Wonder) args.getSerializable("wonder");

        AlertDialog alertDialog = createAlertDialog(view, wonder);


        loadWebView(view, wonder);

        return alertDialog;
    }

    @NonNull
    private AlertDialog createAlertDialog(View view, Wonder wonder) {
        return new AlertDialog
                    .Builder(getActivity())
                    .setTitle(wonder.getName())
                    .setView(view)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
    }

    private void loadWebView(View view, Wonder wonder) {
        WebView sourceView = (WebView) view.findViewById(R.id.view_url_link);

        sourceView.setWebViewClient(new WebViewClient());
        sourceView.loadUrl(wonder.getUrl());
    }

}
