package cn.zzjh.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 查询结果封装
 * @author 王海文
 *
 * @param <T>
 */
public class PageResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Integer pageIndex; // 起始角标
	protected Integer pageSize; // 每页显示条数
	protected Integer totalCount; // 总条数
	protected List<Map<String,Object>> dataList; // 所有数据

	public PageResult() {

	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
}
