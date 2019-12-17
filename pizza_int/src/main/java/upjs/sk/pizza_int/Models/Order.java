package upjs.sk.pizza_int.Models;

import java.sql.Date;

public class Order {

	private Long id;
	private Date date;
	private String adress;
	private Long idPizza;
	private Long idUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Long getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(Long idPizza) {
		this.idPizza = idPizza;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", adress=" + adress + ", idPizza=" + idPizza + ", idUser="
				+ idUser + "]";
	}
	
	

}
