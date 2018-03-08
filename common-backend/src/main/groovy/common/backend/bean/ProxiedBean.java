package common.backend;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
//import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Marca classes que tem scope "session" e necessitam ter associado um ScopedProxyFactoryBean.
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
	String targetBeanName();

	/**
	 * Descricao da "Action"
	 *
	 * @return
	 */
	BooleanEnum proxyTargetClass() default BooleanEnum.TRUE;


}
