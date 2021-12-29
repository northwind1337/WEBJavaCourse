package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.AdminPage;
import controller.command.Courses;
import controller.command.CoursesList;
import controller.command.CreateNewCourse;
import controller.command.DeleteCertainCourse;
import controller.command.EditCertainCourse;
import controller.command.GetBackStudent;
import controller.command.KickStudent;
import controller.command.Registration;
import controller.command.SendRequest;
import controller.command.SuccessTeacherRegistration;
import controller.command.UserLoginPage;
import controller.command.UserLogoutPage;
import controller.command.student.RegistrationOnCoursePage;
import controller.command.student.StudentPage;
import controller.command.student.SuccessRegistration;
import controller.command.student.ViewStudentMyCourses;
import controller.command.student.ViewStudentOtherCourses;
import controller.command.teacher.TeacherCourses;
import controller.command.teacher.TeacherPage;
import controller.command.teacher.ViewTeacherCoursePage;


@WebServlet(urlPatterns = "/")
public class Controller extends HttpServlet {
	private Map<String, SendRequest> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig){
    	commands.put("/favicon.ico", (req) -> "/login.jsp");
    	commands.put("/", (req) -> "/login.jsp");
		commands.put("/registration", new Registration());
		commands.put("/login", new UserLoginPage());
		commands.put("/student", new StudentPage());
		commands.put("/my_courses", new ViewStudentMyCourses());
		commands.put("/courses", new Courses());
		commands.put("/course", new ViewTeacherCoursePage());
		commands.put("/logout", new UserLogoutPage());
		commands.put("/other_courses", new ViewStudentOtherCourses());
		commands.put("/course_registration", new RegistrationOnCoursePage());
		commands.put("/success_registration", new SuccessRegistration());
		commands.put("/instructor", new TeacherPage());
		commands.put("/result_course", new ViewTeacherCoursePage());
		commands.put("/instructor_courses", new TeacherCourses());
		commands.put("/admin", new AdminPage());
		commands.put("/registration_instructor", (req) -> "/registration_instructor.jsp");
		commands.put("/success_instructor_registration", new SuccessTeacherRegistration());
		commands.put("/block", new KickStudent());
		commands.put("/unblock", new GetBackStudent());
		commands.put("/create_course", new CreateNewCourse());
		commands.put("/all_courses", new CoursesList());
		commands.put("/edit_course", new EditCertainCourse());
		commands.put("/delete_course", new DeleteCertainCourse());
    }

    @Override
    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String path = request.getRequestURI();
        SendRequest command = commands.get(path);
        String page = command.execute(request);
		
        request.getRequestDispatcher(page).forward(request, response);
	}
}
