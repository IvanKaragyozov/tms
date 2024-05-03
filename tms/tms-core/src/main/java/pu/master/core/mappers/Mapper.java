package pu.master.core.mappers;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;


/**
 * Used to annotate a mapper class as a component. Works the same way as
 * {@link Component}.
 */
@Target(ElementType.TYPE)
@Component
@interface Mapper
{
}
