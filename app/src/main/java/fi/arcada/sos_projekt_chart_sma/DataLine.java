package fi.arcada.sos_projekt_chart_sma;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;


public class DataLine {
    ArrayList<Double> dataValues;
    String label;
    int startPosition; // x offset
    int color; // t.ex. Color.BLUE ger ett heltal

    // Konstruktormetod som vi anropar när vi skapar ett objekt av denna klass
    public DataLine(ArrayList<Double> dataValues, String label, int startPosition, int color) {
        this.dataValues = dataValues;
        this.label = label;
        this.startPosition = startPosition;
        this.color = color;
    }

    public List<Entry> getEntries() {

        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < dataValues.size(); i++) {
            // vi adderar startPosition till position på X
            entries.add(new Entry(i+startPosition, dataValues.get(i).floatValue()));
        }
        return entries;
    }
}
