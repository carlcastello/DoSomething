package com.example.carlcastello.dosomethingapp.Dialog;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlcastello.dosomethingapp.R;

import java.util.ArrayList;

/**
 * Created by carlcastello on 27/05/17.
 */

public class RadiusDialog extends DialogFragment implements SeekBar.OnSeekBarChangeListener {
    private View view;
    private ArrayList mSelectedItems;

    public RadiusDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static RadiusDialog newInstance(String title) {
        RadiusDialog frag = new RadiusDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.mSelectedItems = new ArrayList();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        this.view = inflater.inflate(R.layout.dialog_radius, null);

        // Seek bar
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.radius_seekbar);
        seekBar.setMax(100);
        seekBar.setProgress(5);
        seekBar.setOnSeekBarChangeListener(this);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        // Use the Builder class for convenient dialog construction
        builder.setTitle(R.string.radius)
                .setView(view)
                // Add action buttons
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RadiusDialog.this.getDialog().cancel();
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
                    Toast.makeText(getContext(),"Radius Fragment",Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            });
        }
    }


    // https://stackoverflow.com/questions/8956218/android-seekbar-setonseekbarchangelistener
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        //set textView's text
        TextView radiusText = (TextView) this.view.findViewById(R.id.radius_edit_2);
        radiusText.setText(""+progress);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {}
}
