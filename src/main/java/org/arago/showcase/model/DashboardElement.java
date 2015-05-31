package org.arago.showcase.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class DashboardElement {
	@NotEmpty
	private String id;

	@NotEmpty
	private String name;

	@NumberFormat(style=Style.PERCENT)
	private BigDecimal percent;
	
	private BigDecimal nrValidElems;
	
	private BigDecimal nrDashboardTotalElems;
	
	public DashboardElement(String id, String name, long totalElems) {
		this.id = id;
		this.name = name;
		this.percent = BigDecimal.valueOf(0.0);
		this.nrValidElems = BigDecimal.valueOf(0);
		this.nrDashboardTotalElems = BigDecimal.valueOf(totalElems);
	}
	
	public void incNrValidElems() {
		nrValidElems = nrValidElems.add(BigDecimal.valueOf(1));
	}
	
	public void incNrDashboardTotalElems() {
		nrDashboardTotalElems = nrDashboardTotalElems.add(BigDecimal.valueOf(1));
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public BigDecimal getNrValidElems() {
		return nrValidElems;
	}

	public void setNrValidElems(BigDecimal nrValidElems) {
		this.nrValidElems = nrValidElems;
	}
	
	public BigDecimal getNrDashboardTotalElems() {
		return nrDashboardTotalElems;
	}

	public void setNrDashboardTotalElems(BigDecimal nrDashboardTotalElems) {
		this.nrDashboardTotalElems = nrDashboardTotalElems;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((nrDashboardTotalElems == null) ? 0 : nrDashboardTotalElems
						.hashCode());
		result = prime * result
				+ ((nrValidElems == null) ? 0 : nrValidElems.hashCode());
		result = prime * result + ((percent == null) ? 0 : percent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DashboardElement other = (DashboardElement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nrDashboardTotalElems == null) {
			if (other.nrDashboardTotalElems != null)
				return false;
		} else if (!nrDashboardTotalElems.equals(other.nrDashboardTotalElems))
			return false;
		if (nrValidElems == null) {
			if (other.nrValidElems != null)
				return false;
		} else if (!nrValidElems.equals(other.nrValidElems))
			return false;
		if (percent == null) {
			if (other.percent != null)
				return false;
		} else if (!percent.equals(other.percent))
			return false;
		return true;
	}
}
