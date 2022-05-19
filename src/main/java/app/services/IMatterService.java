package app.services;

import java.util.List;

import app.entities.Matter;

public interface IMatterService {

	public Matter findById(int idMatter);

	public List<Matter> getAll();

	public boolean insertOrUpdate(Matter matter);

	public boolean remove(int idMatter);

}
