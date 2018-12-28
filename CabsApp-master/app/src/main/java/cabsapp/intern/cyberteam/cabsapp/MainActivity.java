package cabsapp.intern.cyberteam.cabsapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.github.florent37.awesomebar.ActionItem;
import com.github.florent37.awesomebar.AwesomeBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

//import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    @Bind(R.id.bar)
    AwesomeBar bar;

//    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    FrameLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=
                    new NotificationChannel("MyNotification","MyNotification",NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager= getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>(){
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "successfull";
                        if (!task.isSuccessful()) {
                            msg = "fail";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        ButterKnife.bind(this);

        bar = findViewById(R.id.bar);
        drawerLayout = findViewById(R.id.drawer_layout);

        bar.addAction(R.drawable.awsb_ic_edit_animated, "Ride Now");

        bar.setActionItemClickListener(new AwesomeBar.ActionItemClickListener() {
            @Override
            public void onActionItemClicked(int position, ActionItem actionItem) {
                Toast.makeText(getBaseContext(), actionItem.getText()+" clicked", Toast.LENGTH_LONG).show();
            }
        });

        bar.addOverflowItem("overflow 1");
        bar.addOverflowItem("overflow 2");
        bar.addOverflowItem("Sign Out");
//        bar.cleanOverflowMenu();
        bar.setOverflowActionItemClickListener(new AwesomeBar.OverflowActionItemClickListener() {
            @Override
            public void onOverflowActionItemClicked(int position, String item) {

                switch(item){
                    case "Sign Out":
                        //sign out
                        AuthUI.getInstance().signOut(getApplicationContext());
                        Toast.makeText(MainActivity.this, "Signed out Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    default:
                        Toast.makeText(getBaseContext(), item+" clicked", Toast.LENGTH_LONG).show();
                }
            }

        });

//        bar.setIcon(R.drawable.gmail_logo_2);

        bar.setOnMenuClickedListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawerLayout.openDrawer(Gravity.START);
        }
    });

        bar.displayHomeAsUpEnabled(false);
}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.home){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.option1) {

        } else if (id == R.id.option2) {

        } else if (id == R.id.option3) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}