import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        // delete la id primit in request.
        TypedQuery<StudentEntity> query = em.createQuery("UPDATE StudentEntity student SET student.nume=:nume, student.prenume=:prenume, student.varsta=:varsta WHERE student.id = :id", StudentEntity.class);
        query.setParameter("id", id);
        query.setParameter("nume", nume);
        query.setParameter("prenume", prenume);
        query.setParameter("varsta", varsta);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        query.executeUpdate();

        transaction.commit();

        response.sendRedirect("./fetch-student-list");

        em.close();
        factory.close();

    }
}
