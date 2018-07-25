package domain.business;

public interface IRepository<T> {

	void save(T t);
	void delete (T t);
	void edit (T t);
	T findById (int id);
	
}
