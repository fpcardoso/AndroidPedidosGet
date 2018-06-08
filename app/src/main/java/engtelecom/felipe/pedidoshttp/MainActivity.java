package engtelecom.felipe.pedidoshttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private TextView mtextView;
    private EditText meditText;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtextView = (TextView) findViewById(R.id.textViewResult);
        meditText = (EditText) findViewById(R.id.plainTextMatricula);
        mButton = (Button) findViewById(R.id.ButtonEnviar);
    }

    public void pedidoGET(View view){
        RequestQueue filaPedidos = Volley.newRequestQueue(this);

        String url = "http://191.36.9.85:5000/matricula/"+meditText.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mtextView.setText(response);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast toast = Toast.makeText(getApplicationContext(),"Olá",Toast.LENGTH_LONG);
                toast.show();


                mButton.setEnabled(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mtextView.setText("Erro: " + error.toString());
                mButton.setEnabled(true);
            }
        }
        );
        filaPedidos.add(stringRequest);
        mButton.setEnabled(false);

    }

}
