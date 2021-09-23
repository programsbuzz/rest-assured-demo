package finalSD;

import java.util.List;

public class firstPojo {
private String meta;
private List<dataPojo> data;

public String getMeta() {
	return meta;
}
public void setMeta(String meta) {
	this.meta = meta;
}
public List<dataPojo> getData() {
	return data;
}
public void setData(List<dataPojo> data) {
	this.data = data;
}
@Override
public String toString() {
	return "firstPojo [meta=" + meta + ", data=" + data + "]";
}
}
