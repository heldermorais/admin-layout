package common.backend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.lang3.builder.RecursiveToStringStyle
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.text.WordUtils;

public class ProxiedSessionBeanPostProcessor implements BeanFactoryPostProcessor {


  @Override
public void postProcessBeanFactory (
          ConfigurableListableBeanFactory beanFactory)
          throws BeansException {

    //GenericBeanDefinition bd = new GenericBeanDefinition();
    //bd.setBeanClass(MyBean.class);
    //bd.getPropertyValues().add("strProp", "my string property");

    String[] annBeans = ((DefaultListableBeanFactory) beanFactory).getBeanNamesForAnnotation(ProxiedBean);
    for(String beanName in annBeans){
       BeanDefinition beanDef = ((DefaultListableBeanFactory) beanFactory).getBeanDefinition(beanName)
       String string = new ReflectionToStringBuilder(beanDef, new RecursiveToStringStyle()).toString();
       println "BeanDef: ${string}"

       GenericBeanDefinition bd = new GenericBeanDefinition();
       bd.setBeanClass(org.springframework.aop.scope.ScopedProxyFactoryBean);
       bd.getPropertyValues().add("targetBeanName", beanName);
       bd.getPropertyValues().add("proxyTargetClass", true); //TODO: Implementar por BooleanEnum
               //proxyTargetClass = true

       ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("proxied_${beanName}", bd);
       ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("${beanName}Proxy", bd);
       beanName = WordUtils.capitalize(beanName)
       ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("proxied${beanName}", bd);
    }
              //.registerBeanDefinition("myBeanName", bd);
}
}
