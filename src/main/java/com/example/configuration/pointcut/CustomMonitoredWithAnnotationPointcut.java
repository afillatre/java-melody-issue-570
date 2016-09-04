package com.example.configuration.pointcut;

import com.example.service.CustomService;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * Pointcut that identifies methods/classes with the {@link MonitoredWithSpring} annotation.
 *
 * @author Erik van Oosten (Java Simon, Licence LGPL)
 */
public class CustomMonitoredWithAnnotationPointcut implements Pointcut {
	/**
	 * @return a class filter that lets all class through.
	 */
	@Override
	public ClassFilter getClassFilter() {
		return ClassFilter.TRUE;
	}

	/**
	 * @return a method matcher that matches any method that has the {@link MonitoredWithSpring} annotation,
	 *         or is in a class with the {@link MonitoredWithSpring} annotation
	 */
	@Override
	public MethodMatcher getMethodMatcher() {
		return MonitoredMethodMatcher.INSTANCE;
	}

	private enum MonitoredMethodMatcher implements MethodMatcher {
		INSTANCE;

		/** {@inheritDoc} */
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public boolean matches(Method method, Class targetClass) {
			return matches(method, targetClass, MonitoredWithSpring.class) ||
					matches(method, targetClass, CustomService.class);
		}

		private boolean matches(Method method, Class targetClass, Class annotationClass) {
			return targetClass.isAnnotationPresent(annotationClass)
					|| method.getDeclaringClass().isAnnotationPresent(annotationClass)
					|| method.isAnnotationPresent(annotationClass);
		}

		/** {@inheritDoc} */
		@Override
		public boolean isRuntime() {
			return false;
		}

		/** {@inheritDoc} */
		@Override
		@SuppressWarnings("rawtypes")
		public boolean matches(Method method, Class targetClass, Object[] args) {
			throw new UnsupportedOperationException("This is not a runtime method matcher");
		}
	}
}
