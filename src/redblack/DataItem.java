package redblack;

public class DataItem{
    private String key;
    private String data;
    private int bitPosition;

    public DataItem(String key){
        this.key = key;
    }

    public DataItem(String key, String data){
        this.key = key;
        this.data = data;
    }

    public DataItem(String key, int bitPosition){
        this.key = key;
        this.bitPosition = bitPosition;
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

    public int getBitPosition(){
        return this.bitPosition;
    }

    public void setBitPosition(int bitPosition){
        this.bitPosition = bitPosition;
    }
}