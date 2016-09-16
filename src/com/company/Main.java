package com.company;


        import com.google.gson.Gson;
        import com.google.gson.JsonArray;
        import org.json.JSONArray;

        import java.io.BufferedReader;
        import java.io.DataOutputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

        import javax.net.ssl.HttpsURLConnection;

public class Main {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        Main http = new Main();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

    }

    // HTTP GET request
    private void sendGet() throws Exception {

        String url = "https://jsonplaceholder.typicode.com/comments";

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        List<Comment> comments = ParseJson(response.toString());

        for (int i=0; i<comments.size(); i++)
        {
            System.out.println(comments.get(i).email);
        }

    }


    private List<Comment> ParseJson(String inputjson) {
        List<Comment> comments = new ArrayList<>();

        JSONArray myjsonarry= new JSONArray(inputjson);

        int count= myjsonarry.length();
        //print result
        System.out.println(count);
        Gson gson = new Gson();

        for (int i=0; i<myjsonarry.length(); i++)
        {
            Comment c= gson.fromJson(myjsonarry.get(i).toString(),Comment.class);
            comments.add(c);
        }
        return comments;
    }

}



/*public class Main {

    public static void main(String[] args) {
	// write your code here
    }*/










