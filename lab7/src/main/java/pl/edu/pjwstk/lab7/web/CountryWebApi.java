package pl.edu.pjwstk.lab7.web;

        import pl.edu.pjwstk.lab7.domain.Country;
        import pl.edu.pjwstk.lab7.service.CountryManager;

        import java.io.IOException;
        import java.io.PrintWriter;
        import java.sql.SQLException;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api")
public class CountryWebApi extends HttpServlet {

    CountryManager countryManager = new CountryManager();

    private static final long serialVersionUID = 1L;

    public CountryWebApi() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        out.println("{\"data\":[");
        String cmma = "";
        for (Country c : countryManager.getAllCountries()) {
            out.println(cmma+ "{"+
                    "\"id\":" + c.getId() + "," +
                    "\"country\":\"" + c.getCountry() + "\"," +
                    "\"city\":" + c.getCity() + "\"," +
                    "\"postal_code\":" + c.getPostal_code() +
                    "}");
            cmma = ",";
        }
        out.println("]}");
        out.close();
    }

}