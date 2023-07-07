import java.util.ArrayList;
import java.util.List;

public class OptimalWarungStops {
    public static void main(String[] args) {
        List<Integer> warung = List.of(10, 25, 30, 40, 50, 75, 80, 110, 130);

        int minStops = Integer.MAX_VALUE;
        List<Integer> bestCombination = new ArrayList<>();

        // Perulangan untuk mencari semua kombinasi
        for (int i = 0; i < (1 << warung.size()); i++) {
            List<Integer> combination = new ArrayList<>();

            // Memeriksa setiap bit untuk menentukan apakah warung tersebut dipilih atau tidak
            for (int j = 0; j < warung.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    combination.add(warung.get(j));
                }
            }

            // Memeriksa apakah kombinasi saat ini memenuhi persyaratan
            if (isValidCombination(combination, 140, 30)) {
                // Menghitung jumlah berhenti pada kombinasi saat ini
                int numStops = combination.size();

                // Memeriksa apakah kombinasi saat ini lebih optimal
                if (numStops < minStops) {
                    minStops = numStops;
                    bestCombination = new ArrayList<>(combination);
                }
            }
        }

        // Menampilkan titik-titik warung Tono akan berhenti
        System.out.print("Titik-titik Tono akan berhenti di kilometer: ");
        for (int i = 0; i < bestCombination.size(); i++) {
            System.out.print(bestCombination.get(i) + " ");
        }
        System.out.println();
    }

    // Memeriksa apakah kombinasi titik-titik warung memenuhi persyaratan
    private static boolean isValidCombination(List<Integer> combination, int totalDistance, int restDistance) {
        // Memeriksa apakah total jarak yang dapat ditempuh Tono dengan berhenti di warung-warnug memenuhi persyaratan
        int distance = 0;
        for (int i = 0; i < combination.size(); i++) {
            if (combination.get(i) - distance > restDistance) {
                return false;
            }
            distance = combination.get(i);
        }
        if (totalDistance - distance > restDistance) {
            return false;
        }
        return true;
    }
}
