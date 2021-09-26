package education.vt.elearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(MainActivity.this,navigation.class);
                    intent.putExtra("Check","");
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }
    protected void onPause() {

        super.onPause();
        finish();
    }

}
