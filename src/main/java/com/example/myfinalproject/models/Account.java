package com.example.myfinalproject.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Account extends AbstractEntity{

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;

   @NotNull
   private BigDecimal amount;

   @OneToMany(mappedBy = "account",  cascade = CascadeType.ALL,
           orphanRemoval = true)
   private List<Transaction> transactions = new ArrayList<>();

}
