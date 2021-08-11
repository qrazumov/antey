package ru.qrazumov.antey.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(	name = "invoices",
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "name"),
		})
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String name;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime createdAt;

	@NotNull
	private Long balance;

	@Enumerated(EnumType.ORDINAL)
	private ECurrency currency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public ECurrency getCurrency() {
		return currency;
	}

	public void setCurrency(ECurrency currency) {
		this.currency = currency;
	}

	public String getCreatedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss a");
		return createdAt.format(formatter);
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne()
	private User user;
	@JsonBackReference
	public User getUsers() {
		return user;
	}

	public void setUsers(User user) {
		this.user = user;
	}

	public Invoice() {

	}

	public Invoice(String name, ECurrency currency, Long balance, User user) {
		this.name = name;
		this.currency = currency;
		this.balance = balance;
		this.user = user;
		this.setCreatedAt(LocalDateTime.now());
	}

}
