package com.techapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ContactServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private ContactServlet servlet;

    @BeforeEach
    void setUp() {
        servlet = new ContactServlet();
    }

    @Test
    void testDoPostRedirectsCorrectly() throws Exception {
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("details")).thenReturn("Test details");

        servlet.doPost(request, response);

        verify(response).sendRedirect("/index.jsp?message=Thank+you+for+your+inquiry!");
    }
}
