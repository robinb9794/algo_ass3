package redblack;

public class DataItem{
    private String key;
    private String data;

    public DataItem(String key){
        this.key = key;
    }

    public DataItem(String key, String data){
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}