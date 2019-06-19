package com.ssm_Oracle.dao;

import java.util.List;

public interface Dao {

	/**
	 * 新增
	 * @param paramE
	 * @return
	 */
	public abstract <E> int insert(E paramE);
	/**
	 * 自定义新增
	 * @param paramString
	 * @param paramObj
	 * @return
	 */
	public abstract <E> int insert(String paramString, Object paramObj);
	/**
	 * 修改
	 * @param paramE
	 * @return
	 */
	public abstract <E> int update(E paramE);
	/**
	 * 自定义修改
	 * @param paramString
	 * @param paramObj
	 * @return
	 */
	public abstract <E> int update(String paramString, Object paramObj);
	/**
	 * 删除
	 * @param paramE
	 * @return
	 */
	public abstract <E> int delete(E paramE);
	/**
	 * 自定义删除
	 * @param paramString
	 * @param paramObj
	 * @return
	 */
	public abstract <E> int delete(String paramString, Object paramObj);
	/**
	 * 主键查询
	 * @param paramE
	 * @return
	 */
	@Deprecated
	public abstract <E> E  selectByPrimary(E paramE);
	/**
	 * 主键查询并确定是否锁表
	 * @param paramE
	 * @param paramboolean
	 * @return
	 */
	public abstract <E> E  selectByPrimary(E paramE, boolean paramboolean);
	/**
	 * 分页查询
	 * @param paramString
	 * @param paramE
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	@Deprecated
	public abstract <E> List<E>  selectList(String paramString, E paramE, int paramInt1, int paramInt2);
	/**
	 * 分页查询，并确定是否锁表
	 * @param paramString
	 * @param paramE
	 * @param paramInt1
	 * @param paramInt2
	 * @param paramboolean
	 * @return
	 */
	public abstract <E> List<E>  selectList(String paramString, E paramE, int paramInt1, int paramInt2, boolean paramboolean);
	/**
	 * 查询列表
	 * @param paramString
	 * @param paramE
	 * @return
	 */
	@Deprecated
	public abstract <E> List<E>  selectList(String paramString, E paramE);
	/**
	 * 查询列表并确定是否锁表
	 * @param paramString
	 * @param paramE
	 * @param paramboolean
	 * @return
	 */
	public abstract <E> List<E>  selectList(String paramString, E paramE, boolean paramboolean);
	/**
	 * 自定义查询
	 * @param paramString
	 * @param paramE
	 * @return
	 */
	@Deprecated
	public abstract <E> E  selectOne(String paramString, E paramE);
	/**
	 * 自定义查询并确定是否锁表
	 * @param paramString
	 * @param paramE
	 * @param paramboolean
	 * @return
	 */
	public abstract <E> E  selectOne(String paramString, E paramE, boolean paramboolean);
	
}
