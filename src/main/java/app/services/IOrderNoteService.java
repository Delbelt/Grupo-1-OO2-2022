package app.services;

import java.util.List;

import app.entities.OrderNote;

public interface IOrderNoteService {

	public OrderNote findById(int idOrderNote);

	public List<OrderNote> getAll();

	public boolean insertOrUpdate(OrderNote orderNote);

	public boolean remove(int idOrderNote);

}
