package hello;

import com.mongodb.client.MongoCollection;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static spark.Spark.get;
import static spark.Spark.setPort;

/**
 * Created by aditi on 17/04/15.
 */
public class AppController {

    private final Configuration cfg;
    private DatabaseConnection dbConnection;
    private MongoCollection<Document> collection;

    public static void main(String[] args){
        try {
            if (args.length == 0) {
                new AppController("mongodb://localhost");
            } else {
                new AppController(args[0]);
            }
        }catch(Exception e){
            System.out.println("Could not start the application, error occurred: "+ e.getMessage());
        }
    }

    public AppController(String mongoURIString) throws Exception {

        dbConnection = new DatabaseConnection();
      //  dbConnection.setDbConnection();

        cfg = createFreemarkerConfiguration();
        setPort(8080);
        initializeRoutes();
    }

    private Configuration createFreemarkerConfiguration() {
        Configuration retVal = new Configuration();
        retVal.setClassForTemplateLoading(AppController.class, "/freemarker");
        return retVal;
    }

    abstract class FreemarkerBasedRoute extends Route {
        final Template template;

        /**
         * Constructor
         *
         * @param path The route path which is used for matching. (e.g. /hello, users/:name)
         */
        protected FreemarkerBasedRoute(final String path, final String templateName) throws IOException {
            super(path);
            template = cfg.getTemplate(templateName);
        }

        @Override
        public Object handle(Request request, Response response) {
            StringWriter writer = new StringWriter();
            try {
                doHandle(request, response, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/internal_error");
            }
            return writer;
        }

        protected abstract void doHandle(final Request request, final Response response, final Writer writer)
                throws IOException, TemplateException;

    }

    private void initializeRoutes() throws IOException {
        // this is the blog home page
        get(new FreemarkerBasedRoute("/", "index.ftl") {
            @Override
            public void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                SimpleHash root = new SimpleHash();

                root.put("user", "Aditi Rajawat");
                template.process(root, writer);
            }
        });

        get(new FreemarkerBasedRoute("/places", "sensorDetails.ftl") {
            @Override
            public void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                HttpRequestor httpReqClient = new HttpRequestor();
                String places= "Response not retrieved";
                String getData = "Response not retrieved";

                try{
                    getData = httpReqClient.sendGet("https://manager.gimbal.com/api/v2/places");
                }catch(Exception e){
                    System.out.println("Couldn't send request to gimbal server. Error: "+e.getMessage());
                }

                if(!getData.equalsIgnoreCase("Response not retrieved"))
                    places = new Utility().getFormattedPlaces(getData);

                SimpleHash root = new SimpleHash();
                root.put("user", "Aditi Rajawat");
                root.put("details", places);
                template.process(root, writer);
            }
        });

        get(new FreemarkerBasedRoute("/geofences", "sensorDetails.ftl") {
            @Override
            public void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                HttpRequestor httpReqClient = new HttpRequestor();
                String places= "Response not retrieved";
                String getData = "Response not retrieved";

                try{
                    getData = httpReqClient.sendGet("https://manager.gimbal.com/api/geofences");
                }catch(Exception e){
                    System.out.println("Couldn't send request to gimbal server. Error: "+e.getMessage());
                }

                if(!getData.equalsIgnoreCase("Response not retrieved"))
                   places = new Utility().getFormattedGeofences(getData);

                SimpleHash root = new SimpleHash();
                root.put("user", "Aditi Rajawat");
                root.put("details", places);
                template.process(root, writer);
            }
        });

        get(new FreemarkerBasedRoute("/beacons", "sensorDetails.ftl") {
            @Override
            public void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                HttpRequestor httpReqClient = new HttpRequestor();
                String places= "Response not retrieved";
                String getData = "Response not retrieved";

                try{
                    getData = httpReqClient.sendGet("https://manager.gimbal.com/api/beacons");
                }catch(Exception e){
                    System.out.println("Couldn't send request to gimbal server. Error: "+e.getMessage());
                }

                if(!getData.equalsIgnoreCase("Response not retrieved"))
                    places = new Utility().getFormattedBeacons(getData);

                SimpleHash root = new SimpleHash();
                root.put("user", "Aditi Rajawat");
                root.put("details", places);
                template.process(root, writer);
            }
        });
    }
}
