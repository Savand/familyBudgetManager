package org.andsav.familyBudgetManager.app;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  
  public static void main(String[] args) {
    
    ConfigurableApplicationContext ax = new ClassPathXmlApplicationContext("spring/spring-app.xml");
    
    ax.close();
    
  }
  
}
