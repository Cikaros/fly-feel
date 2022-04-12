package io.gitee.openfeign.support;

import feign.QueryMapEncoder;
import feign.codec.EncodeException;
import org.springframework.core.convert.ConversionService;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 参考类 BeanQueryMapEncoder,增加了ConversionService支持 使得bean中如果有如时间(LocalDateTime,LocalDate,Date这些属性都可以根据实际情况转换)
 */
public class ConvertibleBeanQueryMapEncoder implements QueryMapEncoder {

    private final Map<Class<?>, ObjectParamMetadata> classToMetadata = new HashMap<>();

    private final ConversionService conversionService;

    public ConvertibleBeanQueryMapEncoder(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Map<String, Object> encode(Object object) throws EncodeException {
        try {
            ObjectParamMetadata metadata = getMetadata(object.getClass());
            Map<String, Object> propertyNameToValue = new HashMap<>();
            for (PropertyDescriptor pd : metadata.objectProperties) {
                Object value = pd.getReadMethod().invoke(object);

                if (value != null && value != object) {

                    if (!(value instanceof CharSequence) && canConvert(pd)) {
                        value = conversionService.convert(value, String.class);
                    }

                    propertyNameToValue.put(pd.getName(), value);
                }
            }
            return propertyNameToValue;
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            throw new EncodeException("Failure encoding object into query map", e);
        }
    }

    private boolean canConvert(PropertyDescriptor pd) {
        return conversionService != null &&
                conversionService.canConvert(pd.getPropertyType(), String.class);
    }

    private ObjectParamMetadata getMetadata(Class<?> objectType) throws IntrospectionException {
        ObjectParamMetadata metadata = classToMetadata.get(objectType);
        if (metadata == null) {
            metadata = ObjectParamMetadata.parseObjectType(objectType);
            classToMetadata.put(objectType, metadata);
        }
        return metadata;
    }

    private static class ObjectParamMetadata {

        private final List<PropertyDescriptor> objectProperties;

        private ObjectParamMetadata(List<PropertyDescriptor> objectProperties) {
            this.objectProperties = Collections.unmodifiableList(objectProperties);
        }

        private static ObjectParamMetadata parseObjectType(Class<?> type)
                throws IntrospectionException {
            List<PropertyDescriptor> properties = new ArrayList<>();

            for (PropertyDescriptor pd : Introspector.getBeanInfo(type).getPropertyDescriptors()) {
                boolean isGetterMethod = pd.getReadMethod() != null && !"class".equals(pd.getName());
                if (isGetterMethod) {
                    properties.add(pd);
                }
            }

            return new ObjectParamMetadata(properties);
        }
    }
}

