// ExerciseParser.java

import java.io.*;
import java.util.*;
import java.net.URL;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class ExerciseParser{

    // URL of the JSON file
    private static final String exampleURL = "https://raw.githubusercontent.com/yuhonas/free-exercise-db/refs/heads/main/dist/exercises.json";

    public ArrayList<Exercise> parseExercise(){        
        try {

            // Create a URL object with the JSON file URL
            URL url = new URL(exampleURL);

            // Open a connection to the URL and read the JSON data
            InputStreamReader reader = new InputStreamReader(url.openStream());

            // Use Gson to parse the JSON data into an ArrayList of Exercise objects
            Gson gson = new Gson();
            // Need to define generic type using anoymous class
            Type exerciseListType = new TypeToken<ArrayList<Exercise>>(){}.getType();
            // Deserialize the JSON data
            ArrayList<Exercise> exercise = gson.fromJson(reader, exerciseListType);
            
            // Close the InputStreamReader
            reader.close();

            return exercise;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } // end try-catch
    } // end parseExercise

    

    public static void main(String[] args) {
        // for testing purposes
        ExerciseParser parser = new ExerciseParser();
        ArrayList<Exercise> exercises = parser.parseExercise();

        if (exercises != null) {
            System.out.println("Parsed " + exercises.size() + " exercises.");
            for (Exercise exercise : exercises) {
                System.out.println("Name: " + exercise.getName() + " " + exercises.indexOf(exercise));
            } // end for
        } else {
            System.out.println("Failed to parse exercises.");
        } // end if-else

    } // end main
    
} // end class