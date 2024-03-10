package com.techapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import javax.servlet.http.*;
import javax.servlet.*;

class CourseServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private CourseServlet servlet;

    @BeforeEach
    void setUp() {
        // Initialize mock objects
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        servlet = new CourseServlet();

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
    void doPostWithGitCourse() throws Exception {
        testCourseParameter("Git", "Git is a distributed version control system.", "Step 1: sudo apt-get update\nStep 2: sudo apt install git");
    }

    @Test
    void doPostWithJenkinsCourse() throws Exception {
        testCourseParameter("Jenkins", "Jenkins is a popular automation server.", "Step 1: sudo apt-get update\nStep 2: sudo apt install jenkins");
    }

    @Test
    void doPostWithAnsibleCourse() throws Exception {
        testCourseParameter("Ansible", "Ansible is an open-source software provisioning, configuration management, and application-deployment tool.", "Step 1: sudo apt-get update\nStep 2: sudo apt install ansible");
    }

    @Test
    void doPostWithTerraformCourse() throws Exception {
        testCourseParameter("Terraform", "Terraform is an infrastructure as code tool for building, changing, and versioning infrastructure safely and efficiently.", "Step 1: sudo apt-get update\n(Additional installation steps for Terraform)");
    }

    @Test
    void doPostWithDockerCourse() throws Exception {
        testCourseParameter("Docker", "Docker is a platform used to develop, ship, and run applications inside containers.", "Step 1: sudo apt-get update\nStep 2: sudo apt install docker.io");
    }

    @Test
    void doPostWithKubernetesCourse() throws Exception {
        testCourseParameter("Kubernetes", "Kubernetes is an open-source container orchestration platform.", "Step 1: sudo apt-get update\n(Additional installation steps for Kubernetes)");
    }
}
