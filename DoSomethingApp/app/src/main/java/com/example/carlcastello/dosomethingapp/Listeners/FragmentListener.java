package com.example.carlcastello.dosomethingapp.Listeners;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carlcastello on 28/05/17.
 */

public interface FragmentListener extends Serializable {
    public abstract void proceedButtonResponse(ArrayList<Boolean> categories, double pricePoint, int radius);
}
