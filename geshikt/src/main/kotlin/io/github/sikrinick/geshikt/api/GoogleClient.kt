package io.github.sikrinick.geshikt.api

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.auth.oauth2.TokenResponseException
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.drive.Drive
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class GoogleClient private constructor() {
    private val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()

    private val transport = GoogleNetHttpTransport.newTrustedTransport()
    private val credential = authOnServer(transport)

    val sheets by lazy {
        (Sheets::Builder)() as Sheets
    }
    val drive by lazy {
        (Drive::Builder)() as Drive
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    @Throws(IOException::class)
    private fun authOnServer(httpTransport: NetHttpTransport): Credential {
        val credentials = File(CREDENTIALS_FILE_PATH).inputStream()

        return AuthorizationCodeInstalledApp(
            GoogleAuthorizationCodeFlow
                .Builder(
                    httpTransport,
                    jsonFactory,
                    GoogleClientSecrets.load(jsonFactory, InputStreamReader(credentials)),
                    SCOPES
                )
                .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build(),
            LocalServerReceiver.Builder()
                .setPort(8888)
                .build()
        )
            .authorize("GVault")
    }

    @Throws(TokenResponseException::class)
    private fun test() {
        drive.About().get().apply { fields = "kind" }.execute()
    }

    private fun clearTokens() {
        File(TOKENS_DIRECTORY_PATH).deleteRecursively()
    }

    private operator fun <T : AbstractGoogleJsonClient.Builder>
            ((NetHttpTransport, JsonFactory, Credential) -> T).invoke(): AbstractGoogleJsonClient =
        invoke(transport, jsonFactory, credential)
            .setApplicationName("GVault")
            .build()


    companion object {
        /**
         * Global instance of the scopes required by this quickstart.
         * If modifying these scopes, delete your previously saved tokens/ folder.
         */
        private val SCOPES = listOf(SheetsScopes.SPREADSHEETS, SheetsScopes.DRIVE)
        private const val CREDENTIALS_FILE_PATH = "src/main/resources/credentials.json"
        private const val TOKENS_DIRECTORY_PATH = "src/main/resources/tokens"

        operator fun invoke(): GoogleClient = with(GoogleClient()) {
            try {
                test()
                this
            } catch (ex: TokenResponseException) {
                clearTokens()
                invoke()
            }
        }
    }
}