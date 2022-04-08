package io.gitee.generate.resolver;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;


public class IntegerJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public IntegerJavaTypeResolver() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
        super.typeMap.put(Types.BIT, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Boolean.class.getName())));
        super.typeMap.put(Types.BLOB, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BLOB", new FullyQualifiedJavaType(String.class.getName())));
    }
}