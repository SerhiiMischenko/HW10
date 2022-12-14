package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Json {
    public static Object jsonReader() throws IOException {
        StringBuilder json = new StringBuilder();
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("file2.txt"));
        while (reader.ready()) {
            String[] strings = reader.readLine().split(" ");
            for (int i = 0; i < strings.length - 1; i++) {
                try {
                     users.add(new User (strings[i], Integer.parseInt(strings[i + 1])));

                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json.append(gson.toJson(users));
        return json.toString();
    }

    public static void jsonWriter(Object json) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("user.json"))) {
            writer.write((String) json);
        }
    }
    public static void main(String[] args) throws IOException {
        jsonWriter(jsonReader());
    }
}
