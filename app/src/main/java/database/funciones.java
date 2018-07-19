package database;

import android.content.Context;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class funciones {
    private Context context;

    public funciones(Context context) {
        this.context = context;
    }
    public String clave() {

        final String MD5 = "MD5";
        String res="";
        final Calendar cal = Calendar.getInstance();
        int pYear2 = cal.get(Calendar.YEAR);
        int pMonth2 = cal.get(Calendar.MONTH)+1;
        int pDay2 = cal.get(Calendar.DAY_OF_MONTH);
        res=pYear2+"";
        if(pMonth2<10)
            res=res+"0"+pMonth2;
        else
            res=res+pMonth2;
        if(pDay2<10)
            res=res+"0"+pDay2;
        else
            res=res+pDay2;
        res=res+"satcosta";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(res.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
