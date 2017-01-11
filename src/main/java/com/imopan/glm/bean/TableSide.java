package com.imopan.glm.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Sort;

/**
 * <p>DataTables服务器端</p>
 * @author xlp
 *
 */
public class TableSide {
	
	private Integer draw;
	
	private Integer start;
	
	private Integer length;
	
	private String searchValue;
	
	private boolean searchRegex;
	
	private Map<String,Map<String,Object>> columnsByName = new HashMap<>();
	
	private Map<Integer,Map<String,Object>> columnsByIndex = new HashMap<>();
	
	private List<String> orders = new ArrayList<>();
	
	public TableSide(HttpServletRequest request){
		this.draw = Integer.valueOf(request.getParameter("draw"));
		this.start = Integer.valueOf(request.getParameter("start"));
		this.length = Integer.valueOf(request.getParameter("length"));
		this.searchValue = request.getParameter("search[value]");
		this.searchRegex = Boolean.valueOf(request.getParameter("search[regex]"));
		int j = 0;
		while(true){
			String data = request.getParameter("columns["+j+"][data]");
			String name = request.getParameter("columns["+j+"][name]");
			String searchable = request.getParameter("columns["+j+"][searchable]");
			String orderable = request.getParameter("columns["+j+"][orderable]");
			String searchValue = request.getParameter("columns["+j+"][search][value]");
			String searchRegex = request.getParameter("columns["+j+"][search][regex]");
			//searchable与orderable是必定存在的参数,如果为null,表示没有后续请求参数了
			if(searchable==null||orderable==null){
				break;
			}
			Map<String,Object> map = new HashMap<>();
			map.put("columns[j][data]", data);
			map.put("columns[j][name]", name);
			map.put("columns[j][searchable]", searchable);
			map.put("columns[j][orderable]", orderable);
			map.put("columns[j][search][value]", searchValue);
			map.put("columns[j][search][regex]", searchRegex);
			columnsByIndex.put(j, map);
			//如果列的名字不存在，则不存储以列名存储的map集合
			if(StringUtils.isNotBlank(name)){
				columnsByName.put(name, map);
			}
			j++;
		}
		int i = 0;
		while(true){
			String orderColumn = request.getParameter("order["+i+"][column]");
			String orderDir = request.getParameter("order["+i+"][dir]");
			
			if(StringUtils.isBlank(orderColumn)){
				break;
			}
			orders.add(getColumnName(Integer.valueOf(orderColumn))+" "+orderDir);
			i++;
		}
	}

	

	public Integer getDraw() {
		return draw;
	}



	public Integer getStart() {
		return start;
	}



	public Integer getLength() {
		return length;
	}



	public String getSearchValue() {
		return searchValue;
	}



	public boolean isSearchRegex() {
		return searchRegex;
	}



	public Map<String, Map<String, Object>> getColumnsByName() {
		return columnsByName;
	}



	public Map<Integer, Map<String, Object>> getColumnsByIndex() {
		return columnsByIndex;
	}



	public List<String> getOrders() {
		return orders;
	}
	
	public List<Sort> getSorts(){
		List<Sort> sorts = new ArrayList<>();
		for(String order : orders){
			String sortField = order.split(" ")[0];
			if(order.endsWith("desc")){
				sorts.add(Sort.descending(sortField));
			}else if(order.endsWith("asc")){
				sorts.add(Sort.ascending(sortField));
			}
		}
		return sorts;
	}



	public String getColumnData(Integer index){
		return String.valueOf(columnsByIndex.get(index).get("columns[j][data]"));
	}
	
	public String getColumnData(String name){
		return String.valueOf(columnsByName.get(name).get("columns[j][data]"));
	}
	
	public String getColumnName(Integer index){
		return String.valueOf(columnsByIndex.get(index).get("columns[j][name]"));
	}
	
	public String getColumnName(String name){
		return String.valueOf(columnsByName.get(name).get("columns[j][name]"));
	}
	
	public boolean getColumnSearchable(Integer index){
		return Boolean.valueOf(String.valueOf(columnsByIndex.get(index).get("columns[j][searchable]")));
	}
	
	public boolean getColumnSearchable(String name){
		return Boolean.valueOf(String.valueOf(columnsByName.get(name).get("columns[j][searchable]")));
	}
	
	public boolean getColumnOrderable(Integer index){
		return Boolean.valueOf(String.valueOf(columnsByIndex.get(index).get("columns[j][orderable]")));
	}
	
	public boolean getColumnOrderable(String name){
		return Boolean.valueOf(String.valueOf(columnsByName.get(name).get("columns[j][orderable]")));
	}
	
	public String getColumnSearchValue(Integer index){
		return String.valueOf(columnsByIndex.get(index).get("columns[j][search][value]"));
	}
	
	public String getColumnSearchValue(String name){
		return String.valueOf(columnsByName.get(name).get("columns[j][search][value]"));
	}
	
	public boolean getColumnSearchRegex(Integer index){
		return Boolean.valueOf(String.valueOf(columnsByIndex.get(index).get("columns[j][search][regex]")));
	}
	
	public boolean getColumnSearchRegex(String name){
		return Boolean.valueOf(String.valueOf(columnsByName.get(name).get("columns[j][search][regex]")));
	}

	@Override
	public String toString() {
		return "ServerSide [draw=" + draw + ", start=" + start + ", length=" + length + ", searchValue=" + searchValue
				+ ", searchRegex=" + searchRegex + ", columnsByName=" + columnsByName + ", columnsByIndex="
				+ columnsByIndex + ", orders=" + Arrays.toString(orders.toArray()) + "]";
	}
	
	
}
