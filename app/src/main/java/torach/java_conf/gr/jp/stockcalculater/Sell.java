package torach.java_conf.gr.jp.stockcalculater;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



import java.math.BigDecimal;

public class Sell extends Activity implements View.OnClickListener{

    //admob
    private AdView mAdView;

    //ボタン宣言
    Button cal_button_sell;
    Button clear_button_sell;
    Button back_button_sell;

    //計算変数宣言
    int buy;
    int rate;
    EditText edit_buy;
    EditText edit_rate;
    TextView result;
    String buy_str;
    String rate_str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        //admob
        MobileAds.initialize(this, "ca-app-pub-8672150310568733~6447622653");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //ボタン取得
        cal_button_sell = (Button) findViewById(R.id.cal_button_sell);
        clear_button_sell = (Button) findViewById(R.id.clear_button_sell);
        back_button_sell = (Button) findViewById(R.id.back_button_sell);

        //リスナー設定
        cal_button_sell.setOnClickListener((View.OnClickListener) this);
        clear_button_sell.setOnClickListener((View.OnClickListener) this);
        back_button_sell.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id) {
            case R.id.cal_button_sell:
                calculate();
                break;
            case R.id.clear_button_sell:
                clear_data();
                break;
            case R.id.back_button_sell:
                finish();
                break;
        }
    }

    public void calculate() {
        //入力データ取得
        edit_buy = (EditText) findViewById(R.id.value_buy_sell);
        edit_rate = (EditText) findViewById(R.id.value_rate_sell);

        //入力値を取得
        buy_str = edit_buy.getText().toString();
        rate_str = edit_rate.getText().toString();

        try
        {
            //数値変換
            buy = Integer.parseInt(buy_str);
            rate = Integer.parseInt(rate_str);

            //売値計算
            BigDecimal val1 = new BigDecimal(buy);
            BigDecimal val2 = new BigDecimal(rate);
            BigDecimal val3 = new BigDecimal(100);
            BigDecimal temp1 = val2.divide(val3, 2, BigDecimal.ROUND_DOWN);
            BigDecimal temp2 = temp1.multiply(val1);
            BigDecimal temp3 = val1.add(temp2);
            BigDecimal sell = temp3.setScale(1, BigDecimal.ROUND_DOWN);

            //計算結果表示
            result = (TextView) findViewById(R.id.display_result_sell);
            result.setText(String.valueOf(sell));
        } catch (NumberFormatException e) {
            String pop1 = getResources().getString(R.string.exist_non_imp);
            Toast toast = Toast.makeText(Sell.this, pop1, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void clear_data() {

        try
        {
            edit_buy.setText("");
            edit_rate.setText("");
            result.setText("");
        } catch (NullPointerException e) {
            String pop2 = getResources().getString(R.string.no_imp_data);
            Toast toast = Toast.makeText(Sell.this, pop2, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
