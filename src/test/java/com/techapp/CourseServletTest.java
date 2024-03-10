package com.techapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class CourseServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private CourseServlet servlet;

    @BeforeEach
    public void setUp() {
        // Define common behavior for mocks
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
    }

    private void testCourseParameter(String courseName, String expectedDefinition, String expectedInstallation) throws Exception {
        // Setup specific behavior for this test case
        when(request.getParameter("course")).thenReturn(courseName);

        // Execute the servlet's doPost method
        servlet.doPost(request, response);

        // Verify that the correct attributes are set based on the course parameter
        verify(request).setAttribute("definition", expectedDefinition);
        verify(request).setAttribute("installation", expectedInstallation);

        // Verify that the request is forwarded to "/index.jsp"
        verify(request).getRequestDispatcher("/index.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPostWithGitCourse() throws Exception {
        testCourseParameter("Git", "Git is a distributed version control system.", "Step 1: sudo apt-get update\nStep 2: sudo apt install git");
    }

    // Additional tests for other courses
}
