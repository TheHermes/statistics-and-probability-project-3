package fi.arcada.sos_projekt_chart_sma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String currency, datefrom, dateto;
    LineChart chart;

    boolean clicked = false;
    boolean clicked2 = false;
    boolean clicked3 = false;

    TextView outputText;
    TextView smaCustom;
    EditText editText;
    SharedPreferences sharedPref;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        prefEditor = sharedPref.edit();
        outputText = findViewById(R.id.info);
        smaCustom = findViewById(R.id.smaCustom);
        smaCustom.setText(String.format("SMA-%s", sharedPref.getString("window", "15")));
        outputText.setText(String.format("%s - %s",
                sharedPref.getString("datefrom","2022-01-01"),
                sharedPref.getString("dateto","2022-02-01")
                ));

        // TEMPORÄRA VÄRDEN
        currency = sharedPref.getString("currency", "USD");
        datefrom = sharedPref.getString("datefrom","2022-01-01");
        dateto = sharedPref.getString("dateto","2022-02-01");

        // Hämta växelkurser från API
        ArrayList<Double> currencyValues = getCurrencyValues(currency, datefrom, dateto);
        // Skriv ut dem i konsolen
        //System.out.println(currencyValues.toString());
        //
        chart = (LineChart) findViewById(R.id.chart);
        ArrayList<DataLine> dataLines = new ArrayList<>();
        dataLines.add(new DataLine(currencyValues, sharedPref.getString("currency", "USD"), 0, Color.GREEN));
        createChart(dataLines);
    }

    public void getSMA5(View view) {
        ArrayList<Double> currencyValues = getCurrencyValues(currency, datefrom, dateto);
        currency = sharedPref.getString("currency", "USD");
        datefrom = sharedPref.getString("datefrom","2022-01-01");
        dateto = sharedPref.getString("dateto","2022-02-01");
        ArrayList<DataLine> dataLines = new ArrayList<>();
        chart = (LineChart) findViewById(R.id.chart);
        dataLines.add(new DataLine(currencyValues, sharedPref.getString("currency", "USD"), 0, Color.GREEN));

        if (!clicked) {
            clicked = true;
            if (clicked2) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 10), "SMA-10", 10, Color.RED));
            }
            if (clicked3) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, Integer.parseInt(sharedPref.getString("window", "15"))), "SMA-"+sharedPref.getString("window", "15"), Integer.parseInt(sharedPref.getString("window", "15")), Color.MAGENTA));
            }
            dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 5), "SMA-5", 5, Color.BLUE));
        } else {
            clicked = false;
            if (clicked2) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 10), "SMA-10", 10, Color.RED));
            }
            if (clicked3) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, Integer.parseInt(sharedPref.getString("window", "15"))), "SMA-"+sharedPref.getString("window", "15"), Integer.parseInt(sharedPref.getString("window", "15")), Color.MAGENTA));
            }
            dataLines.remove(new DataLine(Statistics.movingAverage(currencyValues, 5), "SMA-5", 5, Color.BLUE));
        }
        createChart(dataLines);
    }

    public void getSMA10(View view) {
        ArrayList<Double> currencyValues = getCurrencyValues(currency, datefrom, dateto);
        currency = sharedPref.getString("currency", "USD");
        datefrom = sharedPref.getString("datefrom","2022-01-01");
        dateto = sharedPref.getString("dateto","2022-02-01");
        ArrayList<DataLine> dataLines = new ArrayList<>();
        chart = (LineChart) findViewById(R.id.chart);
        dataLines.add(new DataLine(currencyValues, sharedPref.getString("currency", "USD"), 0, Color.GREEN));

        if (!clicked2) {
            clicked2 = true;
            if (clicked) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 5), "SMA-5", 5, Color.BLUE));
            }
            if (clicked3) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, Integer.parseInt(sharedPref.getString("window", "15"))), "SMA-"+sharedPref.getString("window", "15"), Integer.parseInt(sharedPref.getString("window", "15")), Color.MAGENTA));
            }
            dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 10), "SMA-10", 10, Color.RED));
        } else {
            clicked2 = false;
            if (clicked) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 5), "SMA-5", 5, Color.BLUE));
            }
            if (clicked3) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, Integer.parseInt(sharedPref.getString("window", "15"))), "SMA-"+sharedPref.getString("window", "15"), Integer.parseInt(sharedPref.getString("window", "15")), Color.MAGENTA));
            }
            dataLines.remove(new DataLine(Statistics.movingAverage(currencyValues, 10), "SMA-10", 10, Color.RED));
        }
        createChart(dataLines);
    }

    public void getSMACustom(View view) {
        ArrayList<Double> currencyValues = getCurrencyValues(currency, datefrom, dateto);
        currency = sharedPref.getString("currency", "USD");
        datefrom = sharedPref.getString("datefrom","2022-01-01");
        dateto = sharedPref.getString("dateto","2022-02-01");
        ArrayList<DataLine> dataLines = new ArrayList<>();
        chart = (LineChart) findViewById(R.id.chart);
        dataLines.add(new DataLine(currencyValues, sharedPref.getString("currency", "USD"), 0, Color.GREEN));

        if (!clicked3) {
            clicked3 = true;
            if (clicked) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 5), "SMA-5", 5, Color.BLUE));
            }
            if (clicked2) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 10), "SMA-10", 10, Color.RED));
            }
            dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, Integer.parseInt(sharedPref.getString("window", "15"))), "SMA-"+sharedPref.getString("window", "15"), Integer.parseInt(sharedPref.getString("window", "15")), Color.MAGENTA));
        } else {
            clicked3 = false;
            if (clicked) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 5), "SMA-5", 5, Color.BLUE));
            }
            if (clicked2) {
                dataLines.add(new DataLine(Statistics.movingAverage(currencyValues, 10), "SMA-10", 10, Color.RED));
            }
            dataLines.remove(new DataLine(Statistics.movingAverage(currencyValues, Integer.parseInt(sharedPref.getString("window", "15"))), "SMA-"+sharedPref.getString("window", "15"), Integer.parseInt(sharedPref.getString("window", "15")), Color.MAGENTA));
        }
        createChart(dataLines);
    }

    public void createChart(ArrayList<DataLine> dataLines) {

        // vi behöver en till ArrayList för dataserierna
        List<ILineDataSet> dataSeries = new ArrayList<>();

        // Vi loopar våra datalinjer och sätter in varje linje i dataSeries
        for (DataLine dataLine: dataLines) {
            LineDataSet lineDataSet = new LineDataSet(dataLine.getEntries(), dataLine.label);
            lineDataSet.setColor(dataLine.color);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            // Lägg till linjen till nuvarande dataserie
            dataSeries.add(lineDataSet);
        }

        LineData lineData = new LineData(dataSeries);

        chart.setData(lineData);
        chart.invalidate();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // Färdig metod som hämtar växelkursdata
    public ArrayList<Double> getCurrencyValues(String currency, String from, String to) {

        CurrencyApi api = new CurrencyApi();
        ArrayList<Double> currencyData = null;

        String urlString = String.format("https://api.exchangerate.host/timeseries?start_date=%s&end_date=%s&symbols=%s",
                from.trim(),
                to.trim(),
                currency.trim());
        try {
            String jsonData = api.execute(urlString).get();

            if (jsonData != null) {
                currencyData = api.getCurrencyData(jsonData, currency.trim());
                //Toast.makeText(getApplicationContext(), String.format("Hämtade %s valutakursvärden från servern", currencyData.size()), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Kunde inte hämta växelkursdata från servern: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return currencyData;
    }
}