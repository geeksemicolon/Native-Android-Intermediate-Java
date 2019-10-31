package id.mpeinc.apploginregister.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class HelperClass {
    Context context;

    public HelperClass(Context context) {
        this.context = context;
    }

    public void pindahActivity(Activity awal, Class tujuan) {
        Intent pindahAct = new Intent(awal, tujuan);
        context.startActivity(pindahAct);
    }
}
