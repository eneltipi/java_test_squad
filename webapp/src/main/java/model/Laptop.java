package model;

public class Laptop {
	private String name;
	private String cpu;	
	private String ram;
	private String storage;
	private String gpu;
	private String monitor;
	private String alias;
	private String brand;
	private int quanity;
	private float price;
	private String photo;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getGpu() {
		return gpu;
	}
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public Laptop(String name, String cpu, String ram, String storage, String gpu, String monitor, String alias,
			float price, String brand, int quanity, String photo) {
		
		this.name = name;
		this.cpu = cpu;
		this.ram = ram;
		this.storage = storage;
		this.gpu = gpu;
		this.monitor = monitor;
		this.alias = alias;
		this.price = price;
		this.brand = brand;
		this.quanity = quanity;
		this.photo = photo;
	}
	

	
	

}
