import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WriteCells extends SheetsQuickstart {

    private String checked;
    private String column;
    private int convertRange;

    public WriteCells(String checked, String column, int convertRange) {
        this.checked = checked;
        this.column = column;
        this.convertRange = convertRange;
    }

    public void updateCells() throws IOException {
        String valueInputOption = "RAW"; // TODO: Update placeholder value.
        List<List<Object>> overWriteValues = Collections.singletonList(Arrays.asList(checked));
        ValueRange requestBody = new ValueRange()
                .setValues(overWriteValues);
        String overWriteRange = Integer.toString(convertRange); // TODO: Update placeholder value.
        UpdateValuesResponse result =
                service.spreadsheets().values().update(configurationSheetId, column + overWriteRange, requestBody)
                        .setValueInputOption(String.valueOf(valueInputOption))
                        .execute();
    }
}
