package workers;

public class ByteManager{

    public byte[] getKeyBytes(String key){
        return key.getBytes();
    }

    public int getKeyIndex(byte[] bytes){
        return bytes.length - 1;
    }

    public int getKeyIndex(byte[] bytes, int i){
        return (i >= 8 && bytes.length > 1) ? (bytes.length-1-(i%8)) : bytes.length-1;
    }

    public int getShiftedBit(byte[] keyBytes, int index, int i){
        return ((keyBytes[index] >> i) & 1);
    }

    public String getBinaryCode(byte[] bytes){
        String binary = "";
        for(int i = 0; i < bytes.length; i++){
            binary += String.format("%8s", Integer.toBinaryString(bytes[i])).replace(" ", "0");
        }
        return binary;
    }
}