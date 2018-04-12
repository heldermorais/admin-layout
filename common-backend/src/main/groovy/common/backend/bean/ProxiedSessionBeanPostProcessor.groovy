package common.backend.bean

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
import org.apache.commons.text.WordUtils
import org.springframework.core.annotation.AnnotationUtils

import common.backend.utils.BooleanEnum

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

            log.debug "Buscando o registro de ${beanName} no container IoC Spring ."
            BeanDefinition beanDef = ((DefaultListableBeanFactory) beanFactory).getBeanDefinition(beanName)


            log.debug "Buscando os valores anotados em ProxiedBean para ${beanDef.beanClassName}"
            ProxiedBean ann = AnnotationUtils.findAnnotation(Class.forName(beanDef.beanClassName), ProxiedBean)
            def proxyTargetClass = ann.proxyTargetClass() == BooleanEnum.TRUE

            log.debug "Removendo o registro original ( ${beanName} )"
            ((DefaultListableBeanFactory) beanFactory).removeBeanDefinition(beanName)

            String oldBeanName = "_${beanName}"



            log.debug "Registrando ${beanName} com nome modificado ( _${beanName} )"
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition( oldBeanName ,beanDef)



            log.debug "Criando registro para ScopedProxyFactoryBean (${oldBeanName})."
            GenericBeanDefinition bd = new GenericBeanDefinition()
            bd.setBeanClass(org.springframework.aop.scope.ScopedProxyFactoryBean)
            bd.getPropertyValues().add("targetBeanName", oldBeanName)
            bd.getPropertyValues().add("proxyTargetClass", proxyTargetClass)

            log.debug "Registrando o proxy como (${beanName})."
            ((DefaultListableBeanFactory) beanFactory).registerAlias( oldBeanName, "${beanName}")




            log.debug "Registrando nomes alternativos para ${beanName}: "

            log.debug " - Alias: proxied_${beanName}"
            ((DefaultListableBeanFactory) beanFactory).registerAlias("${beanName}","proxied_${beanName}")

            log.debug " - Alias: ${beanName}Proxied"
            ((DefaultListableBeanFactory) beanFactory).registerAlias("${beanName}","${beanName}Proxied")

            log.debug " - Alias: ${beanName}Proxy"
            ((DefaultListableBeanFactory) beanFactory).registerAlias("${beanName}","${beanName}Proxy")

            beanName = WordUtils.capitalize(beanName)
            log.debug " - Alias: proxied${beanName}"
            ((DefaultListableBeanFactory) beanFactory).registerAlias("${beanName}","proxied${beanName}")
        }

    }
}
