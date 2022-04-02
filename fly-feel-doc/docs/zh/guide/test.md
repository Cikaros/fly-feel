## 单元测试

单元测试时，请在业务模块的`src/test/java`路径下创建测试类并继承`io.gitee.test.SpringBootTestParent~~~~`这个入口即可。

```java
import io.gitee.test.SpringBootTestParent;

/**
 * 测试样例
 *
 * @author Cikaros
 * @date 2022/3/25
 * @since v1.0
 */
public class SimpleTest extends SpringBootTestParent {

}
```

## http测试

http测试请使用[Idea-Http-Test-Util](https://cikaros.gitee.io/doc/b1e46e75.html)

## Podam工具

Podam官方文档地址：[http://mtedone.github.io/podam/](http://mtedone.github.io/podam/)

### Podam API架构图

![Podam API架构图](/images/podam-architecture.png)

### Podam 类型构建架构图

![Podam 类型构建架构图](/images/type-manufacturers-architecture.png)

### Podam 注解说明

#### `@PodamStrategyValue`

这是所有注释中最重要的注释。它允许用户自定义属性的生成策略。

要创建自定义策略，请创建`AttributeStrategy<T>`接口的实现并在注解中标注它。例如：

```java
@PodamStrategyValue(PostCodeStrategy.class)
private String postCode;

@PodamStrategyValue(MyBirthdayStrategy.class)
private Calendar myBirthday;
```

#### `@PodamBooleanValue`

它允许自定义布尔属性值。且只能为`true`/`false`。例如：

```java
/**
 * 值强制为 true 的布尔字段
 */
@PodamBooleanValue(boolValue = true)
private boolean boolDefaultToTrue;

/**
 * 值强制为 false 的布尔字段
 */
@PodamBooleanValue(boolValue = false)
private boolean boolDefaultToFalse=true;
```

#### `@PodamByteValue`

它允许自定义Byte属性。例如：

```java
@PodamByteValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
private byte byteFieldWithMinValueOnly;

@PodamByteValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
private byte byteFieldWithMaxValueOnly;

@PodamByteValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
private byte byteFieldWithMinAndMaxValue;

@PodamByteValue(numValue = PodamTestConstants.BYTE_PRECISE_VALUE)
private byte byteFieldWithPreciseValue;
```

#### `@PodamShortValue`

它允许自定义Short属性。例如：

```java
@PodamShortValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
private short shortFieldWithMinValueOnly;

@PodamShortValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
private short shortFieldWithMaxValueOnly;

@PodamShortValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
private short shortFieldWithMinAndMaxValue;

@PodamShortValue(numValue = PodamTestConstants.SHORT_PRECISE_VALUE)
private short shortFieldWithPreciseValue;
```

#### `@PodamCharValue`

它允许自定义Character属性。例如：

```java
@PodamCharValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
private char charFieldWithMinValueOnly;

@PodamCharValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
private char charFieldWithMaxValueOnly;

@PodamCharValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
private char charFieldWithMinAndMaxValue;

@PodamCharValue(charValue = PodamTestConstants.CHAR_PRECISE_VALUE)
private char charFieldWithPreciseValue;
```

#### `@PodamIntValue`

它允许自定义Integer属性。例如：

```java
@PodamIntValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
private int intFieldWithMinValueOnly;

@PodamIntValue(numValue = PodamTestConstants.INTEGER_PRECISE_VALUE)
private int intFieldWithPreciseValue;

@PodamIntValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
private int intFieldWithMaxValueOnly;

@PodamIntValue(numValue = PodamTestConstants.INTEGER_PRECISE_VALUE)
private Integer integerObjectFieldWithPreciseValue;
```

#### `@PodamLongValue`

它允许自定义Long属性。例如：

```java
@PodamLongValue(minValue = 0)
private long longFieldWithMinValueOnly;

@PodamLongValue(maxValue = 100)
private long longFieldWithMaxValueOnly;

@PodamLongValue(minValue = 0, maxValue = 1000)
private long longFieldWithMinAndMaxValue;

@PodamLongValue(numValue = PodamTestConstants.LONG_PRECISE_VALUE)
private Long longObjectFieldWithPreciseValue;
```

#### `@PodamFloatValue`

它允许自定义Float属性。例如：

```java
@PodamFloatValue(minValue = PodamTestConstants.NUMBER_FLOAT_MIN_VALUE)
private float floatFieldWithMinValueOnly;

@PodamFloatValue(maxValue = PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED)
private float floatFieldWithMaxValueOnly;

@PodamFloatValue(minValue = PodamTestConstants.NUMBER_FLOAT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_FLOAT_MAX_VALUE)
private float floatFieldWithMinAndMaxValue;

@PodamFloatValue(numValue = PodamTestConstants.FLOAT_PRECISE_VALUE)
private Float floatObjectFieldWithPreciseValue;
```

#### `@PodamDoubleValue`

它允许自定义Double属性。例如：

```java
@PodamDoubleValue(minValue = PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE)
private double doubleFieldWithMinValueOnly;

@PodamDoubleValue(maxValue = PodamTestConstants.NUMBER_DOUBLE_ONE_HUNDRED)
private double doubleFieldWithMaxValueOnly;

@PodamDoubleValue(minValue = PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_DOUBLE_MAX_VALUE)
private double doubleFieldWithMinAndMaxValue;

@PodamDoubleValue(numValue = PodamTestConstants.DOUBLE_PRECISE_VALUE)
private Double doubleObjectFieldWithPreciseValue;
```

#### `@PodamStringValue`

它允许自定义String属性。例如：

```java
@PodamStringValue(length = PodamTestConstants.STR_ANNOTATION_TWENTY_LENGTH)
/** A String attribute with length 20 */
private String twentyLengthString;

@PodamStringValue(strValue = PodamTestConstants.STR_ANNOTATION_PRECISE_VALUE)
private String preciseValueString;
```

#### `@PodamCollection`

它允许自定义类似容器的数据结构，例如Collection、Map和Array。所有类容器数据结构的默认策略是`uk.co.jemos.podam.annotations.strategies.ObjectStrategy`，
其行为是返回一个新的 Object 实例。仅当容器元素类型为 Object 本身时才会使用此策略。否则优先选择元素类型。例如：

Collection的示例。它将用两个元素填充`List<Calendar>`。每个元素将根据`MyBirthdayStrategy`策略进行填充。

```java
@PodamCollection(nbrElements = 2, collectionElementStrategy = MyBirthdayStrategy.class)
private List<Calendar> myBirthdays=new ArrayList<Calendar>();
```

Map示例。它将用两个元素填充`Map<String, Calendar>`。每个Map元素将根据`MyBirthdayStrategy`策略进行填充。

```java
@PodamCollection(nbrElements = 2, mapElementStrategy = MyBirthdayStrategy.class)
private Map<String, Calendar> myBirthdaysMap=new HashMap<String, Calendar>();
```

Array的示例。它将填充`Calendar[]`。每个数组元素将根据`MyBirthdayStrategy`策略进行填充。

```java
@PodamCollection(nbrElements = 2, collectionElementStrategy = MyBirthdayStrategy.class)
private Calendar[]myBirthdaysArray;
```

#### `@PodamConstructor`

允许在无 setter 的 POJO 中标注构造函数。这是 PODAM 的要求：对于那些没有 setter（例如不可变类）的 POJO，需要使用此注解对构造函数进行标注。
`@PodamConstructor`可以用来标记接口。例如：

```java
/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import net.jcip.annotations.Immutable;
import uk.co.jemos.podam.annotations.PodamConstructor;

/**
 * This is an immutable POJO to test PODAM's ability to create an instance which
 * hasn't got setters
 *
 * @author mtedone
 *
 */
@Immutable
public class ImmutableNoHierarchicalAnnotatedPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** An int field */
    private final int intField;

    /** A Calendar field */
    private final Calendar dateCreated;

    /** An array of longs */
    private final long[] longArray;

    /**
     * Full constructor
     *
     * @param intField
     * @param dateCreated
     * @param longArray
     */
    @PodamConstructor
    public ImmutableNoHierarchicalAnnotatedPojo(int intField, Calendar dateCreated,
                                                long[] longArray) {
        super();
        this.intField = intField;
        this.dateCreated = dateCreated;
        this.longArray = longArray;
    }


    /**
     * @return the intField
     */
    public int getIntField() {
        return intField;
    }

    /**
     * @return the dateCreated
     */
    public Calendar getDateCreated() {
        return dateCreated;
    }

    /**
     * @return the longArray
     */
    public long[] getLongArray() {
        return longArray;
    }

}
```

#### `@PodamExclude`

它允许指定跳过某个属性的初始化。例如：

```java
public class Pojo {

    /** PODAM 不会填充这个属性 */
    @PodamExclude
    private SimplePojoToTestSetters somePojo;
}
```

此注释的替代方法是重新加载`ClassInfoStrategy.getExcludedFields()`。例例如：

```java
    private final static DefaultClassInfoStrategy classInfoStrategy=DefaultClassInfoStrategy.getInstance();
        classInfoStrategy.addExcludedField(Pojo.class,"somePojo");
```

### Podam 验证说明

Podam 支持 Bean Validation 注释约束，以使用满足这些约束的值来填充属性。 Bean Validation注解的完整语法请参考[数据校验](/guide/standard.html#数据校验)

#### 自定义约束

如果使用不属于javax.validation.constraints包的约束注释：

```java
public class ValidatedPojo {

    @Email
    private String email;

} 
```

然后你必须告诉Podam如何处理它们：

```java
Class<AttributeStrategy<?>>attrStrategy=(Class<AttributeStrategy<?>>)(Class<?>)EmailStrategy.class;
((RandomDataProviderStrategy)factory.getStrategy()).addAttributeStrategy(Email.class,attrStrategy);ValidatedPojo pojo
        =factory.manufacturePojo(ValidatedPojo.class); 
```

电子邮件策略实现：

```java
public class EmailStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue() {
        return "aaa.bbb@ccc.ddd";
    }

}
```

#### 限制

目前尚不支持`@Pattern`注解。

## Mock

