package common.backend.web.actions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marca os metodos dos Controllers para que estes possam ser renderizados
 * automaticamente.
 * 
 * @author heldermorais
 *
 */
@Retention(RUNTIME)
@Target({ METHOD })
public @interface ActionDescription {

	/**
	 * Titulo da "Action"
	 * 
	 * @return
	 */
	String title();

	/**
	 * Descricao da "Action"
	 * 
	 * @return
	 */
	String description() default "";
	
	String icon() default "";

}
