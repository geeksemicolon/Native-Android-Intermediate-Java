package id.mpeinc.apploginregister.handler;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import id.mpeinc.apploginregister.object.Member;

public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_EMPTY = "";

    private Context context;
    private SharedPreferences.Editor prefEditor;
    private SharedPreferences prefShared;

    public SessionHandler(Context context) {
        this.context = context;
        prefShared = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.prefEditor = prefShared.edit();
    }

    /**
    * Login user disimpan dengan mengambil session dan detailnya
    * @param username
    * @param fullname
    */

    public void loginUser(String username, String fullname) {
        prefEditor.putString(KEY_USERNAME, username);
        prefEditor.putString(KEY_FULL_NAME, fullname);

        Date date = new Date();
        long millis = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        prefEditor.putLong(KEY_EXPIRES, millis);
        prefEditor.commit();
    }


    /**
     * Mengecek apakah User sudah melakukan Login atau Belum
     * @return
     *
     * Jika Shared Preference tidak terdapat nilai yang tersimpan maka
     * user tidak melakukan login pada apps
     *
     * Pengecekan akan dilakukan jika session sudah expired dengan menyesuaikan
     * waktu yang sudah ditentukan berapa lama lagi waktu yang tersisa usia login
     * user tersebut
     */

    public boolean isLoggedIn() {
        Date currentDate = new Date();

        long millis = prefShared.getLong(KEY_EXPIRES, 0);

        if (millis == 0) {
            return false;
        }
        Date expireDate = new Date(millis);

        return  currentDate.before(expireDate);
    }

    /**
     * Mengmabil dan meneruskan data dari User yang sudah login
     *
     * Jika session user belum melakukan login maka tidak akan diarahkan ke
     * halaman dashboard, sebaliknya jika sudah melakukan login maka akan secara
     * langsung mengarah ke dasboard sesuai dengan ukuran waktu jadwal expired dari
     * akses loginnya.
     */

    public Member getMemberDetails() {
        if (!isLoggedIn()) {
            return null;
        }

        Member member = new Member();
        member.setUserName(prefShared.getString(KEY_USERNAME, KEY_EMPTY));
        member.setFullName(prefShared.getString(KEY_FULL_NAME, KEY_EMPTY));
        member.setSessionExpiredDate(new Date(prefShared.getLong(KEY_EXPIRES, 0)));

        return member;
    }

    /**
     * Method untuk melakukan logout dari user yang sudah melakukan login
     *
     */

    public void logoutMember() {
        prefEditor.clear();
        prefEditor.commit();
    }
}
