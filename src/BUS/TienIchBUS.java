package BUS;

import DAO.Database;
import DTO.TienIchDTO;

import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TienIchBUS {
    private final Database db;

    public TienIchBUS(){
        db = new Database();
    }

    public List<TienIchDTO> getAllTienIch() {
        String query = "select * from TienIch where xuLy = 0";
        return db.getListTI_DTO(query);
    }

    public int getTienIchCount() {
        String query = "select count(*) from TienIch";
        return db.executeNonQueryGetInteger(query);
    }

    public void addTienIch(String maTI, String tenTI){
        String query = "insert into TienIch values(?,?,?)";
        Object[] param = {maTI,tenTI,0};
        db.executeNonQuery(query,param);
    }
    
    public List<TienIchDTO> findTienIch(String value) {
        String query = "select * from TienIch where (maTI like ? or tenTI like ?) and xuLy = 0";
        Object[] param = {value,value};
        return db.getListTI_DTO(query,param);
    }


    public void deleteTienIch(String maTI) {
        String query = "update TienIch set xuLy = 1 where maTI = ?";
        Object[] param = {maTI};
        db.executeNonQuery(query,param);
    }
    public void ThemTienIchPhong(String maPhong,String maTI,String maSoLg) {
        String query = "INSERT INTO CHITIETTIENICH VALUES (?, ?, ?)";
        Object[] param = {maPhong,maTI,maSoLg};
        db.executeNonQuery(query,param);
    }
    public void SuaTienIchPhong(String maPhong,String maTI,String maSoLg) {
        String query = "update CHITIETTIENICH set SoLuong = ? where MaTI = ? and MaP = ?";
        Object[] param = {maSoLg,maTI,maPhong};
        db.executeNonQuery(query,param);
    }
    public void XoaTienIchPhong(String maPhong,String maTI) {
        String query = "delete CHITIETTIENICH where MaP = ? and MaTI = ?";
        Object[] param = {maPhong,maTI};
        db.executeNonQuery(query,param);
    }

    public void updateTienIch(String maTI, String tenTI){
        String query = "update TienIch set tenTI = ? where maTI = ? and xuLy = 0";
        Object[] param = {tenTI,maTI};
        db.executeNonQuery(query,param);
    }
    public ResultSet getListTienIchPhong(String maPhong) {
        String query = "select TIENICH.MaTI, TenTI, SoLuong from CHITIETTIENICH, TIENICH where TIENICH.MaTI = CHITIETTIENICH.MaTI and MaP = '"+maPhong+"' and TIENICH.XuLy = 0";
        Object[] param = {maPhong};
        return db.getList(query);
    }
}

