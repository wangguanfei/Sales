package com.basis.core.expand.hibernate;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 * 为Hibernate添加SQL方法:bitand,让其支持"&"查询;
 *
 */
public class BitAndFunction implements SQLFunction {
	public Type getReturnType(Type type, Mapping mapping) {
	   return LongType.INSTANCE;
	}
	public boolean hasArguments() {
	   return true;
	}
	public boolean hasParenthesesIfNoArguments() {
	   return true;
	}
	public String render(Type firstArgumentType, List arguments,
			SessionFactoryImplementor factory) throws QueryException {
		if (arguments.size() != 2) {
			throw new IllegalArgumentException("BitAndFunction requires 2 arguments!");
		}
		return arguments.get(0).toString() + " & " + arguments.get(1).toString();
	}

}