// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// [START sheets_quickstart]

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public abstract class SheetsQuickstart {

    public static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    public static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final String TOKENS_DIRECTORY_PATH = "tokens";
    public static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    public static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    public static final String configurationSheetId = "1VK2XzGxroFH4uhwo4ohALRs5Zaq59a5XlQTeCtj-ZR8";

    public static final NetHttpTransport HTTP_TRANSPORT;
    public static final String range = "A4:H";
    public static final Sheets service;
    public static final ValueRange response;
    public static final ValueRange responseClasses;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            service = new Sheets.Builder(HTTP_TRANSPORT, SheetsQuickstart.JSON_FACTORY, Credential.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            response = service.spreadsheets().values().get(configurationSheetId, range).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            responseClasses = service.spreadsheets().values().get(configurationSheetId, "C2:H2").execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}