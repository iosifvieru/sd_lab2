import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DatabaseMonitorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // prelucrare date
        int low = Integer.parseInt(request.getParameter("minim"));
        int high = Integer.parseInt(request.getParameter("maxim"));

        String camp = request.getParameter("camp");

        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h2>Monitor db</h2>");

        TypedQuery<StudentEntity> query = em.createQuery("select student from StudentEntity student", StudentEntity.class);

        List<StudentEntity> results = query.getResultList();

        for (StudentEntity student : (List<StudentEntity>) results){
            if(camp.equals("varsta")) {
                if ((student.getVarsta() > high) || (student.getVarsta() < low)) {
                    responseText.append("<p><b>[ERROR]</b> Student ID: ").append(student.getId())
                            .append(" a cauzat o eroare, are varsta \"").append(student.getVarsta())
                            .append("\", trebuie sa aiba varsta  < ").append(high).append(" sau > ").append(low).append("</p>");
                }
            } else if(camp.equals("nume")) {
                if ((student.getNume().length() > high) || (student.getNume().length() < low)) {
                    responseText.append("<p><b>[ERROR]</b> Student ID: ").append(student.getId())
                            .append(" a cauzat o eroare, are numele \"").append(student.getNume())
                            .append("\", trebuie sa aiba lungimea  < ").append(high).append(" sau > ").append(low).append("</p>");
                }
            } else if(camp.equals("prenume")) {
                if ((student.getPrenume().length() > high) || (student.getPrenume().length() < low)) {
                    responseText.append("<p><b>[ERROR]</b> Student ID: ").append(student.getId())
                            .append(" a cauzat o eroare, are prenumele \"").append(student.getPrenume())
                            .append("\", trebuie sa aiba lungimea  < ").append(high).append(" sau > ").append(low).append("</p>");
                }
            }
        }
        response.setContentType("text/html");
        response.getWriter().print(responseText.toString());
    }
}
