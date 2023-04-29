import javax.net.ssl.HttpsURLConnection;
import java.net.*;
import java.io.*;

public class WebsiteReader {
    private String word;
    private HttpsURLConnection connection;
    private URL url;

    public WebsiteReader() throws Exception {
        // Create a URL object
        this.url = new URI("https://somelar.dev/eestiwordle/fetchRandomWord.php").toURL();
        this.connection = null;
    }

    public void newWord() throws Exception {
        // Open a connection to the URL
        this.connection = (HttpsURLConnection) url.openConnection();

        // Set the request method to GET
        this.connection.setRequestMethod("GET");

        // Get the input stream from the connection
        InputStream inputStream = connection.getInputStream();

        // Create a buffered reader to read the input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Read the text from the website
        String line;
        StringBuilder body = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        // Close the connection, buffered reader and input stream
        connection.disconnect();
        reader.close();
        inputStream.close();

        this.word = body.toString();
    }

    @Override
    public String toString() {
        return this.word;
    }
}
