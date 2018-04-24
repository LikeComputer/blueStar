package com.blueprint.helper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 *
 * @another 江祖赟
 * @date 2018/4/17.
 */
public class ReflectHelper {


    /**
     * 获得超类的参数类型，取第一个参数类型
     *
     * @param <T>   类型参数
     * @param clazz 超类类型
     */
    @SuppressWarnings("rawtypes")
    public static <T> Class<T> getClassGenricType(final Class clazz) {
        return getClassGenricType(clazz, 0);
    }

    /**
     * 根据索引获得超类的参数类型
     *
     * @param clazz 超类类型
     * @param index 索引
     */
    @SuppressWarnings("rawtypes")
    public static Class getClassGenricType(final Class clazz, final int index) {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的 *直接超类的 Type(完整的类型包括泛型)*，然后将其转换ParameterizedType。
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。[0]就是这个数组中第一个了。简而言之就是获得超类的泛型参数的实际类型。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

}
