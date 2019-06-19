package com.ssm_Oracle.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.stereotype.Repository;

import com.ssm_Oracle.entity.BaseEntity;

@Repository
public class DaoImpl implements Dao {

	protected static final String SQL_INSERT = "insert";
	protected static final String SQL_update = "updateByPrimary";
	protected static final String SQL_SELECT_BY_PRIMARY = "selectByPrimary";
	protected static final String SQL_SELECT = "select";
	protected static final int DEFAULT_COUNT_LIMIT = 2000;
	private static final Logger logger = LoggerFactory.getLogger(DaoImpl.class);
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public <E> int insert(E paramE) {
		// TODO Auto-generated method stub
		if(!(paramE instanceof BaseEntity)){
			logger.warn("传入的实例类型{}不是BaseEntity的子类",paramE.getClass().getName());
		}
		
		String sqlId = paramE.getClass().getSimpleName() + "." + "insert";
		
		return this.sqlSessionTemplate.insert(sqlId,paramE);
	}

	@Override
	public <E> int insert(String paramString, Object paramObj) {
		// TODO Auto-generated method stub
		
		return this.sqlSessionTemplate.insert(paramString,paramObj);
	}

	@Override
	public <E> int update(E paramE) {
		// TODO Auto-generated method stub
		if(!(paramE instanceof BaseEntity)){
			logger.warn("传入的实例类型{}不是BaseEntity的子类",paramE.getClass().getName());
		}
		String sqlId = paramE.getClass().getSimpleName() + "." + "updateByPrimary";
		
		return this.sqlSessionTemplate.update(sqlId,paramE);
	}

	@Override
	public <E> int update(String paramString, Object paramObj) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.update(paramString,paramObj);
	}

	@Override
	public <E> int delete(E paramE) {
		// TODO Auto-generated method stub
		if(!(paramE instanceof BaseEntity)){
			logger.warn("传入的实例类型{}不是BaseEntity的子类",paramE.getClass().getName());
		}
		String sqlId = paramE.getClass().getSimpleName() + "." + "deleteByPrimary";
		
		return this.sqlSessionTemplate.delete(sqlId,paramE);
	}

	@Override
	public <E> int delete(String paramString, Object paramObj) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.delete(paramString,paramObj);
	}

	@Override
	@Deprecated
	public <E> E selectByPrimary(E paramE) {
		// TODO Auto-generated method stub
		if(!(paramE instanceof BaseEntity)){
			logger.warn("传入的实例类型{}不是BaseEntity的子类",paramE.getClass().getName());
		}
		String sqlId = paramE.getClass().getSimpleName() + "." + "selectByPrimary";
		
		paramE = addForUpdate(paramE, false);
		 
		return this.sqlSessionTemplate.selectOne(sqlId,paramE);
	}

	@Override
	public <E> E selectByPrimary(E paramE, boolean paramboolean) {
		// TODO Auto-generated method stub
		if(!(paramE instanceof BaseEntity)){
			logger.warn("传入的实例类型{}不是BaseEntity的子类",paramE.getClass().getName());
		}
		String sqlId = paramE.getClass().getSimpleName() + "." + "selectByPrimary";
		paramE = addForUpdate(paramE, paramboolean);
		
		return this.sqlSessionTemplate.selectOne(sqlId,paramE);
	}

	@Override
	@Deprecated
	public <E> List<E> selectList(String paramString, E paramE, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		RowBounds rb = new RowBounds(paramInt1, paramInt2);
		paramE = addForUpdate(paramE, false);
		return this.sqlSessionTemplate.selectList(paramString,paramE,rb);
	}

	@Override
	public <E> List<E> selectList(String paramString, E paramE, int paramInt1, int paramInt2, boolean paramboolean) {
		// TODO Auto-generated method stub
		RowBounds rb = new RowBounds(paramInt1, paramInt2);
		paramE = addForUpdate(paramE, paramboolean);
		return this.sqlSessionTemplate.selectList(paramString,paramE,rb);
	}

	@Override
	@Deprecated
	public <E> List<E> selectList(String paramString, E paramE) {
		// TODO Auto-generated method stub
		RowBounds rb = new RowBounds(0, 2000);
		paramE = addForUpdate(paramE, false);
		return this.sqlSessionTemplate.selectList(paramString,paramE,rb);
	}

	@Override
	public <E> List<E> selectList(String paramString, E paramE, boolean paramboolean) {
		// TODO Auto-generated method stub
		RowBounds rb = new RowBounds(0, 2000);
		paramE = addForUpdate(paramE, paramboolean);
		return this.sqlSessionTemplate.selectList(paramString,paramE,rb);
	}

	@Override
	@Deprecated
	public <E> E selectOne(String paramString, E paramE) {
		// TODO Auto-generated method stub
		
		paramE = addForUpdate(paramE, false);		
		return this.sqlSessionTemplate.selectOne(paramString,paramE);
	}

	@Override
	public <E> E selectOne(String paramString, E paramE, boolean paramboolean) {
		// TODO Auto-generated method stub
		
		paramE = addForUpdate(paramE, paramboolean);
		return this.sqlSessionTemplate.selectOne(paramString,paramE);
	}
	
	
	private <E> E addForUpdate(E entity, boolean isForUpdate){
		
		if(entity == null){
			entity = (E) new HashMap();
		}
		
		if((entity instanceof BaseEntity)){
			((BaseEntity) entity).setForUpdate(isForUpdate);
		}else if((entity instanceof Map)){
			((Map) entity).put("forUpdate", isForUpdate);
		}else{
			BeanGenerator beanGenerator = new BeanGenerator();
			beanGenerator.setSuperclass(entity.getClass());
			beanGenerator.addProperty("forUpdate", Boolean.TYPE);
			Object element = beanGenerator.create();
			
			BeanCopier beanCopier = BeanCopier.create(entity.getClass(), element.getClass(), false);
			beanCopier.copy(entity, element, null);
			try{
				element.getClass().getDeclaredMethod("setForUpdate", new Class[]{Boolean.TYPE}).invoke(element, new Object[] {Boolean.valueOf(isForUpdate)});

			}catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
			entity = (E) element;
			
		}
		return entity;
	}

}
