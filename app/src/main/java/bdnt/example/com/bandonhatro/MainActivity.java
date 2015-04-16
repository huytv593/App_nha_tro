package bdnt.example.com.bandonhatro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    public static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Tìm kiếm", getResources().getDrawable(R.drawable.search)),
                SearchFragment.class, null);
        //FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Bản đồ", getResources().getDrawable(R.drawable.map)),
                MapFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Tùy chọn", getResources().getDrawable(R.drawable.option)),
                OptionFragment.class, null);
    }


}