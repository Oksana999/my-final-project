package com.example.myfinalproject.controllers;

import com.example.myfinalproject.data.AccountRepository;
import com.example.myfinalproject.data.HotelRepository;
import com.example.myfinalproject.data.UserRepository;
import com.example.myfinalproject.models.Account;
import com.example.myfinalproject.models.Hotel;
import com.example.myfinalproject.models.Transaction;
import com.example.myfinalproject.models.User;
import com.example.myfinalproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author Oksana
 */
@Controller
@RequestMapping("accounts")
public class AccountController extends ParentController {

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private AccountRepository accountRepository;
   @Autowired
   private HotelRepository hotelRepository;
   @Autowired
   private TransactionService transactionService;

   @GetMapping
   public String getUserAccount(@RequestParam(name = "message", required = false) String message,
                                HttpServletRequest request, Model model,
                                @ModelAttribute("message1") String message1) {
      User sessionUser = (User) request.getSession().getAttribute("user");

      if (sessionUser == null) {
         model.addAttribute("title", "No user found");
      } else {
         Optional<User> optionalUser = userRepository.findById(sessionUser.getId());
         if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            Account account = accountRepository.findByUser(user);
            List<Transaction> transactions = transactionService.getByAccount(account.getId());
            model.addAttribute("transactions", transactions);

            model.addAttribute("user", sessionUser);
            model.addAttribute("account", account);

            model.addAttribute("title", "Personal Service");
            // request.getParameter("message");

            model.addAttribute("message", message1);
         }
      }
      return "users/profile";
   }

   @PostMapping("replenishment")
   public String replenishment(@RequestParam Integer accountId,
                               @RequestParam BigDecimal amount) {
      transactionService.saveTransaction(amount, false, accountId);

      return "redirect:/accounts";
   }

   @PostMapping("withdrawMoney/{hotelId}")
   public String withdrawMoney(@PathVariable Integer hotelId, @RequestParam Integer nights, HttpServletRequest request,
                               RedirectAttributes redirectAttributes, Model model) {
      User sessionUser = (User) request.getSession().getAttribute("user");
      Account account = sessionUser.getAccount();

      Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
      if (hotelOptional.isPresent()) {
         Hotel hotel = hotelOptional.get();

         BigDecimal hotelPrice = hotel.getPrice().multiply(BigDecimal.valueOf(nights));

         if (account.getAmount().compareTo(hotelPrice) < 0) {
            redirectAttributes.addFlashAttribute("message1", "Not enough money on your account");
            //   redirectAttributes.addAttribute("message", "Not enough money on your account");

            return "redirect:/accounts";
         } else {
            int accountId = account.getId();

            transactionService.saveTransaction(hotelPrice, true, accountId);

            model.addAttribute("user", sessionUser);
         }

      }
      return "/success";
   }


}
