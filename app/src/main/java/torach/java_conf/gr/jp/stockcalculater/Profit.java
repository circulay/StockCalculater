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

public class Profit extends Activity implements View.OnClickListener{

    //admob
    private AdView mAdView;

    //ボタン宣言
    Button cal_button_profit;
    Button clear_button_profit;
    Button back_button_profit;

    //計算変数宣言
    int buy_cost;
    int sell_cost;
    EditText edit_buy_cost;
    EditText edit_sell_cost;
    TextView result_rate;
    String buy_cost_str;
    String sell_cost_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        //admob
        MobileAds.initialize(this, "ca-app-pub-8672150310568733~6447622653");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //ボタン取得
        cal_button_profit = (Button) findViewById(R.id.cal_button_profit);
        clear_button_profit = (Button) findViewById(R.id.clear_button_profit);
        back_button_profit = (Button) findViewById(R.id.back_button_profit);

        //リスナー設定
        cal_button_profit.setOnClickListener((View.OnClickListener) this);
        clear_button_profit.setOnClickListener((View.OnClickListener) this);
        back_button_profit.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id) {
            case R.id.cal_button_profit:
                calculate_rate();
                break;
            case R.id.clear_button_profit:
                clear_data();
                break;
            case R.id.back_button_profit:
                finish();
                break;
        }
    }

    public void calculate_rate(){
        //入力データ取得
        edit_buy_cost = (EditText) findViewById(R.id.value_buy_profit);
        edit_sell_cost = (EditText) findViewById(R.id.value_sell_profit);

        //入力値を取得
        buy_cost_str = edit_buy_cost.getText().toString();
        sell_cost_str = edit_sell_cost.getText().toString();

        try
        {
            //整数値変換
            buy_cost = Integer.parseInt(buy_cost_str);
            sell_cost = Integer.parseInt(sell_cost_str);

            //売値計算
            //インスタンス
            BigDecimal val_buy = new BigDecimal(buy_cost);
            BigDecimal val_sell = new BigDecimal(sell_cost);
            BigDecimal val_hundred = new BigDecimal(100);

            //計算数式
            BigDecimal temp1 = val_sell.subtract(val_buy);
            BigDecimal temp2 = temp1.divide(val_buy,3,BigDecimal.ROUND_DOWN);
            BigDecimal temp3 = temp2.multiply(val_hundred);
            BigDecimal rate = temp3.setScale(1,BigDecimal.ROUND_DOWN);

            //計算結果表示
            result_rate = (TextView) findViewById(R.id.display_result_profit);
            result_rate.setText(String.valueOf(rate));
        } catch (NumberFormatException e) {
            String pop1 = getResources().getString(R.string.exist_non_imp);
            Toast toast = Toast.makeText(Profit.this, pop1, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

   public void clear_data() {
       try
       {
           edit_buy_cost.setText("");
           edit_sell_cost.setText("");
           result_rate.setText("");
       } catch (NullPointerException e) {
           String pop2 = getResources().getString(R.string.no_imp_data);
           Toast toast = Toast.makeText(Profit.this, pop2, Toast.LENGTH_SHORT);
           toast.show();
       }
   }


}
