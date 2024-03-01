import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteStudentServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        // delete la id primit in request.
        TypedQuery<StudentEntity> query = em.createQuery("DELETE from StudentEntity student WHERE student.id = :id", StudentEntity.class);
        query.setParameter("id", id);
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        query.executeUpdate();

        transaction.commit();

        //List<StudentEntity> results = query.getResultList();

        response.sendRedirect("./fetch-student-list");

        em.close();
        factory.close();
    }
}
