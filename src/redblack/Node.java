package redblack;

public abstract class Node {
	protected DataItem dataItem;

	public Node(String key) {
        this.dataItem = new DataItem(key);
	}

	public Node(String key, String data){
		this.dataItem = new DataItem(key, data);
	}
	
	public String getKey(){
		return this.dataItem.getKey();
	}

	public void setKey(String key){
		this.dataItem.setKey(key);
	}

	public String getData(){
		return this.dataItem.getData();
	}

	public void setData(String data){
		this.dataItem.setData(data);
	}
}
