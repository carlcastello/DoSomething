package com.example.carlcastello.dosomethingapp.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.carlcastello.dosomethingapp.R;

import java.util.ArrayList;

/**
 * Created by carlcastello on 27/05/17.
 */

public class PricePointDialog extends DialogFragment {
        private View view;
        private ArrayList mSelectedItems;

        public PricePointDialog() {
            // Empty constructor is required for DialogFragment
            // Make sure not to add arguments to the constructor
            // Use `newInstance` instead as shown below
        }

        public static PricePointDialog newInstance(String title) {
            PricePointDialog frag = new PricePointDialog();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.mSelectedItems = new ArrayList();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        this.view = inflater.inflate(R.layout.dialog_price, null);
        builder.setTitle(R.string.price_point)
                .setView(view)
                // Add action buttons
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PricePointDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    // http://stackoverflow.com/questions/13746412/prevent-dialogfragment-from-dismissing-when-button-is-clicked
    @Override
    public void onStart() {
        //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        super.onStart();

        AlertDialog dialog = (AlertDialog) getDialog();
        if(dialog != null) {
            Button positiveButton = (Button) dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Price Point Fragment",Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            });
        }
    }
}
