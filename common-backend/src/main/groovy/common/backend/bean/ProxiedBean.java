package common.backend.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import common.backend.utils.BooleanEnum;

import static java.lang.annotation.ElementType.TYPE;
//import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Marca classes que tem scope que necessitam ser instanciadas através de um
 * {@link org.springframework.aop.scope.ScopedProxyFactoryBean ScopedProxyFactoryBean}.
 *
 * @author helder.morais
 *
 */
@Retention(RUNTIME)
@Target({ TYPE })
public @interface ProxiedBean {

	/**
	 * nome do Bean ao qual o proxy será associado
	 *
	 * @return
	 */
	String targetBeanName() default ".";

	BooleanEnum proxyTargetClass() default BooleanEnum.TRUE;


}
