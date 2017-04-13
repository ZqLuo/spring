package com.example;

import static com.google.common.collect.Iterables.toArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class CustomerSpecs {

	public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) { //1

		final Class<T> type = (Class<T>) example.getClass();//2 获取当前实体类对象类型

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>(); //3 新建Predicate列表存储构造的查询条件
				
				EntityType<T> entity = entityManager.getMetamodel().entity(type);//4 获取实体类属性
				
				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {//5 对所有属性做循环
					Object attrValue = getValue(example, attr); //6 获取属性值
					if (attrValue != null) {
						if (attr.getJavaType() == String.class) { //7 字符串类型
							if (!StringUtils.isEmpty(attrValue)) { //8 非空
								predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),
										pattern((String) attrValue))); //9 字符串默认使用like查询
							}
						} else {
							predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
									attrValue)); //10 其余条件=
						}
					}

				}
				return predicates.isEmpty() ? cb.conjunction() : cb.and(toArray(predicates, Predicate.class));//11 列表转化成Predicate
			}

			/**
			 * 12 获取单独一个属性值
			 */
			private <T> Object getValue(T example, Attribute<T, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}
			
			/**
			 * 13 获取属性
			 */
			private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
					Class<E> fieldClass) { 
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}

		};

	}
	
	/**
	 * 14 构造like查询
	 */
	static private String pattern(String str) {
		return "%" + str + "%";
	}

}
