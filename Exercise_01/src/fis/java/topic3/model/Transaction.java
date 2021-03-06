package fis.java.topic3.model;

import java.util.Date;

public class Transaction {
	private String soLenhGiaoDich;
	private Date thoiGianGiaoDich;
	private String taiKhoanNguon;
	private String taiKhoanNguoiHuong;
	private String tenNguoiHuong;
	private String tenNganHangHuong;
	private Double soTien;
	private String loaiPhi;
	private Double soTienPhi;
	private String noiDungChuyenTien;
	
	public Transaction(String soLenhGiaoDich, Date thoiGianGiaoDich, String taiKhoanNguon, String taiKhoanNguoiHuong,
			String tenNguoiHuong, String tenNganHangHuong, Double soTien, String loaiPhi, Double soTienPhi,
			String noiDungChuyenTien) {
		this.soLenhGiaoDich = soLenhGiaoDich;
		this.thoiGianGiaoDich = thoiGianGiaoDich;
		this.taiKhoanNguon = taiKhoanNguon;
		this.taiKhoanNguoiHuong = taiKhoanNguoiHuong;
		this.tenNguoiHuong = tenNguoiHuong;
		this.tenNganHangHuong = tenNganHangHuong;
		this.soTien = soTien;
		this.loaiPhi = loaiPhi;
		this.soTienPhi = soTienPhi;
		this.noiDungChuyenTien = noiDungChuyenTien;
	}

	public Transaction() {
	}


	public String getSoLenhGiaoDich() {
		return soLenhGiaoDich;
	}

	public void setSoLenhGiaoDich(String soLenhGiaoDich) {
		this.soLenhGiaoDich = soLenhGiaoDich;
	}

	public Date getThoiGianGiaoDich() {
		return thoiGianGiaoDich;
	}

	public void setThoiGianGiaoDich(Date thoiGianGiaoDich) {
		this.thoiGianGiaoDich = thoiGianGiaoDich;
	}

	public String getTaiKhoanNguon() {
		return taiKhoanNguon;
	}

	public void setTaiKhoanNguon(String taiKhoanNguon) {
		this.taiKhoanNguon = taiKhoanNguon;
	}

	public String getTaiKhoanNguoiHuong() {
		return taiKhoanNguoiHuong;
	}

	public void setTaiKhoanNguoiHuong(String taiKhoanNguoiHuong) {
		this.taiKhoanNguoiHuong = taiKhoanNguoiHuong;
	}

	public String getTenNguoiHuong() {
		return tenNguoiHuong;
	}

	public void setTenNguoiHuong(String tenNguoiHuong) {
		this.tenNguoiHuong = tenNguoiHuong;
	}

	public String getTenNganHangHuong() {
		return tenNganHangHuong;
	}

	public void setTenNganHangHuong(String tenNganHangHuong) {
		this.tenNganHangHuong = tenNganHangHuong;
	}

	public Double getSoTien() {
		return soTien;
	}

	public void setSoTien(Double soTien) {
		this.soTien = soTien;
	}

	public String getLoaiPhi() {
		return loaiPhi;
	}

	public void setLoaiPhi(String loaiPhi) {
		this.loaiPhi = loaiPhi;
	}

	public Double getSoTienPhi() {
		return soTienPhi;
	}

	public void setSoTienPhi(Double soTienPhi) {
		this.soTienPhi = soTienPhi;
	}

	public String getNoiDungChuyenTien() {
		return noiDungChuyenTien;
	}

	public void setNoiDungChuyenTien(String noiDungChuyenTien) {
		this.noiDungChuyenTien = noiDungChuyenTien;
	}

	@Override
	public String toString() {
		return "Transaction [soLenhGiaoDich=" + soLenhGiaoDich + ", thoiGianGiaoDich=" + thoiGianGiaoDich
				+ ", taiKhoanNguon=" + taiKhoanNguon + ", taiKhoanNguoiHuong=" + taiKhoanNguoiHuong + ", tenNguoiHuong="
				+ tenNguoiHuong + ", tenNganHangHuong=" + tenNganHangHuong + ", soTien=" + soTien + ", loaiPhi="
				+ loaiPhi + ", soTienPhi=" + soTienPhi + ", noiDungChuyenTien=" + noiDungChuyenTien + "]";
	}
	
	
	
}
