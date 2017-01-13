package com.tim.spring0200.common.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tim.spring0200.page.Pagination;
import com.tim.spring0200.page.SearchResult;

public class CommonDAO {
	
	private SessionFactory sessionFactory;
	
	private JdbcTemplate jdbcTemplate;
	
	public <T> void saveOrUpdateEntity(T entry){
		Session session = this.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(entry);
	}
	
	public <T> void update(T entry){
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(entry);
	}
	
	public <T> void delete(T entry){
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(entry);
	}
	
	public <T> void deleteAll(Collection<T> entities){
		Session session = this.getSessionFactory().getCurrentSession();
		for(Iterator<T> it = entities.iterator();it.hasNext();){
			session.delete(it.next());
		}
	}
	
	public <T> void saveOrUpdateAllEntity(Collection<T> entities){
		Session session = this.getSessionFactory().getCurrentSession();
		for(Iterator<T> it = entities.iterator();it.hasNext();){
			session.saveOrUpdate(it.next());
		}
	}
	
	public <T> List<T> loadAllEntities(Class<T> clazz){
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(clazz);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// 消除掉重复实体对象
		return criteria.list();
	}

	//单个属性查询对象list
	public <T> List<T> getEntitiesByField(Class<T> clazz,String field,Object value){
		return getEntitiesByField(clazz, field, value, null);
	}
	
	public <T> List<T> getEntitiesByField(Class<T> clazz,String field,Object value,HashMap<String, String> orders){
		//加限制的
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(clazz).add(Restrictions.eq(field, value));
		
		if(orders != null && !orders.isEmpty()){
			Iterator it = orders.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, String> entry = (Map.Entry) it.next();
				if(entry.getKey().toString().equalsIgnoreCase("ASC")){
					
					criteria.addOrder(Order.asc(entry.getValue()));
					System.out.println(entry.getKey()+"\t"+entry.getValue());
				}else if(entry.getKey().toString().equalsIgnoreCase("DESC")){
					criteria.addOrder(Order.desc(entry.getValue()));
				}
			}
		}
		return criteria.list();
	}
	
	//多个属性查询对象list
	public <T> List<T> getEntitiesByFields(Class<T> clazz,HashMap<String, Object> fields){
		return getEntitiesByFields(clazz, fields, null);
	}
	
	public <T> List<T> getEntitiesByFields(Class<T> clazz,HashMap<String, Object> fields,HashMap<String, String> orders){
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(clazz);
		if(fields != null && !fields.isEmpty()){
			Iterator it = fields.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry) it.next();
				criteria.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
			}
		}
		if(orders != null && !orders.isEmpty()){
			Iterator it = orders.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, String> entry = (Map.Entry) it.next();
				if(entry.getKey().toString().equalsIgnoreCase("ASC")){
					criteria.addOrder(Order.asc(entry.getValue()));
				}else if(entry.getKey().toString().equalsIgnoreCase("DESC")){
					criteria.addOrder(Order.desc(entry.getValue()));
				}
			}
		}
		return criteria.list();
	}
	
	//pagination data
	public <T> SearchResult<T> search(String sql,Pagination page){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List tempList = query.list();
		page.setTotalResults(tempList.size());
		int totalTemp = tempList.size() % page.getPageSize();
		int totalTemp2 = tempList.size() / page.getPageSize();
		page.setTotalPages(totalTemp==0?totalTemp2:(totalTemp2 + 1));
		query.setFirstResult(page.getCurrentPage() * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		SearchResult<T> searchResult = new SearchResult<T>();
		List<T> result= query.list();
		searchResult.setPagination(page);
		searchResult.setResults(result);
		return searchResult;
	}
	
	//pagination data
		public <T> SearchResult<T> search(String sql,Pagination page,Map<String,Object> map){
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery(sql);
			//query.setString("mycode", "0352-0");
			query.setProperties(map);
			List tempList = query.list();
			page.setTotalResults(tempList.size());
			int totalTemp = tempList.size() % page.getPageSize();
			int totalTemp2 = tempList.size() / page.getPageSize();
			page.setTotalPages(totalTemp==0?totalTemp2:(totalTemp2 + 1));
			query.setFirstResult(page.getCurrentPage() * page.getPageSize());
			query.setMaxResults(page.getPageSize());
			
			
			SearchResult<T> searchResult = new SearchResult<T>();
			List<T> result= query.list();
			searchResult.setPagination(page);
			searchResult.setResults(result);
			return searchResult;
		}
		
	
	//根据ID查询 单个 对象
	public <T> Object load(Class clazz,long id){
		return this.getSessionFactory().getCurrentSession().load(clazz, id);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
