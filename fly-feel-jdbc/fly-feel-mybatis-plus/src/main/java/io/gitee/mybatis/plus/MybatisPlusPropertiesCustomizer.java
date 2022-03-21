package io.gitee.mybatis.plus;

/**
 * Callback interface that can be customized a {@link MybatisPlusProperties} object generated on auto-configuration.
 *
 * <p> 慎用 </p>
 *
 * @author miemie
 * @since 3.1.2
 */
@FunctionalInterface
public interface MybatisPlusPropertiesCustomizer {

    /**
     * Customize the given a {@link MybatisPlusProperties} object.
     *
     * @param properties the io.gitee.mybatis.plus.MybatisPlusProperties object to customize
     */
    void customize(MybatisPlusProperties properties);
}
