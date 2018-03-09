package common.backend

import groovy.util.logging.Slf4j
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.beans.factory.config.BeanDefinition
//import org.springframework.context.annotation.AnnotationConfigApplicationContext
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.apache.commons.lang3.builder.RecursiveToStringStyle
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.text.WordUtils
import org.springframework.core.annotation.AnnotationUtils

@Slf4j
public class ProxiedSessionBeanPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(
            ConfigurableListableBeanFactory beanFactory)
            throws BeansException {

        log.debug "Busca os beans registrado no ApplicationContext, que tem a annotation ProxiedBean.class"
        String[] annBeans = ((DefaultListableBeanFactory) beanFactory).getBeanNamesForAnnotation(ProxiedBean.class)

        for (String beanName in annBeans) {
            // Para cada bean encontrado ...

            log.debug "Busca o registro do bean ( tal como definido para o Spring )."
            BeanDefinition beanDef = ((DefaultListableBeanFactory) beanFactory).getBeanDefinition(beanName)

            log.debug "Busca os valores da annotation ProxiedBean.class"
            ProxiedBean ann = AnnotationUtils.findAnnotation(Class.forName(beanDef.beanClassName), ProxiedBean)

            log.debug "Remove o registro do bean original ( ${beanName} )"
            ((DefaultListableBeanFactory) beanFactory).removeBeanDefinition(beanName)

            String oldBeanName = "_${beanName}"

            log.debug "Registra o bean original com nome modificado ( _${beanName} )"
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition( oldBeanName ,beanDef)

            log.debug "Cria o registro do ScopedProxyFactoryBean requerido"
            GenericBeanDefinition bd = new GenericBeanDefinition();
            bd.setBeanClass(org.springframework.aop.scope.ScopedProxyFactoryBean)
            bd.getPropertyValues().add("targetBeanName", oldBeanName)
            bd.getPropertyValues().add("proxyTargetClass", (ann.proxyTargetClass() == BooleanEnum.TRUE))

            log.debug "Registra o proxy com nome Original (${beanName}) !"
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("${beanName}", bd)

            log.debug "Registra o proxy com nomes alternativos : "

            log.debug " - proxied_${beanName}"
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("proxied_${beanName}", bd)

            log.debug " - ${beanName}Proxied"
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("${beanName}Proxied", bd)

            log.debug " - ${beanName}Proxy"
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("${beanName}Proxy", bd)

            beanName = WordUtils.capitalize(beanName)
            log.debug " - proxied${beanName}"
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("proxied${beanName}", bd)
        }

    }
}
