import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class read extends SheetsQuickstart {

    public static void main(String... args) throws IOException, GeneralSecurityException {

        Sheets service =
                new Sheets.Builder(HTTP_TRANSPORT, SheetsQuickstart.JSON_FACTORY, Credential.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
        ValueRange response = service.spreadsheets().values()
                .get(configurationSheetId, range)
                .execute();

        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Matricula, Aluno, Faltas, P1, P2, P3, Situacao, Nota para Exame Final");
            for (List row : values) {
                System.out.println(row.get(0) + ", " + row.get(1) + ", " + row.get(2) + ", " + row.get(3) + ", " + row.get(4) + ", " + row.get(5) + ", " + row.get(6) + ", " + row.get(7));
            }
        }
    }
}
