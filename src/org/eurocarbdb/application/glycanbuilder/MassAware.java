package org.eurocarbdb.application.glycanbuilder;

public interface MassAware{
	public double computeMass();
	public double computeMass(String type);
	public boolean equals(MassAware aware);
}
