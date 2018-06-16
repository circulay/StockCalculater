package torach.java_conf.gr.jp.stockcalculater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class MainActivity extends Activity implements View.OnClickListener {

    //admob
    private AdView mAdView;

    //ボタン変数宣言
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //admob
        MobileAds.initialize(this, "ca-app-pub-8672150310568733~6447622653");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //計算選択ボタン
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        //リスナー設定
        button1.setOnClickListener((View.OnClickListener)this);
        button2.setOnClickListener((View.OnClickListener)this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this, Sell.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(MainActivity.this, Profit.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

}
