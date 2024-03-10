package com.techapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private ContactServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new ContactServlet();
    }

    @Test
    public void testDoPostRedirectsCorrectly() throws Exception {
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("details")).thenReturn("Test details");

        servlet.doPost(request, response);

        verify(response).sendRedirect("/index.jsp?message=Thank+you+for+your+inquiry!");
    }
}
