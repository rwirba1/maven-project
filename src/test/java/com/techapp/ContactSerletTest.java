package com.techapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.*;

class ContactServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private ContactServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new ContactServlet();
    }

    @Test
    void doPostRedirectsCorrectly() throws Exception {
        // Mock the request parameters
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("details")).thenReturn("Sample inquiry details");

        // Call the servlet's doPost method
        servlet.doPost(request, response);

        // Verify the servlet redirects to the expected URL
        verify(response).sendRedirect("/index.jsp?message=Thank+you+for+your+inquiry!");
    }
}
