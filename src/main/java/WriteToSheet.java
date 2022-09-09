import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class WriteToSheet extends SheetsQuickstart {

    public static void main(String[] args) throws IOException, GeneralSecurityException {

        List<List<Object>> values = response.getValues();
        List<List<Object>> valueClasses = responseClasses.getValues();
        int totalClasses = 0;
        for (List amountClasses : valueClasses) {
            totalClasses = Integer.parseInt((String) amountClasses.get(0));
        }
        int maxFaults = (int) (totalClasses * 0.25);

        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {

            for (List row : values) {
                int convertRange = Integer.parseInt((String) row.get(0)) + 3;
                int faults = Integer.parseInt((String) row.get(2));
                float p1 = Float.parseFloat((String) row.get(3));
                float p2 = Float.parseFloat((String) row.get(4));
                float p3 = Float.parseFloat((String) row.get(5));
                int m = Math.round(((p1 + p2 + p3) / 3) / 10);
                int naf = 10 - m;
                WriteCells fillZeroNaf = new WriteCells("0", "H", convertRange);
                if (faults > maxFaults) {
                    WriteCells failedLack = new WriteCells("Reprovado por Falta", "G", convertRange);
                    failedLack.updateCells();
                    fillZeroNaf.updateCells();
                } else if (m < 5) {
                    WriteCells failedGrade = new WriteCells("Reprovado por Nota", "G", convertRange);
                    failedGrade.updateCells();
                    fillZeroNaf.updateCells();
                } else if (m >= 5 & m < 7) {
                    WriteCells finalExam = new WriteCells("Exame Final", "G", convertRange);
                    WriteCells finalExamGradle = new WriteCells(Integer.toString(naf), "H", convertRange);
                    finalExam.updateCells();
                    finalExamGradle.updateCells();
                } else {
                    WriteCells approved = new WriteCells("Aprovado", "G", convertRange);
                    approved.updateCells();
                    fillZeroNaf.updateCells();
                }
            }
            read.main();
        }
    }
}