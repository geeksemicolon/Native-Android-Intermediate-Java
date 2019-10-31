package id.mpeinc.apploginregister.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.mpeinc.apploginregister.R;
import id.mpeinc.apploginregister.auth.LoginActivity;
import id.mpeinc.apploginregister.handler.SessionHandler;
import id.mpeinc.apploginregister.helper.HelperClass;
import id.mpeinc.apploginregister.object.Member;

public class DashboardActivity extends AppCompatActivity {
    private SessionHandler session;

    HelperClass helperClass = new HelperClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        session = new SessionHandler(getApplicationContext());
        Member member = session.getMemberDetails();

        TextView tv_detailmemberlogin = findViewById(R.id.welcomeText);
        Button bt_logout = findViewById(R.id.btnLogout);

        tv_detailmemberlogin.setText("Welcome " + member.getFullName() + " your session expired on " + member.getSessionExpiredDate());

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutMember();
                helperClass.pindahActivity(DashboardActivity.this, LoginActivity.class);
                finish();
            }
        });
    }
}
