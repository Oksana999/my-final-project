package com.example.myfinalproject.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Oksana
 */
@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class Transaction extends AbstractEntity {

   private BigDecimal amount;

   private long date;

   private boolean isWithdraw;

   @ManyToOne(fetch = FetchType.LAZY)
   @NotNull(message = "Account is required")
   private Account account;

}
