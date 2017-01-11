package org.andsav.familyBudgetManager.app;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  
  public static void main(String[] args) {
    
    ConfigurableApplicationContext ax = new ClassPathXmlApplicationContext("spring/spring-app.xml");
    
    System.out.println(Arrays.toString(ax.getBeanDefinitionNames()));

    
    ax.close();
    
  }
  
}
