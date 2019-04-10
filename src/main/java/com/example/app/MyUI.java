package com.example.app;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MyUI extends UI {

	static Navigator navigator;
    protected static final String MAINVIEW = "main";
    protected static final String LOGINVIEW = "login";
    protected static final String HOMEPAGE = "home";
    protected static final String SIGNUP = "signup";
    protected static final String FORGOTVIEW = "forgot password";
 
    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation Example");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new LoginView());
        navigator.addView(LOGINVIEW, new LoginView());
      navigator.addView(MAINVIEW, new MainView());
      navigator.addView(HOMEPAGE, new HomePage());
      navigator.addView(SIGNUP, new SignupView());
      navigator.addView(FORGOTVIEW, new ForgotPassView());
      
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
