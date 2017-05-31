package com.example.carlcastello.dosomethingapp.Listeners;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carlcastello on 27/05/17.
 */

public interface DialogListener extends Serializable {
    public abstract void categoryDialogResponse(ArrayList<Boolean> stateOfList,Integer trueList);
    public abstract void pricePointDialogResponse(String params);
    public abstract void radiusDialogResponse(String params);
}
