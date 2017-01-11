package com.imopan.glm.vo;


/**
 * The Class GeometryBean.
 */
public class GeometryBean extends PersistentObject{

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 8598800272524445740L;

	/**
	 * GeoJSON支持下面几何类型：点、线、面、多点、多线、多面和几何集合
	 * Point, LineString ,Polygon
	 */
	private String type;
	
	/**
	 * 经纬度
	 */
	private String [] coordinates;

	/**
	 * Gets the geoJSON支持下面几何类型：点、线、面、多点、多线、多面和几何集合 Point, LineString ,Polygon.
	 *
	 * @return the geoJSON支持下面几何类型：点、线、面、多点、多线、多面和几何集合 Point, LineString ,Polygon
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the geoJSON支持下面几何类型：点、线、面、多点、多线、多面和几何集合 Point, LineString ,Polygon.
	 *
	 * @param type the new geoJSON支持下面几何类型：点、线、面、多点、多线、多面和几何集合 Point, LineString ,Polygon
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the 经纬度.
	 *
	 * @return the 经纬度
	 */
	public String[] getCoordinates() {
		return coordinates;
	}

	/**
	 * Sets the 经纬度.
	 *
	 * @param coordinates the new 经纬度
	 */
	public void setCoordinates(String[] coordinates) {
		this.coordinates = coordinates;
	}
}
