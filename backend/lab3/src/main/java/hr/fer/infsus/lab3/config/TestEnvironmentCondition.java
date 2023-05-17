package hr.fer.infsus.lab3.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class TestEnvironmentCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String environment = context.getEnvironment().getProperty("spring.profiles.active");
        return StringUtils.hasText(environment) && environment.equalsIgnoreCase("dev");
	}

}
