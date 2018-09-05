package domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import domain.business.IPaymentRepository;
import domain.model.Payment;

public class PaymentRepository implements IPaymentRepository{

	private EntityManager manager;
	private EntityTransaction transaction;
	
	public PaymentRepository(EntityManager manager) {
		this.manager = manager; 
		transaction = manager.getTransaction();
	}

	@Override
	public void add(Payment payment) {		
		transaction.begin();
		manager.persist(payment);
		transaction.commit();
	}

	@Override
	public void delete(Payment payment) {
		transaction.begin();
		manager.remove(payment);
		transaction.commit();		
	}

	@Override
	public void edit(Payment payment) {
		transaction.begin();
		manager.merge(payment);
		transaction.commit();
	}

	@Override
	public Payment findById(int id) {
		transaction.begin();
		Payment payment = manager.find(Payment.class, id);
		transaction.commit();
		
		return payment;
	}
}