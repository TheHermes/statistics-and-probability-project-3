package fi.arcada.sos_projekt_chart_sma;

import java.util.ArrayList;

public class Statistics {

    /**
     * Glidande medelvärde (Simple Moving Avg)
     */
    static ArrayList<Double> movingAverage(ArrayList<Double> dataset, int window) {
        ArrayList<Double> ma = new ArrayList<>();

        for (int i = 0; i < dataset.size()-window; i++) {
            double sum = 0;
            // Inre loop loopar igenom window
            for (int j = 0; j < window; j++) {
                sum += dataset.get(i-j+window); // vi börjar från dataset-index + fönsterstorleken
            }
            ma.add(sum/window);
        }
        return ma;
    }

}
