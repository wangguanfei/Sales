package com.basis.core.expand.hibernate;

import org.hibernate.dialect.MySQLInnoDBDialect;
/**
 * 
 * 继承MySQLInnoDBDialect方言,并加入bitand方法
 *
 */
public class ExpandMySQLInnoDBDialect extends MySQLInnoDBDialect {
	/**
	*
	*/
	public ExpandMySQLInnoDBDialect() {
		super();
		registerFunction("bitand", new BitAndFunction());
		registerFunction("bitleft", new BitLeftFunction());
	}
}