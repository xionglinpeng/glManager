package com.imopan.glm.bean;

public class TableResult {

	private Integer draw;
	
	private Long recordsTotal;
	
	private Long recordsFiltered;
	
	private Object[] dataObject;
	
	private Object[][] dataArray;
	
	private String error;
	
	
	public TableResult(Integer draw, String error) {
		super();
		this.draw = draw;
		this.error = error;
	}

	public TableResult(Integer draw, Integer recordsTotal, Integer recordsFiltered,
			Object[] dataObject) {
		this.draw = draw;
		this.recordsTotal = recordsTotal.longValue();
		this.recordsFiltered = recordsFiltered.longValue();
		this.dataObject = dataObject;
	}
	
	public TableResult(Integer draw, Long recordsTotal, Long recordsFiltered,
			Object[] dataObject) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.dataObject = dataObject;
	}
	
	public TableResult(Integer draw, Integer recordsTotal, Integer recordsFiltered,
			Object[] dataObject,String error) {
		this.draw = draw;
		this.recordsTotal = recordsTotal.longValue();
		this.recordsFiltered = recordsFiltered.longValue();
		this.dataObject = dataObject;
		this.error = error;
	}
	
	public TableResult(Integer draw, Long recordsTotal, Long recordsFiltered,
			Object[] dataObject,String error) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.dataObject = dataObject;
		this.error = error;
	}
	
	public TableResult(Integer draw, Integer recordsTotal, Integer recordsFiltered, Object[][] dataArray) {
		this.draw = draw;
		this.recordsTotal = recordsTotal.longValue();
		this.recordsFiltered = recordsFiltered.longValue();
		this.dataArray = dataArray;
	}
	
	public TableResult(Integer draw, Long recordsTotal, Long recordsFiltered, Object[][] dataArray) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.dataArray = dataArray;
	}
	
	public TableResult(Integer draw, Integer recordsTotal, Integer recordsFiltered, Object[][] dataArray,
			String error) {
		this.draw = draw;
		this.recordsTotal = recordsTotal.longValue();
		this.recordsFiltered = recordsFiltered.longValue();
		this.dataArray = dataArray;
		this.error = error;
	}
	public TableResult(Integer draw, Long recordsTotal, Long recordsFiltered, Object[][] dataArray,
			String error) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.dataArray = dataArray;
		this.error = error;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object[] getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object[] dataObject) {
		this.dataObject = dataObject;
	}

	public Object[][] getDataArray() {
		return dataArray;
	}

	public void setDataArray(Object[][] dataArray) {
		this.dataArray = dataArray;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
