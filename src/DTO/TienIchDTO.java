package DTO;

public class TienIchDTO {
    private String maTI;
    private String tenTI;
    private int xuLy;

    public TienIchDTO() {
    }

    public TienIchDTO(String maTI, String tenTI, int xuLy) {
        this.maTI = maTI;
        this.tenTI = tenTI;
        this.xuLy = xuLy;
    }

    public String getMaTI() {
        return maTI;
    }

    public void setMaTI(String maTI) {
        this.maTI = maTI;
    }

    public String getTenTI() {
        return tenTI;
    }

    public void setTenTI(String tenTI) {
        this.tenTI = tenTI;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}
