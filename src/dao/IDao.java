package dao;

import java.util.List;

public interface IDao <T>{

	void save(T t);
	
	T getOne(int id);
	
	List<T> list();
	
	void update (T t);
	
	void datele (int id);
	
}
