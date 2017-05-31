package com.example.carlcastello.dosomethingapp.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carlcastello.dosomethingapp.Listeners.DialogListener;
import com.example.carlcastello.dosomethingapp.R;

import java.util.ArrayList;

/**
 * Created by carlcastello on 26/05/17.
 */

public class CategoryDialog extends DialogFragment {

    private View view;
    private DialogListener dialogListener;

    public CategoryDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static CategoryDialog newInstance(String title,ArrayList<Boolean> selectedList, DialogListener dialogListener) {
        CategoryDialog frag = new CategoryDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putSerializable("dialogListener",dialogListener);
        args.putSerializable("selectedList",selectedList);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //  https://stackoverflow.com/questions/14640397/dialogfragment-throws-classcastexception-if-called-from-fragment
        this.dialogListener = (DialogListener) getArguments().getSerializable("dialogListener");

        ArrayList<Boolean> selectedList = (ArrayList<Boolean>) getArguments().getSerializable("selectedList");
        boolean[] checkedList = new boolean[selectedList.size()];
        for (int i = 0; i < selectedList.size(); ++i) {
            checkedList[i] = selectedList.get(i);
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
//        this.view = inflater.inflate(R.layout.dialog_category, null);
        builder.setTitle(R.string.categories)
                .setMultiChoiceItems(R.array.categories, checkedList,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                            }
                        })
                // Add action buttons
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CategoryDialog.this.getDialog().cancel();
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

        final AlertDialog dialog = (AlertDialog) getDialog();
        if(dialog != null) {
            Button positiveButton = (Button) dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListView list = dialog.getListView();
                    int selected = 0;
                    ArrayList<Boolean> stateOfList =  new ArrayList<>();
                    for (int i = 0; i < list.getCount(); ++i) {
                        boolean checked = list.isItemChecked(i);
                        stateOfList.add(checked);
                        if (checked) {
                            selected += 1;
                        }
                    }
                    dialogListener.categoryDialogResponse(stateOfList,selected);
                    dismiss();
                }
            });
        }
    }

}
