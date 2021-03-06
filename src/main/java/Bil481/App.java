/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Bil481;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import org.apache.logging.log4j.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    public static int findNumberOfSearchedElementInTheSameIndex(ArrayList<Integer> list1,ArrayList<Integer> list2, Integer element) 
   {    //If the element we are looking for in two arrays is in the same index, the counter is incremented by one.
        if(list1 == null && list2 == null)
        {
            return 0;
        }
        if(element == null)
        {
            return 0;
        }
        
        int counter = 0;
        int list1Size = list1.size();
        int list2Size = list2.size();
        int small = (list1Size < list2Size ? list1Size : list2Size);
        int element1, element2;
        

        for(int i = 0; i < small; i++)
        {
            element1 = list1.get(i);
            element2 = list2.get(i);
            if(element1 == element && element2 == element)
            {
                counter++;
            }
        }
        return counter;
   }


   public String getGreeting() {
    return "Hello world.";
}

public static void main(String[] argos) {
    port(getHerokuAssignedPort());

    get("/", (req, res) -> "Hello, World");

    post("/compute", (req, res) -> {

      String input1 = req.queryParams("input1");
      java.util.Scanner sc1 = new java.util.Scanner(input1);
      sc1.useDelimiter("[;\r\n]+");
      java.util.ArrayList<Integer> inputList1 = new java.util.ArrayList<>();
      while (sc1.hasNext())
      {
        int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
        inputList1.add(value);
      }
      sc1.close();
      System.out.println(inputList1);

      String input2 = req.queryParams("input2");
      java.util.Scanner sc2 = new java.util.Scanner(input2);
      sc1.useDelimiter("[;\r\n]+");
      java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
      while (sc2.hasNext())
      {
        int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
        inputList2.add(value);
      }
      sc2.close();
      System.out.println(inputList2);


      String input3 = req.queryParams("input3").replaceAll("\\s","");
      int input3AsInt = Integer.parseInt(input3);

      int result = App.findNumberOfSearchedElementInTheSameIndex(inputList1, inputList2, input3AsInt);

      Map<String, Integer> map = new HashMap<String, Integer>();
      map.put("result", result);
      return new ModelAndView(map, "compute.mustache");
    }, new MustacheTemplateEngine());


    get("/compute",
        (rq, rs) -> {
          Map<String, String> map = new HashMap<String, String>();
          map.put("result", "not computed yet!");
          return new ModelAndView(map, "compute.mustache");
        },
        new MustacheTemplateEngine());

    org.apache.logging.log4j.Logger logger = LogManager.getLogger(App.class);
    int port = Integer.parseInt(System.getenv("PORT"));
    port(getHerokuAssignedPort());
    logger.error("Current port number:" + port);
}

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


}
