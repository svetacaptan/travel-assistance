import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/hello", (req, res) -> "Hello World");

    get("/webhook", (request, response) -> request.params(":hub.challenge"));




//    get("/db", (req, res) -> {
//      Connection connection = null;
//      Map<String, Object> attributes = new HashMap<>();
//      try {
//        connection = DatabaseUrl.extract().getConnection();
//
//        Statement stmt = connection.createStatement();
//        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//        ArrayList<String> output = new ArrayList<String>();
//        while (rs.next()) {
//          output.add( "Read from DB: " + rs.getTimestamp("tick"));
//        }
//
//        attributes.put("results", output);
//        return new ModelAndView(attributes, "db.ftl");
//      } catch (Exception e) {
//        attributes.put("message", "There was an error: " + e);
//        return new ModelAndView(attributes, "error.ftl");
//      } finally {
//        if (connection != null) try{connection.close();} catch(SQLException e){}
//      }
//    }, new FreeMarkerEngine());

  }

}
