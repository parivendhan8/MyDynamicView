package techy.ap.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RelativeLayout dynamicView;
    LinearLayout addView;
    private EditText editText;
    int addTag = 0;
    private Button submit;
    public static ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dynamicView = (RelativeLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.dynamic_view, null);

        addView = findViewById(R.id.addView);
        addView.addView(dynamicView);

         editText = dynamicView.findViewById(R.id.editText);
        editText.addTextChangedListener(textWatcher);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0 ; i < addView.getChildCount(); i++)
                {
                    RelativeLayout relativeLayout = (RelativeLayout) addView.getChildAt(i);
                    EditText editText = (EditText) relativeLayout.getChildAt(2);
                    String s = editText.getText().toString();
                    arrayList.add(s);
                }

            }
        });



    }

    TextWatcher textWatcher = new TextWatcher() {

        private ImageView image_delete;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            RelativeLayout dynamicView = (RelativeLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.dynamic_view, null);
            EditText editText = dynamicView.findViewById(R.id.editText);
            image_delete = dynamicView.findViewById(R.id.image_delete);
            image_delete.setTag(addTag ++);
            image_delete.setOnClickListener(onClickListener);
            editText.addTextChangedListener(textWatcher);
            addView.addView(dynamicView);



        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

      OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            ImageView delete = (ImageView) v;
            int tag = (int) delete.getTag();

            for (int i = 0 ; i < addView.getChildCount(); i++ )
            {

                RelativeLayout relativeLayout = (RelativeLayout) addView.getChildAt(i);
                ImageView childAt = (ImageView) relativeLayout.getChildAt(0);
                if (childAt.getTag() != null)
                {
                    int child = (int) childAt.getTag();
                    if (child == tag)
                    {
                        addView.removeViewAt(i);
                    }
                }

            }

            Log.d(TAG, "onClick: " + tag);
        }
    };



}